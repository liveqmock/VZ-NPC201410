package com.weizhen.npc.controller;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.weizhen.npc.base.BaseController;
import com.weizhen.npc.base.StatusEntity;
import com.weizhen.npc.model.Congress;
import com.weizhen.npc.model.Document;
import com.weizhen.npc.model.ImageMain;
import com.weizhen.npc.model.ImageRelated;
import com.weizhen.npc.model.ResourceAuditLog;
import com.weizhen.npc.model.User;
import com.weizhen.npc.service.CongressService;
import com.weizhen.npc.service.DocumentService;
import com.weizhen.npc.service.ImageMainService;
import com.weizhen.npc.service.ImageRelatedService;
import com.weizhen.npc.service.ResourceAuditLogService;
import com.weizhen.npc.service.UserService;
import com.weizhen.npc.utils.Constants;
import com.weizhen.npc.utils.MaterialTypeEnum;
import com.weizhen.npc.utils.ModelStatusEnum;
import com.weizhen.npc.utils.OperationEnum;
import com.weizhen.npc.utils.UserTypeEnum;
import com.weizhen.npc.vo.ResourceAuditLogQuery;

/**
 * 通用请求
 * 
 * @author y
 *
 */
@Controller
@RequestMapping(value = "/manager")
@SessionAttributes("loggedUser")
public class ManagerController extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(ManagerController.class);

	@Autowired
	private CongressService congressService;

	@Autowired
	private ImageMainService imageMainService;

	@Autowired
	private ImageRelatedService imageRelatedService;

	@Autowired
	private DocumentService documentService;

	@Autowired
	private UserService userService;

	@Autowired
	private ResourceAuditLogService resourceAuditLogService;

	private String webRootPath;

	@RequestMapping(value = "/users.html")
	public ModelAndView navigation(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("manager/users");
		mav.addObject("users", userService.loadAll());

		return mav;
	}

	@RequestMapping(value = "/users/{userId}.html")
	@ResponseBody
	public User userDetail(@PathVariable Integer userId) {
		return userService.get(userId);
	}

	@RequestMapping(value = "/users/add.html")
	@ResponseBody
	public Map<String, Object> addUser(User user) {
		Map<String, Object> res = new HashMap<String, Object>();

		try {
			User loggedUser = (User) session.getAttribute("user");
			assertUserTypeIn(loggedUser, UserTypeEnum.MANAGER);
			
			if (!checkValid(user)) {
				throw new Exception("字段填写不正确,请重新填写");
			}
			
			user = userService.addUser(user);
			res.put("user", user);
		} catch (Exception e) {
			logger.error("添加用户失败", e);
			res.put("msg", e.getMessage());
		}
		
		return res;
	}

	/**
	 * 修改用户
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/users/modify.html", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateUser(User user) {
		Map<String, Object> res = new HashMap<String, Object>();

		try {
			User currentUser = (User) session.getAttribute("user");
			assertUserTypeIn(currentUser, UserTypeEnum.MANAGER);

			user = userService.updateUser(user);
			res.put("user", user);
		} catch (Exception e) {
			logger.error("修改用户失败", e);
			res.put("msg", e.getMessage());
		}
		
		return res;
	}

	private Boolean checkValid(User user) {
		return true;
	}

	@RequestMapping("/content.html")
	public ModelAndView content() {
		ModelAndView mav = new ModelAndView("manager/content");
		List<Congress> congresses = congressService.loadAll();
		mav.addObject("congresses", congresses);

		return mav;
	}

	@RequestMapping("/imagemains.html")
	public ModelAndView imageMains(HttpServletRequest request, @RequestParam Integer congressId) {
		ModelAndView mav = new ModelAndView("manager/imagemains");
		mav.addObject("congressId", congressId);
		mav.addObject("imageMains", imageMainService.findByCongressId(congressId));

		return mav;
	}
	
	@RequestMapping("/imagemains/{imageMainId}.html")
	@ResponseBody
	public ImageMain imageMainDetail(@PathVariable Integer imageMainId) {
		return imageMainService.get(imageMainId);
	}
	
	@RequestMapping("/imagemainview.html")
	public ModelAndView imageMainView(@RequestParam("id") Integer imageMainId) {
		ModelAndView mav = new ModelAndView("manager/imagemainview");
		mav.addObject("imageMain", imageMainService.get(imageMainId));
		
		return mav;
	}	

	@RequestMapping(value = "/imagemains/add.html", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addImageMain(ImageMain imageMain, @RequestParam Integer congressId,
			@RequestParam("imageMainFile") MultipartFile imageMainFile) {
		Map<String, Object> res = new HashMap<String, Object>();

		try {
			User user = (User) session.getAttribute("user");
			assertUserTypeIn(user, UserTypeEnum.EDITOR);

			saveImageMainFile(imageMain, imageMainFile);
			
			imageMain = imageMainService.addImageMain(imageMain);
			res.put("imageMain", imageMain);

			ResourceAuditLog resourceAuditLog = new ResourceAuditLog();
			resourceAuditLog.setAuditor(user.getRealName());
			resourceAuditLog.setOperation(OperationEnum.SUBMIT.getItemCode());
			resourceAuditLog.setResourceId(imageMain.getImageMainId());
			resourceAuditLog.setResourceType(ImageMain.class.getSimpleName());
			resourceAuditLogService.saveOrUpdate(resourceAuditLog);

			return res;
		} catch (Exception e) {
			res.put("msg", e.getMessage());

			return res;
		}
	}
	
	/**
	 * 保存主题文件
	 * @param imageMain
	 * @param imageMainFile
	 * @return
	 * @throws IOException
	 */
	private String saveImageMainFile(ImageMain imageMain, MultipartFile imageMainFile) throws IOException {
		String originalFilename = imageMainFile.getOriginalFilename();
		String suffixName = originalFilename.substring(originalFilename.lastIndexOf("."));
		String directory = Constants.getImageMainFileDirectory();
		String fileName = String.format("%02d", imageMain.getCongressId()) + Constants.getFileNameSplitter()
				+ String.format("%02d", imageMain.getImageMainSequence());
		
		String filepath = directory + fileName + suffixName;
		File file = new File(getWebRootPath() + filepath);
		imageMainFile.transferTo(file);
		imageMain.setImageMainFilepath(filepath);
		
		String mFilePath = directory + "m/" + fileName + "-m" + suffixName;
		FileUtils.copyFile(file, new File(getWebRootPath() + mFilePath));
		String sFilePath = directory + "s/" + fileName + "-s" + suffixName;
		FileUtils.copyFile(file, new File(getWebRootPath() + sFilePath));
		String bFilePath = directory + "b/" + fileName + "-b" + suffixName;
		FileUtils.copyFile(file, new File(getWebRootPath() + bFilePath));
		
		return filepath;
	}

	@RequestMapping(value = "/imagemains/modify.html", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> modifyImageMain(ImageMain imageMain,
			@RequestParam(value="imageMainFile", required=false) MultipartFile imageMainFile) {
		Map<String, Object> res = new HashMap<String, Object>();

		try {
			User user = (User) session.getAttribute("user");
			assertUserTypeIn(user, UserTypeEnum.EDITOR);

			ImageMain original = imageMainService.get(imageMain.getImageMainId());
			String status = original.getStatus();
			assertCanBeModified(original);

			if (null != imageMainFile) {
				saveImageMainFile(original, imageMainFile);
			}

			original.setImageMainTitle(imageMain.getImageMainTitle());
			original.setImageMainDescription(imageMain.getImageMainDescription());
			original.setImageMainSequence(imageMain.getImageMainSequence());
			
			original = imageMainService.modifyImageMain(original);
			res.put("imageMain", original);

			ResourceAuditLog resourceAuditLog = new ResourceAuditLog();
			resourceAuditLog.setAuditor(user.getRealName());
			resourceAuditLog.setOperation(OperationEnum.SUBMIT.getItemCode());
			resourceAuditLog.setResourceId(original.getImageMainId());
			resourceAuditLog.setResourceType(ImageMain.class.getSimpleName());
			resourceAuditLog.setStatusFrom(status);
			resourceAuditLog.setStatusTo(ModelStatusEnum.SUBMITTED.getItemCode());
			resourceAuditLogService.saveOrUpdate(resourceAuditLog);

			return res;
		} catch (Exception e) {
			res.put("msg", e.getMessage());

			return res;
		}
	}

	private void assertUserTypeIn(User user, UserTypeEnum... expectedUserTypes) throws Exception {
		for (UserTypeEnum expectedUserType : expectedUserTypes)
			if (expectedUserType.getItemCode().equalsIgnoreCase(user.getUserType()))
				return;

		throw new Exception("您无权进行此操作");
	}

	private void assertCanBeModified(StatusEntity statusEntity) throws Exception {
		String status = statusEntity.getStatus();

		if (ModelStatusEnum.SUBMITTED.getItemCode().equalsIgnoreCase(status))
			return;

		if (ModelStatusEnum.REJECTED.getItemCode().equalsIgnoreCase(status))
			return;

		throw new Exception("数据目前不能被修改");
	}

	private String getWebRootPath() {
		if (null == this.webRootPath)
			this.webRootPath = session.getServletContext().getRealPath("/");

		return this.webRootPath;
	}

	@RequestMapping("/relateds.html")
	public ModelAndView showRelateds(@RequestParam Integer imageMainId) {
		ModelAndView mav = new ModelAndView("manager/relateds");

		ImageMain imageMain = imageMainService.get(imageMainId);
		mav.addObject("imageMain", imageMain);
		List<ImageRelated> relateds = imageRelatedService.findAllImageRelatedsOfImageMain(imageMainId);
		mav.addObject("relateds", relateds);
		List<Document> documents = documentService.findAllDocumentsOfImageMain(imageMainId);
		mav.addObject("documents", documents);

		return mav;
	}
	
	@RequestMapping("/relateds/{imageRelatedId}.html")
	@ResponseBody
	public ImageRelated imageRelatedDetail(@PathVariable Integer imageRelatedId) {
		return imageRelatedService.get(imageRelatedId);
	}
	
	@RequestMapping("/imagerelatedview.html")
	public ModelAndView imageRelatedView(@RequestParam("id") Integer imageRelatedId) {
		ModelAndView mav = new ModelAndView("manager/imagerelatedview");
		mav.addObject("imageRelated", imageRelatedService.get(imageRelatedId));
		
		return mav;
	}

	@RequestMapping(value = "/relateds/add.html", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addImageRelated(HttpServletResponse response, ImageRelated imageRelated,
			@RequestParam MultipartFile imageRelatedFile,
			@RequestParam(required = false) MultipartFile imageRelatedThumbFile) {
		Map<String, Object> res = new HashMap<String, Object>();

		try {
			User user = (User) session.getAttribute("user");
			assertUserTypeIn(user, UserTypeEnum.EDITOR);
			
			saveImageRelatedFile(imageRelated, imageRelatedFile, imageRelatedThumbFile);
			
			imageRelated = imageRelatedService.addImageRelated(imageRelated);

			res.put("imageRelated", imageRelated);

			ResourceAuditLog resourceAuditLog = new ResourceAuditLog();
			resourceAuditLog.setAuditor(user.getRealName());
			resourceAuditLog.setOperation(OperationEnum.SUBMIT.getItemCode());
			resourceAuditLog.setResourceId(imageRelated.getImageRelatedId());
			resourceAuditLog.setResourceType(ImageRelated.class.getSimpleName());
			resourceAuditLogService.saveOrUpdate(resourceAuditLog);

			return res;
		} catch (Exception e) {
			logger.error("添加相关资料失败:", e);
			res.put("msg", e.getMessage());

			return res;
		}
	}
	
	/**
	 * 保存相关资料的文件
	 * @param imageRelated
	 * @param imageRelatedFile
	 * @param imageRelatedThumbFile
	 * @throws IOException
	 */
	private void saveImageRelatedFile(ImageRelated imageRelated, MultipartFile imageRelatedFile, MultipartFile imageRelatedThumbFile) throws IOException {
		int materialId = imageRelated.getMaterialId().intValue();
		String directory = Constants.getImageRelatedFileDirectory();
		String fileName = String.format("%02d", imageRelated.getCongressId()) + Constants.getFileNameSplitter()
				+ String.format("%02d", imageRelated.getImageMainId()) + Constants.getFileNameSplitter()
				+ String.format("%02d", imageRelated.getImageRelatedSequence());
		
		String originalFilename = imageRelatedFile.getOriginalFilename();
		String suffixName = originalFilename.substring(originalFilename.lastIndexOf("."));
		
		String filepath  = directory + fileName + suffixName;
		File file = new File(getWebRootPath() + filepath);
		imageRelatedFile.transferTo(file);
		imageRelated.setImageRelatedFilepath(filepath);
		
		if (MaterialTypeEnum.IMAGE.getItemCode() == materialId) {
			String mFilepath = directory + "m/" + fileName + "-m" + suffixName;
			FileUtils.copyFile(file, new File(getWebRootPath() + mFilepath));
			String sFilepath = directory + "s/" + fileName + "-s" + suffixName;
			FileUtils.copyFile(file, new File(getWebRootPath() + sFilepath));
			String bFilepath = directory + "b/" + fileName + "-b" + suffixName;
			FileUtils.copyFile(file, new File(getWebRootPath() + bFilepath));
		}

		// 视频资料的缩略图处理
		if (MaterialTypeEnum.VIDEO.getItemCode() == materialId) {
			originalFilename = imageRelatedThumbFile.getOriginalFilename();
			suffixName = originalFilename.substring(originalFilename.lastIndexOf("."));
			filepath  = directory + fileName + suffixName;
			file = new File(getWebRootPath() + filepath);
			imageRelatedThumbFile.transferTo(file);
			
			String mFilepath = directory + "m/" + fileName + "-m" + suffixName;
			FileUtils.copyFile(file, new File(getWebRootPath() + mFilepath));
			String sFilepath = directory + "s/" + fileName + "-s" + suffixName;
			FileUtils.copyFile(file, new File(getWebRootPath() + sFilepath));
			String bFilepath = directory + "b/" + fileName + "-b" + suffixName;
			FileUtils.copyFile(file, new File(getWebRootPath() + bFilepath));
		}
		imageRelated.setImageRelatedThumbFilepath(filepath);
	}
	
	

	@RequestMapping(value = "/relateds/modify.html", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> modifyImageRelated(ImageRelated imageRelated,
			@RequestParam(required=false) MultipartFile imageRelatedFile,
			@RequestParam(required = false) MultipartFile imageRelatedThumbFile) {
		Map<String, Object> res = new HashMap<String, Object>();

		try {
			User user = (User) session.getAttribute("user");
			assertUserTypeIn(user, UserTypeEnum.EDITOR);

			ImageRelated original = imageRelatedService.get(imageRelated.getImageRelatedId());
			String status = original.getStatus();
			assertCanBeModified(original);

			if (null != imageRelatedFile) {
				String fileNamePrefix = Constants.getImageMainFileDirectory()
						+ String.format("%02d", original.getCongressId()) + Constants.getFileNameSplitter()
						+ String.format("%02d", original.getImageMainId()) + Constants.getFileNameSplitter()
						+ String.format("%02d", imageRelated.getImageRelatedSequence());
	
				// 保存文件并设置文件路径
				String fileName = imageRelatedFile.getOriginalFilename();
				String fileNameSuffix = fileName.substring(fileName.lastIndexOf("."));
				fileName = fileNamePrefix + fileNameSuffix;
				imageRelatedFile.transferTo(new File(getWebRootPath() + fileName));
				original.setImageRelatedFilepath(fileName);
				
				// 视频资料的缩略图处理
				if (MaterialTypeEnum.VIDEO.getItemCode() == imageRelated.getMaterialId().intValue()) {
					fileName = imageRelatedThumbFile.getOriginalFilename();
					fileNameSuffix = fileName.substring(fileName.lastIndexOf("."));
					fileName = fileNamePrefix + fileNameSuffix;
					imageRelatedThumbFile.transferTo(new File(getWebRootPath() + fileName));
				}
				original.setImageRelatedThumbFilepath(fileName);
			}

			original.setImageRelatedTitle(imageRelated.getImageRelatedTitle());
			original.setImageRelatedDescription(imageRelated.getImageRelatedDescription());
			original.setImageRelatedSequence(imageRelated.getImageRelatedSequence());

			original = imageRelatedService.modifyImageRelated(original);

			res.put("imageRelated", original);

			ResourceAuditLog resourceAuditLog = new ResourceAuditLog();
			resourceAuditLog.setAuditor(user.getRealName());
			resourceAuditLog.setOperation(OperationEnum.SUBMIT.getItemCode());
			resourceAuditLog.setResourceId(imageRelated.getImageRelatedId());
			resourceAuditLog.setResourceType(ImageRelated.class.getSimpleName());
			resourceAuditLog.setStatusFrom(status);
			resourceAuditLogService.saveOrUpdate(resourceAuditLog);

			return res;
		} catch (Exception e) {
			logger.error("添加相关资料失败:", e);
			res.put("msg", e.getMessage());

			return res;
		}
	}

	@RequestMapping(value = "/documents/add.html", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addDocument(Document document, @RequestParam String[] paragraphContents) {
		Map<String, Object> res = new HashMap<String, Object>();

		try {
			User user = (User) session.getAttribute("user");
			assertUserTypeIn(user, UserTypeEnum.EDITOR);

			document = documentService.addDocument(document, Arrays.asList(paragraphContents));
			res.put("document", document);

			ResourceAuditLog resourceAuditLog = new ResourceAuditLog();
			resourceAuditLog.setAuditor(user.getRealName());
			resourceAuditLog.setOperation(OperationEnum.SUBMIT.getItemCode());
			resourceAuditLog.setResourceId(document.getDocumentId());
			resourceAuditLog.setResourceType(Document.class.getSimpleName());
			resourceAuditLogService.saveOrUpdate(resourceAuditLog);

			return res;
		} catch (Exception e) {
			res.put("msg", e.getMessage());
			return res;
		}
	}
	
	
	@RequestMapping(value = "/documents/{documentId}.html")
	@ResponseBody
	public Document documentDetail(@PathVariable Integer documentId) {
		return documentService.get(documentId);
	}
	
	@RequestMapping(value = "/documentview.html")
	public ModelAndView documentView(@RequestParam("id") Integer documentId) {
		ModelAndView mav = new ModelAndView("manager/documentview");
		mav.addObject("document", documentService.get(documentId));
		
		return mav;
	}
	
	@RequestMapping(value = "/documents/modify.html", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> modifyDocument(Document document, @RequestParam String[] paragraphContents) {
		Map<String, Object> res = new HashMap<String, Object>();

		try {
			User user = (User) session.getAttribute("user");
			assertUserTypeIn(user, UserTypeEnum.EDITOR);
			
			Document original = documentService.get(document.getDocumentId());
			String status = original.getStatus();
			assertCanBeModified(original);
			
			original.setDocumentTitle(document.getDocumentTitle());
			original = documentService.modifyDocument(original, Arrays.asList(paragraphContents));
			res.put("document", original);

			ResourceAuditLog resourceAuditLog = new ResourceAuditLog();
			resourceAuditLog.setAuditor(user.getRealName());
			resourceAuditLog.setOperation(OperationEnum.SUBMIT.getItemCode());
			resourceAuditLog.setResourceId(document.getDocumentId());
			resourceAuditLog.setResourceType(Document.class.getSimpleName());
			resourceAuditLog.setStatusFrom(status);
			resourceAuditLog.setStatusTo(ModelStatusEnum.SUBMITTED.getItemCode());
			resourceAuditLogService.saveOrUpdate(resourceAuditLog);

			return res;
		} catch (Exception e) {
			res.put("msg", e.getMessage());
			return res;
		}
	}	

	@RequestMapping("/auditing.html")
	public ModelAndView auditing() {
		ModelAndView mav = new ModelAndView("manager/auditing");

		mav.addObject("imageMains", imageMainService.findSubmittedImageMains());
		mav.addObject("imageRelateds", imageRelatedService.findSubmittedRelateds());
		mav.addObject("documents", documentService.findSubmittedDocuments());

		return mav;
	}

	@RequestMapping(value = "/resource/audit.html", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> auditResource(@RequestParam Integer resourceId, @RequestParam String operation,
			@RequestParam String resourceType, ResourceAuditLog resourceAuditLog) {
		Map<String, Object> res = new HashMap<String, Object>();

		try {
			User user = (User) session.getAttribute("user");
			assertUserTypeIn(user, UserTypeEnum.AUDITOR);

			resourceAuditLog.setAuditor(user.getRealName());

			resourceAuditLogService.audit(resourceAuditLog);
		} catch (Exception e) {
			logger.error("审核失败", e);

			res.put("msg", e.getMessage());
		}

		return res;
	}

	@RequestMapping(value = "/publish.html", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> publish(@RequestParam Integer resourceId, @RequestParam String resourceType) {
		Map<String, Object> res = new HashMap<String, Object>();

		try {
			User user = (User) session.getAttribute("user");
			assertUserTypeIn(user, UserTypeEnum.EDITOR);

			ResourceAuditLog resourceAuditLog = new ResourceAuditLog();
			resourceAuditLog.setAuditor(user.getRealName());
			resourceAuditLog.setResourceType(resourceType);
			resourceAuditLog.setResourceId(resourceId);
			resourceAuditLog.setOperation(OperationEnum.PUBLISH.getItemCode());

			resourceAuditLogService.publish(resourceAuditLog);
		} catch (Exception e) {
			logger.error("发布失败", e);

			res.put("msg", e.getMessage());
		}

		return res;
	}

	@RequestMapping("/auditlogs.html")
	public ModelAndView auditLogs(@RequestParam String resourceType, @RequestParam Integer resourceId,
			ResourceAuditLogQuery query) {
		ModelAndView mav = new ModelAndView("manager/auditlogs");

		List<ResourceAuditLog> auditLogs = resourceAuditLogService.showAuditLogs(query);
		mav.addObject("auditLogs", auditLogs);

		return mav;
	}
	
	@RequestMapping("/stats.html")
	public ModelAndView stats() {
		ModelAndView mav = new ModelAndView("manager/stats");
		
		return mav;
	}
}
