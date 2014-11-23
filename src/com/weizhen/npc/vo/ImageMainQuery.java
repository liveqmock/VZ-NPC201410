package com.weizhen.npc.vo;

import com.chineseall.dams.common.paging.BaseQueryModel;
import com.chineseall.dams.common.paging.Expression;
import com.chineseall.dams.common.paging.OperatorType;

/**
 * 界别主题查询对象
 * 
 * @author y
 *
 */
public class ImageMainQuery extends BaseQueryModel<ImageMainQuery> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String imageMainTitle;
	private String imageMainDescription;

	private String status;
	private Integer congressId;
	private Integer checkPublish;

	@Expression(operatorType = OperatorType.LIKE)
	public String getImageMainTitle() {
		return imageMainTitle;
	}

	public void setImageMainTitle(String imageMainTitle) {
		this.imageMainTitle = imageMainTitle;
	}

	@Expression(operatorType = OperatorType.LIKE)
	public String getImageMainDescription() {
		return imageMainDescription;
	}

	public void setImageMainDescription(String imageMainDescription) {
		this.imageMainDescription = imageMainDescription;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getCongressId() {
		return congressId;
	}

	public void setCongressId(Integer congressId) {
		this.congressId = congressId;
	}

	public Integer getCheckPublish() {
		return checkPublish;
	}

	public void setCheckPublish(Integer checkPublish) {
		this.checkPublish = checkPublish;
	}

}
