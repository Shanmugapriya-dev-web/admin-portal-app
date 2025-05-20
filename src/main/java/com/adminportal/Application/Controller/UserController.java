package com.adminportal.Application.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adminportal.Application.Service.UserService;
import com.adminportal.Application.model.ApiResponse;
import com.adminportal.Application.model.HttpResponseStatusCode;
import com.adminportal.Application.model.UserRequestDTO;
import com.adminportal.Application.model.UserResponseDTO;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	UserService userService;
	
	@PostMapping("/login")
	public  ApiResponse<?> validateUser(@RequestBody UserRequestDTO request) {
		System.out.println("request : "+request.toString());
		UserResponseDTO response = (UserResponseDTO) userService.validateUser(request);
		if(response!=null && response.getUserId()!=0) {
			ApiResponse<Object> resp = (ApiResponse<Object>) HttpResponseStatusCode.SUCCESS.getApiResponse();
			resp.setResp(response);
			return resp; 
		}
		return HttpResponseStatusCode.NOT_FOUND.getApiResponse();		
	}
}
