package com.weizhen.npc.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chineseall.dams.common.paging.ConditionsAndNameValuePairs;
import com.chineseall.dams.common.paging.Paging;
import com.chineseall.dams.common.paging.PagingQueryResult;
import com.chineseall.dams.common.paging.SimpleQueryModelProcessor;
import com.fylaw.utils.DateTimeUtils;
import com.fylaw.utils.EntityUtils;
import com.weizhen.npc.base.BaseService;
import com.weizhen.npc.dao.DateDocumentDAO;
import com.weizhen.npc.dao.DocumentDAO;
import com.weizhen.npc.dao.LocationDAO;
import com.weizhen.npc.dao.LocationDocumentDAO;
import com.weizhen.npc.dao.ParagraphDAO;
import com.weizhen.npc.dao.PersonDAO;
import com.weizhen.npc.dao.PersonDocumentDAO;
import com.weizhen.npc.model.DateDocument;
import com.weizhen.npc.model.Document;
import com.weizhen.npc.model.Location;
import com.weizhen.npc.model.LocationDocument;
import com.weizhen.npc.model.Paragraph;
import com.weizhen.npc.model.PersonDocument;
import com.weizhen.npc.utils.MaterialTypeEnum;
import com.weizhen.npc.utils.ModelStatusEnum;
import com.weizhen.npc.vo.DocumentQuery;
import com.weizhen.npc.vo.ParagraphQuery;

/**
 * 界别主题相关文章
 * 
 * @author y
 * 
 */
@Service
public class DocumentService extends BaseService {

	@Autowired
	private DocumentDAO documentDao;

	@Autowired
	private ParagraphDAO paragraphDao;

	@Autowired
	private DateDocumentDAO dateDocumentDao;

	@Autowired
	private PersonDAO personDao;

	@Autowired
	private PersonDocumentDAO personDocumentDao;

	@Autowired
	private LocationDAO locationDao;

	@Autowired
	private LocationDocumentDAO locationDocumentDao;

	public List<Document> findByImageMainId(Integer imageMainId) {
		return documentDao.findByImageMainId(imageMainId);
	}

	public Document get(Integer documentId) {
		return documentDao.get(documentId);
	}

	public PagingQueryResult<Document> findByKeyword(String keyword, Paging paging) {
		ParagraphQuery query = new ParagraphQuery();
		query.setCombinationType("or");
		query.setParagraphContent(keyword);

		DocumentQuery document = new DocumentQuery();
		document.setDocumentTitle(keyword);
		query.setDocument(document);

		ConditionsAndNameValuePairs canv = SimpleQueryModelProcessor.gen(query, "m");
		String select = "select distinct m.document from Paragraph m " + canv.getWhereCondition(true)
				+ " order by m.document.congressId, m.document.imageMainId, m.document.documentSequence";

		return documentDao.pagingQuery(select, canv.getNameValuePairs(), paging);
	}

	public Document addDocument(Document document, List<String> paragraphs) {
		documentDao.loadExists(document.getImageMainId());

		document.setMaterialId(MaterialTypeEnum.ARTICLE.getItemCode());
		document.setDocumentSequence(documentDao.nextSequence(document.getImageMainId()));
		document.setCheckPublish(1);
		document.setCreatedDate(new Date());
		document.setUpdateTime(new Date());
		document.setStatus(ModelStatusEnum.SAVED.getItemCode());
		document = documentDao.saveOrUpdate(document);

		if (EntityUtils.notEmpty(paragraphs)) {
			for (String paragraphContent : paragraphs) {
				if (EntityUtils.notEmpty(paragraphContent)) {
					Paragraph paragraph = new Paragraph();
					paragraph.setDocument(document);
					paragraph.setParagraphContent(paragraphContent);
					paragraph.setParagraphSequence(0);
					paragraph.setUpdateTime(new Date());

					paragraphDao.saveOrUpdate(paragraph);
				}
			}
		}

		return document;
	}

	public Document modifyDocument(Document document, List<String> paragraphs) {
		document.setUpdateTime(new Date());
		document.setStatus(ModelStatusEnum.SAVED.getItemCode());
		document = documentDao.saveOrUpdate(document);

		paragraphDao.execute("delete from Paragraph p where p.document.documentId = ?", document.getDocumentId());

		if (EntityUtils.notEmpty(paragraphs)) {
			for (String paragraphContent : paragraphs) {
				if (EntityUtils.notEmpty(paragraphContent)) {
					Paragraph paragraph = new Paragraph();
					paragraph.setDocument(document);
					paragraph.setParagraphContent(paragraphContent);
					paragraph.setParagraphSequence(0);
					paragraph.setUpdateTime(new Date());

					paragraphDao.saveOrUpdate(paragraph);
				}
			}
		}

		return document;
	}

