package com.weizhen.npc.exception;

/**
 * 系统错误基类
 * @author y
 *
 */
public abstract class NPCException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public NPCException(String message) {
		super(message);
	}

}
