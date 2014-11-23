package com.weizhen.npc.vo;

import java.io.Serializable;

/**
 * 请求响应
 * 
 * @author y
 *
 */
public class Response<E> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String msg;
	private Boolean success = true;
	private E data;

	private Response() {
		
	}
	
	public Response(E data) {
		this.setData(data);
	}

	public static Response<String> error(String msg) {
		Response<String> response = new Response<String>();
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

	public E getData() {
		return data;
	}

	public void setData(E data) {
		this.data = data;
	}

}
