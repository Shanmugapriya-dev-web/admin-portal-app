package com.adminportal.Application.model;

public enum HttpResponseStatusCode{
	
	SUCCESS(new ApiResponse<>("Success",null, 200, "Success")),
	NOT_FOUND(new ApiResponse<>("Failed", null, 404, "Not Found")),
	FAILED(new ApiResponse<>("Failed", null, 401, null));

	private ApiResponse<?> apiResponse;
	
	HttpResponseStatusCode(ApiResponse<?> apiResponse) {
		this.apiResponse = apiResponse;
	}

	public ApiResponse<?> getApiResponse() {
		return apiResponse;
	}

	public void setApiResponse(ApiResponse<?> apiResponse) {
		this.apiResponse = apiResponse;
	}
	
	@SuppressWarnings("unchecked")
    public <T> void setData(T data) {
        ((ApiResponse<T>) this.apiResponse).setResp(data);
    }
	
	public void setErrorMessage(String message) {
		this.apiResponse.setErrorMessage(message);
	}
}
