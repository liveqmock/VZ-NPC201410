package com.weizhen.npc.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.weizhen.npc.exception.IllegalStatusException;
import com.weizhen.npc.utils.ModelStatusEnum;

/**
 * service基类
 * 
 * @author y
 *
 */
public abstract class BaseService {
	protected Logger logger = LoggerFactory.getLogger(getClass());

	public void assertEntityCanBeRemoved(StatusEntity entity) {
		if (entity.getCheckPublish() == 0)
			throw new IllegalStatusException("内容不允许被删除");

		if (ModelStatusEnum.PUBLISHED.getItemCode().equalsIgnoreCase(entity.getStatus()))
			throw new IllegalStatusException("内容不允许被删除");
	}
}
