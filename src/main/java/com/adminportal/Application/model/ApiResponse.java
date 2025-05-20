package com.adminportal.Application.model;

public class ApiResponse<T> {
	private String status;
	private T resp;
	private int errorCode;
	private String errorMessage;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public T getResp() {
		return resp;
	}

	public void setResp(T resp) {
		this.resp = resp;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public ApiResponse(String status, T resp, int errorCode, String errorMessage) {
		super();
		this.status = status;
		this.resp = resp;
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
	
	@Override
	public String toString() {
		return "ApiResponse [status=" + status + ", resp=" + resp + ", errorCode=" + errorCode + ", errorMessage="
				+ errorMessage + "]";
	}

}
