package com.adminportal.Application.Controller;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adminportal.Application.Service.UserService;
import com.adminportal.Application.model.ApiResponse;
import com.adminportal.Application.model.HttpResponseStatusCode;
import com.adminportal.Application.model.UserRequestDTO;
import com.adminportal.Application.model.UserResponseDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {
	private static final Logger LOGGER = LogManager.getLogger(UserController.class);

	@Autowired
	UserService userService;
	
	@PostMapping("/login")
	public  ApiResponse<?> validateUser(@Valid @RequestBody UserRequestDTO request) {
		LOGGER.info("request : "+request.toString());
		UserResponseDTO response = (UserResponseDTO) userService.validateUser(request);
		ApiResponse<Object> resp = (ApiResponse<Object>) HttpResponseStatusCode.FAILED.getApiResponse();
		if(response!=null && response.getUserId()!=0) {
			resp = (ApiResponse<Object>) HttpResponseStatusCode.SUCCESS.getApiResponse();
			resp.setResp(response);
		}
		return resp; 
	}
	
	@PostMapping("/add")
	public ApiResponse<?> addUser(@Valid @RequestBody JSONObject jObject){
		LOGGER.info("request : "+jObject.toString());
		ApiResponse<Object> resp = (ApiResponse<Object>) HttpResponseStatusCode.FAILED.getApiResponse();
		String response = userService.addUser(jObject);
		if(response.contains("Success")) {
			resp = (ApiResponse<Object>) HttpResponseStatusCode.SUCCESS.getApiResponse();
			resp.setResp(response);
		}
		return resp; 
	}
}
