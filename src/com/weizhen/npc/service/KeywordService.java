package com.weizhen.npc.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fylaw.utils.EntityUtils;
import com.weizhen.npc.base.BaseService;
import com.weizhen.npc.dao.PersonDAO;

/**
 * 人物主题
 * 
 * @author y
 * 
 */
@Service
public class KeywordService extends BaseService {

	@Autowired
	private PersonDAO personDao;

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> statKeywords() {
		StringBuffer sb = new StringBuffer("");
		sb.append(" select keyword from image_main where keyword is not null ");
		sb.append(" union select keyword from image_related where keyword is not null ");
		sb.append(" union select keyword from document where keyword is not null ");
		List<String> keywords = (List<String>) personDao.findBySql(sb
				.toString());

		Map<String, Integer> tmp = new HashMap<String, Integer>();
		for (String keyword : keywords) {
			if (EntityUtils.notEmpty(keyword)) {
				for (String name : keyword.split(",")) {
					Integer cnt = tmp.get(name);
					if (null == cnt)
						cnt = 0;
					tmp.put(name, cnt + 1);
				}
			}
		}

		List<Map<String, Object>> res = new ArrayList<Map<String, Object>>();
		for (String key : tmp.keySet()) {
			Map<String, Object> value = new HashMap<String, Object>();
			value.put("keyword", key);
			value.put("cnt", tmp.get(key));
			res.add(value);
		}

		return res;
	}
}
