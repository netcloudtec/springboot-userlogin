package com.hsbc.userlogin.response;

/**
 * 函数的返回值
 * 
 */
public class ReturnValue {
	private int code;
	private String message;
	private Object data;

	public ReturnValue() {
	}

	public ReturnValue(int code, String message, Object data) {
		this.code = code;
		this.message = message;
		this.data = data;
	}

	public ReturnValue(int code, String message) {
		this(code, message, "");
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
