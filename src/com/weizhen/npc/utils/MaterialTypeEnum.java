package com.weizhen.npc.utils;

/**
 * 相关资料类型
 * @author y
 *
 */
public enum MaterialTypeEnum {
	IMAGE(1, "图片"),
	VIDEO(2, "视频"),
	ARTICLE(4, "文章");
	
	private Integer itemCode;
	private String itemValue;
	
	private MaterialTypeEnum(Integer itemCode, String itemValue) {
		this.itemCode = itemCode;
		this.itemValue = itemValue;
	}
	
	public static MaterialTypeEnum from(Integer itemCode) {
		for(MaterialTypeEnum e : MaterialTypeEnum.values())
			if(e.getItemCode() == itemCode.intValue()) 
				return e;
		
		throw new IllegalArgumentException("无法识别的相关资料类型");
	}

	public Integer getItemCode() {
		return itemCode;
	}

	public void setItemCode(Integer itemCode) {
		this.itemCode = itemCode;
	}

	public String getItemValue() {
		return itemValue;
	}

	public void setItemValue(String itemValue) {
		this.itemValue = itemValue;
	}	
}
