package com.weizhen.npc.vo;

import java.io.Serializable;

/**
 * 请求响应
 * 
 * @author y
 *
 */
public class Response implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String msg;
	private Boolean success = true;
	private Object data;

	private Response() {

	}

	public static Response success(Object data) {
		Response response = new Response();
		response.setData(data);

		return response;
	}

	public static Response error(String msg) {
		Response response = new Response();
		response.setSuccess(false);
		response.setMsg(msg);

		return response;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
