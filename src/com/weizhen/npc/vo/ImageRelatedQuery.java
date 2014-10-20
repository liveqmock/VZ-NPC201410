package com.weizhen.npc.vo;

import com.chineseall.dams.common.paging.BaseQueryModel;
import com.chineseall.dams.common.paging.Expression;
import com.chineseall.dams.common.paging.OperatorType;

/**
 * 主题相关图片视频查询对象
 * @author y
 *
 */
public class ImageRelatedQuery extends BaseQueryModel<ImageRelatedQuery> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String imageRelatedTitle;
	private String imageRelatedDescription;
	
	@Expression(operatorType = OperatorType.LIKE)
	public String getImageRelatedTitle() {
		return imageRelatedTitle;
	}
	public void setImageRelatedTitle(String imageRelatedTitle) {
		this.imageRelatedTitle = imageRelatedTitle;
	}
	
	@Expression(operatorType = OperatorType.LIKE)
	public String getImageRelatedDescription() {
		return imageRelatedDescription;
	}
	public void setImageRelatedDescription(String imageRelatedDescription) {
		this.imageRelatedDescription = imageRelatedDescription;
	}
	
	

}
