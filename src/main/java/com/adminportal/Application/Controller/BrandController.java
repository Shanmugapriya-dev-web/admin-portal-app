package com.adminportal.Application.Controller;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adminportal.Application.Service.BrandService;
import com.adminportal.Application.model.ApiResponse;
import com.adminportal.Application.model.HttpResponseStatusCode;

@RestController
@RequestMapping("/api/brand")
public class BrandController {

	@Autowired
	private BrandService brandService;
	
	@GetMapping("/orgtype")
	public ApiResponse<?> getOrgType(){
		ApiResponse<Object> apiResponse = (ApiResponse<Object>) HttpResponseStatusCode.NOT_FOUND.getApiResponse();
		JSONArray response = brandService.getOrganisationType();
		if(response.length()>0) {
			apiResponse = (ApiResponse<Object>) HttpResponseStatusCode.SUCCESS.getApiResponse();
			apiResponse.setResp(response.toList());
		}
		return apiResponse;
	}
	
	@PostMapping("/add")
	public ApiResponse<?> addBrandDetails(@RequestBody JSONObject req) {
		ApiResponse<Object> apiResponse = (ApiResponse<Object>) HttpResponseStatusCode.FAILED.getApiResponse();
		String resp = brandService.addBrandDetails(req);
		if(resp.contains("Success")) {
			apiResponse = (ApiResponse<Object>) HttpResponseStatusCode.SUCCESS.getApiResponse();
			apiResponse.setResp(resp);
		}else {
			apiResponse.setErrorMessage(resp);
		}
		return apiResponse;
	}
}
