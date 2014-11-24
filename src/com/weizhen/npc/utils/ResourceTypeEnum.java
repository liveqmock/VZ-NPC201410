package com.weizhen.npc.utils;

/**
 * 资源类型
 * 
 * @author y
 *
 */
public enum ResourceTypeEnum {
	IMAGEMAIN("ImageMain", "主题"), IMAGERELATED("ImageRelated", "相关资料"), DOCUMENT("Document", "文章");

	private String itemCode;
	private String itemValue;

	private ResourceTypeEnum(String itemCode, String itemValue) {
		this.itemCode = itemCode;
		this.itemValue = itemValue;
	}

	public static ResourceTypeEnum from(String itemCode) {
		for (ResourceTypeEnum e : ResourceTypeEnum.values())
			if (e.getItemCode().equals(itemCode))
				return e;

		throw new IllegalArgumentException("无法识别的资源类型");
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

	public Boolean eq(String value) {
		return this.itemCode.equalsIgnoreCase(value) || this.itemValue.equalsIgnoreCase(value);
	}
}
