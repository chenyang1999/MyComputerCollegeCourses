package com.moment.common.domain;

public class JsonResult {
	/**
	 * 1成功，0失败
	 */
	private int status;
	private String msg;
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
}
