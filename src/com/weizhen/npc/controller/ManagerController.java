package com.weizhen.npc.controller;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.chineseall.dams.common.paging.Paging;
import com.fylaw.utils.EntityUtils;
import com.weizhen.npc.base.BaseController;
import com.weizhen.npc.model.Document;
import com.weizhen.npc.model.ImageMain;
import com.weizhen.npc.model.ImageRelated;
import com.weizhen.npc.model.User;
import com.weizhen.npc.service.CongressService;
import com.weizhen.npc.service.DocumentService;
import com.weizhen.npc.service.ImageMainService;
import com.weizhen.npc.service.ImageRelatedService;
import com.weizhen.npc.service.UserService;
import com.weizhen.npc.utils.Constants;

/**
 * 通用请求
 * 
 * @author y
 *
 */
@Controller
@RequestMapping(value = "/manager")
@SessionAttributes("user")
public class ManagerController extends BaseController {

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

	private String webRootPath;

	@RequestMapping(value = "/users.html")
	public ModelAndView navigation(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("manager/users");
		mav.addObject("users", userService.loadAll());

		return mav;
	}

	@RequestMapping(value = "/users/list.html")
	@ResponseBody
	public List<User> listUser(HttpServletRequest request) {
		return userService.loadAll();
	}

	@RequestMapping(value = "/users/add.html")
	@ResponseBody
	public Map<String, Object> addUser(User user) {
		Map<String, Object> res = new HashMap<String, Object>();

		if (!checkValid(user)) {
			res.put("msg", "字段填写不正确,请重新填写");
			return res;
		}

		try {
			user = userService.addUser(user);
			res.put("user", user);

			return res;
		} catch (Exception e) {
			res.put("msg", e.getMessage());
			return res;
		}
	}

	/**
	 * 修改用户
	 * 
	 * @param user
	 * @return
	 */
	public Map<String, Object> updateUser(User user) {
		Map<String, Object> res = new HashMap<String, Object>();

		try {
			user = userService.updateUser(user);
			res.put("user", user);

			return res;
		} catch (Exception e) {
			res.put("msg", e.getMessage());

			return res;
		}
	}

	private Boolean checkValid(User user) {
		return true;
	}

	@RequestMapping("/imagemains.html")
	public ModelAndView imageMains(HttpServletRequest request,
			@RequestParam Integer congressId) {
		ModelAndView mav = new ModelAndView("manager/imagemains");
		mav.addObject("congressId", congressId);
		mav.addObject("imageMains",
				imageMainService.findByCongressId(congressId));

		return mav;
	}

	@RequestMapping(value = "/imagemains/add.html", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addImageMain(ImageMain imageMain,
			@RequestParam Integer congressId,
			@RequestParam("imageMainFile") MultipartFile imageMainFile) {
		Map<String, Object> res = new HashMap<String, Object>();

		try {
			String fileName = Constants.getImageMainFileDirectory()
					+ String.format("%02d", congressId)
					+ Constants.getFileNameSplitter()
					+ String.format("%02d", imageMain.getImageMainSequence())
					+ imageMainFile.getOriginalFilename().substring(
							imageMainFile.getOriginalFilename()
									.lastIndexOf("."));
			imageMainFile.transferTo(new File(getWebRootPath() + fileName));

			imageMain.setImageMainFilepath(fileName);
			imageMain.setMaterialId(0);
			imageMain.setCheckPublish(0);
			imageMain.setUpdateTime(new Date());
			imageMain = imageMainService.addImageMain(imageMain);

			res.put("imageMain", imageMain);

			return res;
		} catch (Exception e) {
			res.put("msg", e.getMessage());
			return res;
		}
	}

	public String getWebRootPath() {
		if (null == this.webRootPath)
			this.webRootPath = session.getServletContext().getRealPath("/");

		return this.webRootPath;
	}

	@RequestMapping("/relateds.html")
	public ModelAndView showRelateds(@RequestParam Integer imageMainId) {
		ModelAndView mav = new ModelAndView("manager/relateds");
		List<ImageRelated> relateds = imageRelatedService
				.findByImageMainId(imageMainId);
		mav.addObject("relateds", relateds);
		List<Document> documents = documentService
				.findByImageMainId(imageMainId);
		mav.addObject("documents", documents);
		mav.addObject("size", relateds.size() + documents.size());

		return mav;
	}

	@RequestMapping("/relateds/add.html")
	public Map<String, Object> addImageRelated(ImageRelated imageRelated,
			MultipartFile imageRelatedFile, MultipartFile imageRelatedThumbFile) {

		Map<String, Object> res = new HashMap<String, Object>();

		try {
			String fileNamePrefix = Constants.getImageMainFileDirectory()
					+ String.format("%02d", imageRelated.getCongressId())
					+ Constants.getFileNameSplitter()
					+ String.format("%02d", imageRelated.getImageMainId())
					+ Constants.getFileNameSplitter()
					+ String.format("%02d", imageRelated.getImageRelatedSequence());
			
			// 保存文件并设置文件路径
			String fileName = imageRelatedFile.getOriginalFilename();
			String fileNameSuffix = fileName.substring(fileName.lastIndexOf("."));
			fileName = fileNamePrefix + fileNameSuffix;
			imageRelatedFile.transferTo(new File(getWebRootPath() + fileName));
			imageRelated.setImageRelatedFilepath(fileName);

			// 视频资料的缩略图处理
			if (Constants.MATERIAL_TYPE_VEDIO ==  imageRelated.getMaterialId()) {
				fileName = imageRelatedThumbFile.getOriginalFilename();
				fileNameSuffix = fileName.substring(fileName.lastIndexOf("."));
				fileName = fileNamePrefix + fileNameSuffix;
				imageRelatedThumbFile.transferTo(new File(getWebRootPath() + fileName));
			}
			imageRelated.setImageRelatedThumbFilepath(fileName);
			
			imageRelated = imageRelatedService.addImageRelated(imageRelated);

			res.put("imageRelated", imageRelated);

			return res;
		} catch (Exception e) {
			res.put("msg", e.getMessage());
			return res;
		}
	}

	@RequestMapping("/documents/add.html")
	public Map<String, Object> addDocument(Document document, List<String> paragraphs) {
		Map<String, Object> res = new HashMap<String, Object>();

		try {
			document = documentService.addDocument(document, paragraphs);
			res.put("document", document);

			return res;
		} catch (Exception e) {
			res.put("msg", e.getMessage());
			return res;
		}
	}

	@RequestMapping(value = "/index.html")
	public ModelAndView index(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("index");
		mav.addObject("congresses", congressService.loadAll());

		return mav;
	}

	@RequestMapping(value = "/search.html")
	public ModelAndView search(HttpServletRequest request, String keyword,
			Paging paging) {
		ModelAndView mav = new ModelAndView("search");
		mav.addObject("congresses", congressService.loadAll());
		mav.addObject("keyword", keyword);

		if (EntityUtils.notEmpty(keyword)) {
			mav.addObject("imageMains",
					imageMainService.findByKeyword(keyword, paging));
			mav.addObject("imageRelateds",
					imageRelatedService.findByKeyword(keyword, paging));
			mav.addObject("documents",
					documentService.findByKeyword(keyword, paging));
		}

		return mav;
	}

	@RequestMapping(value = "/3d.html")
	public ModelAndView threed(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("3d");
		mav.addObject("congresses", congressService.loadAll());

		return mav;
	}

	@RequestMapping(value = "/360.html")
	public ModelAndView threesixzero(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("360");
		mav.addObject("congresses", congressService.loadAll());

		return mav;
	}

}
