package com.weizhen.npc.utils;

/**
 * 状态类型
 * @author y
 *
 */
public enum ModelStatusEnum {
	SUBMITTED("submitted", "已提交"),
	RATIFIED("ratified", "已通过"),
	REJECTED("rejected", "已拒绝"),
	PUBLISHED("published", "已发布");
	
	private String itemCode;
	private String itemValue;
	
	private ModelStatusEnum(String itemCode, String itemValue) {
		this.itemCode = itemCode;
		this.itemValue = itemValue;
	}
	
	public static ModelStatusEnum from(String itemCode) {
		for(ModelStatusEnum e : ModelStatusEnum.values())
			if(e.getItemCode().equals(itemCode))
				return e;
		
		throw new IllegalArgumentException("无法识别的状态类型");
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getItemValue() {
		return itemValue;
	}

	public void setItemValue(String itemValue) {
		this.itemValue = itemValue;
	}	
}
