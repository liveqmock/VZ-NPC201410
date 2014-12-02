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
		if (ModelStatusEnum.SAVED.eq(entity.getStatus()) || ModelStatusEnum.REJECTED.eq(entity.getStatus()))
			return;

		throw new IllegalStatusException("内容不允许被删除");
	}
}