	public List<Document> findAllDocumentsOfImageMain(Integer imageMainId) {
		DocumentQuery query = new DocumentQuery();
		query.setImageMainId(imageMainId);
		query.setSidx(new String[] { "documentSequence" });
		query.setSord(new String[] { "asc" });

		return documentDao.findByQueryModel(query);
	}

	public List<Document> findSubmittedDocuments() {
		DocumentQuery query = new DocumentQuery();
		query.setStatus(ModelStatusEnum.SUBMITTED.getItemCode());

		return documentDao.findByQueryModel(query);
	}

	public void remove(Integer documentId) {
		Document document = documentDao.getExists(documentId);
		assertEntityCanBeRemoved(document);
		documentDao.delete(document);
	}

	/**
	 * 调整文章次序
	 * 
	 * @param documentId
	 * @param direction
	 */
	public void adjustSequence(Integer documentId, String direction) {
		Document document = documentDao.getExists(documentId);
		Integer sequence = document.getDocumentSequence();

		DocumentQuery query = new DocumentQuery();
		query.setImageMainId(document.getImageMainId());
		query.setSidx(new String[] { "documentSequence" });
		if ("up".equalsIgnoreCase(direction)) {
			query.setSequenceLessThan(sequence);
			query.setSord(new String[] { "desc" });
		} else {
			query.setSequenceGreatThan(sequence);
			query.setSord(new String[] { "asc" });
		}

		List<Document> documents = documentDao.findByQueryModel(query);
		if (EntityUtils.isEmpty(documents))
			return;

		Document target = documents.get(0);
		Integer targetSequence = target.getDocumentSequence();

		document.setDocumentSequence(targetSequence);
		documentDao.saveOrUpdate(document);

		target.setDocumentSequence(sequence);
		documentDao.saveOrUpdate(target);
	}

	public void publish(Integer documentId) {
		Document document = documentDao.getExists(documentId);

		try {
			// 日期专题
			if (EntityUtils.notEmpty(document.getDate())) {
				DateDocument dateDocument = new DateDocument();
				dateDocument.setPublishDate(DateTimeUtils.parse(document.getDate(), "yyyy-MM-dd"));
				dateDocument.setDocument(document);
				dateDocumentDao.save(dateDocument);
			}

			// 人物专题
			if (EntityUtils.notEmpty(document.getPerson())) {
				String[] personNames = document.getPerson().split(",");
				for (String personName : personNames) {
					PersonDocument personDocument = new PersonDocument();
					personDocument.setDocument(document);
					personDocument.setPerson(personDao.findByPersonName(personName));
					personDocumentDao.save(personDocument);
				}
			}

			// 地点专题
			if (EntityUtils.notEmpty(document.getLocation())) {
				Location location = new Location();
				location.setLocationName(document.getLocation());
				location.setLocationLng(document.getLocationLong());
				location.setLocationLat(document.getLocationLat());
				location = locationDao.saveOrUpdate(location);
				LocationDocument locationDocument = new LocationDocument();
				locationDocument.setDocument(document);
				locationDocument.setLocation(location);
				locationDocumentDao.saveOrUpdate(locationDocument);

				document.setLocationId(location.getLocationId());
				documentDao.saveOrUpdate(document);
			}
		} catch (Exception e) {
			logger.error("发布文章失败", e);

			throw new RuntimeException(e);
		}
	}

	public void unpublish(Integer documentId) {
		Document document = documentDao.getExists(documentId);

		try {
			// 删除日期专题记录
			documentDao.execute("delete from DateDocument m where m.document.documentId = ?", documentId);

			// 删除人物专题记录
			documentDao.execute("delete from PersonDocument m where m.document.documentId = ?", documentId);

			// 删除地点专题记录
			documentDao.execute("delete from LocationDocument m where m.document.documentId = ?", documentId);
			documentDao.execute("delete from Location m where m.locationId = ?", document.getLocationId());
		} catch (Exception e) {
			logger.error("取消发布文章失败", e);

			throw new RuntimeException(e);
		}
	}
}
