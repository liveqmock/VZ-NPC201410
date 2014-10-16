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
	
	public static void main(String[] args) {
		System.out.println(transImagePath("/Img/abc/01-01.jpg", "b"));
		System.out.println(transImagePath("/Img/abc/01-01.jpg", "m"));
	}
}
