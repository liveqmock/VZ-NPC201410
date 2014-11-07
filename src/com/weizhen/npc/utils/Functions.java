package com.weizhen.npc.utils;

/**
 * 实用功能
 * @author y
 *
 */
public class Functions {
	
	/**
	 * 将图片路径转为对应的大图或小图的路径
	 * @param imagePath 图片路径
	 * @param type 大图/小图
	 * @return
	 */
	public static String transImagePath(String imagePath, String type) {
		String splitter = "/";
		
		int index = imagePath.lastIndexOf(splitter) + 1;
		String fileName = imagePath.substring(index, imagePath.length());
		fileName = fileName.replace(".", "-" + type + ".");
		imagePath = imagePath.substring(0, index) + type + splitter + fileName;
		
		
		return imagePath;
	}
	
	private static final String[] materialTypes = new String[] {"article", "image", "video", "article"};
	
	/**
	 * 将相关资料类型转换为英文描述
	 * @param materialId 相关资料类型
	 * @return
	 */
	public static String transMaterialType(Integer materialId) {
		try {
			return materialTypes[materialId];
		} catch (Exception e) {
			return null;
		}
	}
	
	
	/**
	 * 格式化用户类型
	 * @param userType
	 * @return
	 */
	public static String formatUserType(String userType) {
		try {
			return UserTypeEnum.from(userType).getItemValue();
		} catch(Exception e) {
			return userType;
		}
	}
	
	public static void main(String[] args) {
		System.out.println(transImagePath("/Img/abc/01-01.jpg", "b"));
		System.out.println(transImagePath("/Img/abc/01-01.jpg", "m"));
	}
}
