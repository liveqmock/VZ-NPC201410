package com.weizhen.npc.utils;

/**
 * 操作枚举
 * @author y
 *
 */
public enum OperationEnum {
	SUBMIT("submit", "提交"),
	REJECT("reject", "拒绝"),
	RATIFY("ratify", "通过"),
	PUBLISH("publish", "发布"),
	REMOVE("remove", "删除");
	
	private String itemCode;
	private String itemValue;
	
	private OperationEnum(String itemCode, String itemValue) {
		this.itemCode = itemCode;
		this.itemValue = itemValue;
	}
	
	public static OperationEnum from(String itemCode) {
		for(OperationEnum e : OperationEnum.values())
			if(e.getItemCode().equals(itemCode))
				return e;
		
		throw new IllegalArgumentException("无法识别的操作类型");
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
