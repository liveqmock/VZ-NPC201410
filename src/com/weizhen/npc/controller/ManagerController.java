package com.weizhen.npc.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.chineseall.dams.common.json.JsonMapper;
import com.fylaw.utils.FileManager;
import com.weizhen.npc.base.BaseController;
import com.weizhen.npc.base.StatusEntity;
import com.weizhen.npc.exception.PermissionDenyException;
import com.weizhen.npc.json.AuditLogData;
import com.weizhen.npc.json.CongressData;
import com.weizhen.npc.json.ContentDetailData;
import com.weizhen.npc.json.ContentListData;
import com.weizhen.npc.json.Save;
import com.weizhen.npc.model.Congress;
import com.weizhen.npc.model.Document;
import com.weizhen.npc.model.ImageMain;
import com.weizhen.npc.model.ImageRelated;
import com.weizhen.npc.model.Person;
import com.weizhen.npc.model.ResourceAuditLog;
import com.weizhen.npc.model.User;
import com.weizhen.npc.service.CongressService;
import com.weizhen.npc.service.DocumentService;
import com.weizhen.npc.service.ImageMainService;
import com.weizhen.npc.service.ImageRelatedService;
import com.weizhen.npc.service.PersonService;
import com.weizhen.npc.service.ResourceAuditLogService;
import com.weizhen.npc.service.UserService;
import com.weizhen.npc.utils.Constants;
import com.weizhen.npc.utils.MaterialTypeEnum;
import com.weizhen.npc.utils.ModelStatusEnum;
import com.weizhen.npc.utils.OperationEnum;
import com.weizhen.npc.utils.ResourceTypeEnum;
import com.weizhen.npc.utils.UserTypeEnum;
import com.weizhen.npc.vo.ResourceAuditLogQuery;
import com.weizhen.npc.vo.Response;

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
	
	@Autowired
	private PersonService personService;

	private String webRootPath;
	private String uploadPath;

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
	@RequestMapping(value = "/users/modify.html", method = RequestMethod.POST)
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
		List<Congress> congresses = congressService.loadAllPublishedCongresses();
		mav.addObject("congresses", congresses);

		return mav;
	}

	@RequestMapping("/imagemains.html")
	public ModelAndView imageMains(HttpServletRequest request, @RequestParam Integer congressId) {
		ModelAndView mav = new ModelAndView("manager/imagemains");
		mav.addObject("congressId", congressId);
		mav.addObject("imageMains", imageMainService.findPublishedImageMainsByCongressId(congressId));

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
	 * 
	 * @param imageMain
	 * @param imageMainFile
	 * @return
	 * @throws IOException
	 */
	@Deprecated
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

	/**
	 * 保存主题文件
	 * 
	 * @param imageMain
	 * @param imageMainFile
	 * @return
	 * @throws IOException
	 */
	private String saveImageMainFile(ImageMain imageMain, File imageMainFile) throws IOException {
		String originalFilename = imageMainFile.getName();
		String suffixName = "";
		if (originalFilename.lastIndexOf(".") >= 0)
			suffixName = originalFilename.substring(originalFilename.lastIndexOf("."));
		String directory = Constants.getImageMainFileDirectory();
		String fileName = String.format("%02d", imageMain.getCongressId()) + Constants.getFileNameSplitter()
				+ String.format("%02d", imageMain.getImageMainSequence());

		String filepath = directory + fileName + suffixName;
		File file = new File(getWebRootPath() + filepath);
		FileUtils.copyFile(imageMainFile, file);
		imageMain.setImageMainFilepath(filepath);
		imageMainFile.delete();

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
			@RequestParam(value = "imageMainFile", required = false) MultipartFile imageMainFile) {
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

	private void assertUserTypeIn(User user, UserTypeEnum... expectedUserTypes) {
		for (UserTypeEnum expectedUserType : expectedUserTypes)
			if (expectedUserType.getItemCode().equalsIgnoreCase(user.getUserType()))
				return;

		throw new PermissionDenyException("您无权进行此操作");
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
	 * 
	 * @param imageRelated
	 * @param imageRelatedFile
	 * @param imageRelatedThumbFile
	 * @throws IOException
	 */
	@Deprecated
	private void saveImageRelatedFile(ImageRelated imageRelated, MultipartFile imageRelatedFile,
			MultipartFile imageRelatedThumbFile) throws IOException {
		int materialId = imageRelated.getMaterialId().intValue();
		String directory = Constants.getImageRelatedFileDirectory();
		String fileName = String.format("%02d", imageRelated.getCongressId()) + Constants.getFileNameSplitter()
				+ String.format("%02d", imageRelated.getImageMainId()) + Constants.getFileNameSplitter()
				+ String.format("%02d", imageRelated.getImageRelatedSequence());

		String originalFilename = imageRelatedFile.getOriginalFilename();
		String suffixName = originalFilename.substring(originalFilename.lastIndexOf("."));

		String filepath = directory + fileName + suffixName;
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
			filepath = directory + fileName + suffixName;
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

	private void saveImageRelatedFile(ImageRelated imageRelated, File imageRelatedFile, File imageRelatedThumbFile)
			throws IOException {
		int materialId = imageRelated.getMaterialId().intValue();
		String directory = Constants.getImageRelatedFileDirectory();
		String fileName = String.format("%02d", imageRelated.getCongressId()) + Constants.getFileNameSplitter()
				+ String.format("%02d", imageRelated.getImageMainId()) + Constants.getFileNameSplitter()
				+ String.format("%02d", imageRelated.getImageRelatedSequence());

		String originalFilename = imageRelatedFile.getName();
		String suffixName = originalFilename.substring(originalFilename.lastIndexOf("."));

		String filepath = directory + fileName + suffixName;
		File file = new File(getWebRootPath() + filepath);

		// 图片相关资料文件处理
		if (MaterialTypeEnum.IMAGE.getItemCode() == materialId) {
			if (null != imageRelatedFile && imageRelatedFile.exists()) {
				FileUtils.copyFile(imageRelatedFile, file);
				imageRelated.setImageRelatedFilepath(filepath);
				imageRelated.setImageRelatedThumbFilepath(filepath);

				String mFilepath = directory + "m/" + fileName + "-m" + suffixName;
				FileUtils.copyFile(file, new File(getWebRootPath() + mFilepath));
				String sFilepath = directory + "s/" + fileName + "-s" + suffixName;
				FileUtils.copyFile(file, new File(getWebRootPath() + sFilepath));
				String bFilepath = directory + "b/" + fileName + "-b" + suffixName;
				FileUtils.copyFile(file, new File(getWebRootPath() + bFilepath));

				imageRelatedFile.delete();
			}
		}

		// 视频相关资料文件处理
		if (MaterialTypeEnum.VIDEO.getItemCode() == materialId) {
			if (null != imageRelatedFile && imageRelatedFile.exists()) {
				FileUtils.copyFile(imageRelatedFile, file);
				imageRelated.setImageRelatedFilepath(filepath);

				imageRelatedFile.delete();
			}

			if (null != imageRelatedThumbFile && imageRelatedThumbFile.exists()) {
				originalFilename = imageRelatedThumbFile.getName();
				suffixName = originalFilename.substring(originalFilename.lastIndexOf("."));
				filepath = directory + fileName + suffixName;
				file = new File(getWebRootPath() + filepath);
				FileUtils.copyFile(imageRelatedThumbFile, file);
				imageRelated.setImageRelatedThumbFilepath(filepath);

				String mFilepath = directory + "m/" + fileName + "-m" + suffixName;
				FileUtils.copyFile(file, new File(getWebRootPath() + mFilepath));
				String sFilepath = directory + "s/" + fileName + "-s" + suffixName;
				FileUtils.copyFile(file, new File(getWebRootPath() + sFilepath));
				String bFilepath = directory + "b/" + fileName + "-b" + suffixName;
				FileUtils.copyFile(file, new File(getWebRootPath() + bFilepath));

				imageRelatedThumbFile.delete();
			}
		}
	}

	@RequestMapping(value = "/relateds/modify.html", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> modifyImageRelated(ImageRelated imageRelated,
			@RequestParam(required = false) MultipartFile imageRelatedFile,
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

	@RequestMapping(value = "/publish.json", method = RequestMethod.POST)
	@ResponseBody
	public Response<ContentDetailData> publish(@RequestParam String resourceType, @RequestParam Integer resourceId) {
		User user = (User) session.getAttribute("user");
		assertUserTypeIn(user, UserTypeEnum.EDITOR);

		ResourceAuditLog resourceAuditLog = new ResourceAuditLog();
		resourceAuditLog.setAuditor(user.getRealName());
		resourceAuditLog.setResourceType(resourceType);
		resourceAuditLog.setResourceId(resourceId);
		resourceAuditLog.setOperation(OperationEnum.PUBLISH.getItemCode());

		resourceAuditLogService.publish(resourceAuditLog);

		if (ResourceTypeEnum.IMAGEMAIN.eq(resourceType))
			imageMainService.publish(resourceId);
		else if (ResourceTypeEnum.IMAGERELATED.eq(resourceType))
			imageRelatedService.publish(resourceId);
		else if (ResourceTypeEnum.DOCUMENT.eq(resourceType))
			documentService.publish(resourceId);

		return contentDetail(resourceType, resourceId);
	}

	@RequestMapping(value = "/unpublish.json", method = RequestMethod.POST)
	@ResponseBody
	public Response<ContentDetailData> unpublish(@RequestParam String resourceType, @RequestParam Integer resourceId) {
		User user = (User) session.getAttribute("user");
		assertUserTypeIn(user, UserTypeEnum.EDITOR);

		ResourceAuditLog resourceAuditLog = new ResourceAuditLog();
		resourceAuditLog.setAuditor(user.getRealName());
		resourceAuditLog.setResourceType(resourceType);
		resourceAuditLog.setResourceId(resourceId);
		resourceAuditLog.setOperation(OperationEnum.UNPUBLISH.getItemCode());

		resourceAuditLogService.unpublish(resourceAuditLog);

		if (ResourceTypeEnum.IMAGEMAIN.eq(resourceType))
			imageMainService.unpublish(resourceId);
		else if (ResourceTypeEnum.IMAGERELATED.eq(resourceType))
			imageRelatedService.unpublish(resourceId);
		else if (ResourceTypeEnum.DOCUMENT.eq(resourceType))
			documentService.unpublish(resourceId);

		return contentDetail(resourceType, resourceId);
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

	@RequestMapping("/congressList.json")
	@ResponseBody
	public Response<List<CongressData>> congressList() {
		List<Congress> congresses = congressService.loadAllCongresses();
		List<CongressData> data = new ArrayList<CongressData>();
		for (Congress congress : congresses) {
			data.add(CongressData.from(congress));
		}

		return new Response<List<CongressData>>(data);
	}

	@RequestMapping("/auditLog.json")
	@ResponseBody
	public Response<List<AuditLogData>> auditLog(@RequestParam("contentType") String resourceType,
			@RequestParam("contentId") Integer resourceId) {
		ResourceAuditLogQuery query = new ResourceAuditLogQuery();
		query.setResourceType(resourceType);
		query.setResourceId(resourceId);

		List<ResourceAuditLog> logs = resourceAuditLogService.showAuditLogs(query);
		List<AuditLogData> data = new ArrayList<AuditLogData>();
		for (ResourceAuditLog log : logs) {
			data.add(AuditLogData.from(log));
		}

		return new Response<List<AuditLogData>>(data);
	}

	@RequestMapping("/contentDetail.json")
	@ResponseBody
	public Response<ContentDetailData> contentDetail(@RequestParam String contentType, @RequestParam Integer contentId) {
		ContentDetailData data = null;

		if (ResourceTypeEnum.IMAGEMAIN.getItemCode().equalsIgnoreCase(contentType)) {// 主题详情
			ImageMain imageMain = imageMainService.get(contentId);
			Congress congress = congressService.load(imageMain.getCongressId());

			data = ContentDetailData.from(imageMain, congress);
		} else if (ResourceTypeEnum.IMAGERELATED.getItemCode().equalsIgnoreCase(contentType)) { // 相关资料
			ImageRelated imageRelated = imageRelatedService.get(contentId);
			ImageMain imageMain = imageMainService.get(imageRelated.getImageMainId());
			Congress congress = congressService.load(imageMain.getCongressId());

			data = ContentDetailData.from(imageRelated, imageMain, congress);
		} else if (ResourceTypeEnum.DOCUMENT.getItemCode().equalsIgnoreCase(contentType)) { // 文章
			Document document = documentService.get(contentId);
			ImageMain imageMain = imageMainService.get(document.getImageMainId());
			Congress congress = congressService.load(imageMain.getCongressId());

			data = ContentDetailData.from(document, imageMain, congress);
		}

		return new Response<ContentDetailData>(data);
	}

	@RequestMapping("/contentList.json")
	@ResponseBody
	public Response<List<ContentListData>> contentList(@RequestParam Integer congressId) {
		List<ImageMain> imageMains = imageMainService.findAllImageMainsByCongressId(congressId);
		List<ContentListData> data = new ArrayList<ContentListData>();

		for (ImageMain imageMain : imageMains) {
			data.add(ContentListData.from(imageMain));

			Integer imageMainId = imageMain.getImageMainId();

			List<ImageRelated> imageRelateds = imageRelatedService.findAllImageRelatedsOfImageMain(imageMainId);
			for (ImageRelated imageRelated : imageRelateds) {
				data.add(ContentListData.from(imageMain, imageRelated));
			}

			List<Document> documents = documentService.findAllDocumentsOfImageMain(imageMainId);
			for (Document document : documents) {
				data.add(ContentListData.from(imageMain, document));
			}
		}

		return new Response<List<ContentListData>>(data);
	}

	@RequestMapping("/deleteContent.json")
	@ResponseBody
	public Response<Object> deleteContent(@RequestParam("contentTypeNo") String resourceType,
			@RequestParam("contentId") Integer resourceId) {
		Object data = new Object();

		if (ResourceTypeEnum.IMAGEMAIN.getItemCode().equalsIgnoreCase(resourceType)) {
			imageMainService.remove(resourceId);
		} else if (ResourceTypeEnum.IMAGERELATED.getItemCode().equalsIgnoreCase(resourceType)) {
			imageRelatedService.remove(resourceId);
		} else if (ResourceTypeEnum.DOCUMENT.getItemCode().equalsIgnoreCase(resourceType)) {
			documentService.remove(resourceId);
		}

		return new Response<Object>(data);
	}

	@RequestMapping("/saveContent.json")
	@ResponseBody
	public Response<ContentDetailData> saveContent(@RequestParam("contentTypeNo") String resourceType,
			@RequestParam("contentId") Integer resourceId, Save data) throws IOException {

		User user = (User) session.getAttribute("user");
		assertUserTypeIn(user, UserTypeEnum.EDITOR);

		String statusFrom = null;

		if (ResourceTypeEnum.IMAGEMAIN.getItemCode().equalsIgnoreCase(resourceType)) {
			ImageMain imageMain = new ImageMain();

			Integer imageMainId = data.getImageMainId();
			if (null == imageMainId) {
				imageMain.setCongressId(data.getBelongCongressId());
				imageMain.setCreator(user.getRealName());
				imageMain.setImageMainSequence(imageMainService.nextSequence(data.getBelongCongressId()));
			} else {
				imageMain = imageMainService.get(imageMainId);
				statusFrom = imageMain.getStatus();
			}

			imageMain.setImageMainTitle(data.getContentTitle());
			imageMain.setImageMainDescription(data.getContentDescription());
			if (null != data.getImageMainImgPath()) {
				imageMain.setImageMainFilepath(data.getImageMainImgPath());
				saveImageMainFile(imageMain, new File(getUploadPath() + data.getImageMainImgPath()));
			}

			imageMain.setKeyword(data.getContentKeyword());
			imageMain.setDate(data.getContentDate());
			imageMain.setPerson(data.getContentPerson());
			imageMain.setLocation(data.getContentLocation());
			imageMain.setLocationLong(data.getContentLocationLong());
			imageMain.setLocationLat(data.getContentLocationLat());

			if (null == imageMainId) {
				imageMain = imageMainService.addImageMain(imageMain);
			} else {
				imageMain = imageMainService.modifyImageMain(imageMain);
			}

			resourceId = imageMain.getImageMainId();
		} else if (ResourceTypeEnum.IMAGERELATED.getItemCode().equalsIgnoreCase(resourceType)) {
			ImageRelated imageRelated = new ImageRelated();

			Integer imageRelatedId = data.getImageRelatedId();
			if (null == imageRelatedId) {
				imageRelated.setCongressId(data.getBelongCongressId());
				imageRelated.setImageMainId(data.getBelongImageMainId());
				imageRelated.setCreator(user.getRealName());
				imageRelated.setImageRelatedSequence(imageRelatedService.nextSequence(data.getBelongImageMainId()));
			} else {
				imageRelated = imageRelatedService.get(imageRelatedId);
				statusFrom = imageRelated.getStatus();
			}

			imageRelated.setImageRelatedTitle(data.getContentTitle());
			imageRelated.setImageRelatedDescription(data.getContentDescription());
			imageRelated.setMaterialId(data.getImageRelatedType());

			if (MaterialTypeEnum.IMAGE.getItemCode().intValue() == data.getImageRelatedType()) {
				if (null != data.getImageRelatedImgPath()) {
					saveImageRelatedFile(imageRelated, new File(getUploadPath() + data.getImageRelatedImgPath()), null);
				}
			} else if (MaterialTypeEnum.VIDEO.getItemCode().intValue() == data.getImageRelatedType()) {
				if (null != data.getImageRelatedVideoPath() || null != data.getImageRelatedVideoThumbImgPath()) {
					imageRelated.setImageRelatedFilepath(data.getImageRelatedVideoPath());
					imageRelated.setImageRelatedThumbFilepath(data.getImageRelatedVideoThumbImgPath());
					File imageRelatedFile = new File(getUploadPath() + data.getImageRelatedVideoPath());
					File imageRelatedThumbFile = new File(getUploadPath() + data.getImageRelatedVideoThumbImgPath());
					saveImageRelatedFile(imageRelated, imageRelatedFile, imageRelatedThumbFile);
				}
			}

			imageRelated.setKeyword(data.getContentKeyword());
			imageRelated.setDate(data.getContentDate());
			imageRelated.setPerson(data.getContentPerson());
			imageRelated.setLocation(data.getContentLocation());
			imageRelated.setLocationLong(data.getContentLocationLong());
			imageRelated.setLocationLat(data.getContentLocationLat());

			if (null == imageRelatedId) {
				imageRelated = imageRelatedService.addImageRelated(imageRelated);
			} else {
				imageRelated = imageRelatedService.modifyImageRelated(imageRelated);
			}

			resourceId = imageRelated.getImageRelatedId();
		} else if (ResourceTypeEnum.DOCUMENT.getItemCode().equalsIgnoreCase(resourceType)) {
			Document document = new Document();

			Integer documentId = data.getImageRelatedId();
			if (null == documentId) {
				document.setCongressId(data.getBelongCongressId());
				document.setImageMainId(data.getBelongImageMainId());
				document.setCreator(user.getRealName());
			} else {
				document = documentService.get(documentId);
				statusFrom = document.getStatus();
			}

			document.setDocumentTitle(data.getContentTitle());
			document.setDocumentDescription(data.getContentDescription());
			document.setKeyword(data.getContentKeyword());
			document.setDate(data.getContentDate());
			document.setPerson(data.getContentPerson());
			document.setLocation(data.getContentLocation());
			document.setLocationLong(data.getContentLocationLong());
			document.setLocationLat(data.getContentLocationLat());

			if (null == documentId) {
				document = documentService.addDocument(document, data.getParagraphContents());
			} else {
				document = documentService.modifyDocument(document, data.getParagraphContents());
			}

			resourceId = document.getDocumentId();
		}

		ResourceAuditLog resourceAuditLog = new ResourceAuditLog();
		resourceAuditLog.setAuditor(user.getRealName());
		resourceAuditLog.setOperation(OperationEnum.SAVE.getItemCode());
		resourceAuditLog.setResourceId(resourceId);
		resourceAuditLog.setResourceType(resourceType);
		resourceAuditLog.setStatusFrom(statusFrom);
		resourceAuditLog.setStatusTo(ModelStatusEnum.SAVED.getItemCode());
		resourceAuditLogService.saveOrUpdate(resourceAuditLog);

		return contentDetail(resourceType, resourceId);
	}

	@RequestMapping("/submitAudit.json")
	@ResponseBody
	public Response<ContentDetailData> submitAudit(@RequestParam("contentTypeNo") String resourceType,
			@RequestParam("contentId") Integer resourceId, ResourceAuditLog resourceAuditLog, Save data)
			throws IOException {
		Response<ContentDetailData> res = saveContent(resourceType, resourceId, data);

		resourceAuditLog.setResourceType(resourceType);
		resourceAuditLog.setResourceId(res.getData().getContentId());
		resourceAuditLog.setOperation(OperationEnum.SUBMIT.getItemCode());
		resourceAuditLog.setAuditor(((User) session.getAttribute("user")).getRealName());
		resourceAuditLogService.submit(resourceAuditLog);

		return contentDetail(resourceType, res.getData().getContentId());
	}

	@RequestMapping(value = "/upload.html", method = RequestMethod.POST)
	@ResponseBody
	public Response<String> upload(@RequestParam MultipartFile uploadfile) throws IOException {
		FileManager.mkdirIfNotExists(getUploadPath());

		String fileName = uploadfile.getOriginalFilename();
		String extName = "";
		if (fileName.lastIndexOf(".") >= 0)
			extName = fileName.substring(fileName.lastIndexOf("."));

		fileName = UUID.randomUUID().toString();
		uploadfile.transferTo(new File(getUploadPath() + fileName + extName));

		return new Response<String>(fileName + extName);
	}

	private String getUploadPath() {
		if (null == this.uploadPath)
			this.uploadPath = getWebRootPath() + "/uploads/";

		return this.uploadPath;
	}

	@RequestMapping(value = "/adjustSequence.json", method = RequestMethod.POST)
	@ResponseBody
	public Response<Integer> adjustSequence(@RequestParam String resourceType, @RequestParam Integer resourceId,
			@RequestParam String direction) {
		if (ResourceTypeEnum.DOCUMENT.getItemCode().equalsIgnoreCase(resourceType))
			documentService.adjustSequence(resourceId, direction);
		else if (ResourceTypeEnum.IMAGERELATED.getItemCode().equalsIgnoreCase(resourceType))
			imageRelatedService.adjustSequence(resourceId, direction);
		else if (ResourceTypeEnum.IMAGEMAIN.getItemCode().equalsIgnoreCase(resourceType))
			imageMainService.adjustSequence(resourceId, direction);

		return new Response<Integer>(resourceId);
	}
	
	
	@RequestMapping(value="/persons.json", method = RequestMethod.GET)
	@ResponseBody
	public Response<List<Person>> persons() {
		return new Response<List<Person>>(personService.loadAll());
	}
	

	@ExceptionHandler(RuntimeException.class)
	public void handleRuntimeException(RuntimeException ex, HttpServletResponse response) throws IOException {
		logger.error("捕获错误信息:", ex);
		printError(ex.getMessage(), response);
	}

	@ExceptionHandler(Exception.class)
	public void handleRuntimeException(Exception ex, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		logger.error("捕获错误信息:", ex);
		printError(ex.getMessage(), response);
	}

	private void printError(String message, HttpServletResponse response) throws IOException {
		Response<String> res = Response.error(message);

		PrintWriter pw = response.getWriter();
		pw.print(JsonMapper.toJson(res, true));
		pw.flush();
		pw.close();
	}
}
