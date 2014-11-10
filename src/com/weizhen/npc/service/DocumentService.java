package com.weizhen.npc.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chineseall.dams.common.paging.ConditionsAndNameValuePairs;
import com.chineseall.dams.common.paging.Paging;
import com.chineseall.dams.common.paging.PagingQueryResult;
import com.chineseall.dams.common.paging.SimpleQueryModelProcessor;
import com.fylaw.utils.EntityUtils;
import com.weizhen.npc.base.BaseService;
import com.weizhen.npc.dao.DocumentDAO;
import com.weizhen.npc.dao.ParagraphDAO;
import com.weizhen.npc.model.Document;
import com.weizhen.npc.model.Paragraph;
import com.weizhen.npc.utils.Constants;
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
	
	public List<Document> findByImageMainId(Integer imageMainId) {
		return documentDao.findByImageMainId(imageMainId);
	}
	
	public PagingQueryResult<Document> findByKeyword(String keyword, Paging paging) {
		ParagraphQuery query = new ParagraphQuery();
		query.setCombinationType("or");
		query.setParagraphContent(keyword);
		
		DocumentQuery document = new DocumentQuery();
		document.setDocumentTitle(keyword);
		query.setDocument(document);
		
		ConditionsAndNameValuePairs canv = SimpleQueryModelProcessor.gen(query, "m");
		String select = "select distinct m.document from Paragraph m " + canv.getWhereCondition(true);
		
		return documentDao.pagingQuery(select, canv.getNameValuePairs(), paging);
	}
	
	public Document addDocument(Document document, List<String> paragraphs) {
		
		document.setMaterialId(Constants.MATERIAL_TYPE_ARTICLE);
		document.setCheckPublish(1);
		document.setUpdateTime(new Date());
		document.setStatus(ModelStatusEnum.SUBMITTED.getItemCode());
		document = documentDao.saveOrUpdate(document);
		
		if(EntityUtils.notEmpty(paragraphs)) {
			for(String paragraphContent : paragraphs) {
				Paragraph paragraph = new Paragraph();
				paragraph.setDocument(document);
				paragraph.setParagraphContent(paragraphContent);
				paragraph.setParagraphSequence(0);
				paragraph.setUpdateTime(new Date());
				
				paragraphDao.saveOrUpdate(paragraph);
			}
		}
		
		return document;
	}
}
