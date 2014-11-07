package com.weizhen.npc.utils;

/**
 * 支付单位
 * @author y
 *
 */
public enum UserTypeEnum {
	AUDITOR("auditor", "审核员"),
	EDITOR("editor", "录入员"),
	MANAGER("manager", "管理员");
	
	private String itemCode;
	private String itemValue;
	
	private UserTypeEnum(String itemCode, String itemValue) {
		this.itemCode = itemCode;
		this.itemValue = itemValue;
	}
	
	public static UserTypeEnum from(String itemCode) {
		for(UserTypeEnum e : UserTypeEnum.values())
			if(e.getItemCode().equals(itemCode))
				return e;
		
		throw new IllegalArgumentException("无法识别的用户类型");
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
