package com.adminportal.Application.Controller;

import java.io.IOException;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.adminportal.Application.Service.BrandService;
import com.adminportal.Application.model.ApiResponse;
import com.adminportal.Application.model.HttpResponseStatusCode;

@RestController
@RequestMapping("/api/brand")
public class BrandController {

	@Autowired
	private BrandService brandService;

	@GetMapping("/orgtype/{id}")
	public ApiResponse<?> getOrgType(@PathVariable int id) {
		ApiResponse<Object> apiResponse = (ApiResponse<Object>) HttpResponseStatusCode.NOT_FOUND.getApiResponse();
		JSONArray response = brandService.getOrganisationType(id);
		if (response.length() > 0) {
			apiResponse = (ApiResponse<Object>) HttpResponseStatusCode.SUCCESS.getApiResponse();
			apiResponse.setResp(response.toList());
		}
		return apiResponse;
	}

	@PostMapping("/add")
	public ApiResponse<?> addBrandDetails(@RequestBody String req) {
		JSONArray reqObj = new JSONArray(req);
		System.out.println(reqObj.toString());
		ApiResponse<Object> apiResponse = (ApiResponse<Object>) HttpResponseStatusCode.FAILED.getApiResponse();
		String resp = brandService.addBrandDetails(reqObj);
		if (resp.contains("Success")) {
			apiResponse = (ApiResponse<Object>) HttpResponseStatusCode.SUCCESS.getApiResponse();
			apiResponse.setResp(resp);
		} else {
			apiResponse.setErrorMessage(resp);
		}
		return apiResponse;
	}

	@PostMapping("/data/upload")
	public ApiResponse<?> uploadBrandDataDetails(@RequestParam("brandId") int brandId,
			@RequestParam("orgType") int orgType, @RequestParam("userId") int userId,
			@RequestParam("file") MultipartFile file) throws IOException {
		ApiResponse<Object> apiResponse = (ApiResponse<Object>) HttpResponseStatusCode.FAILED.getApiResponse();
		JSONArray resp = brandService.uploadBrandDataDetails(brandId, userId, orgType, file);
		apiResponse = (ApiResponse<Object>) HttpResponseStatusCode.SUCCESS.getApiResponse();
		apiResponse.setResp(resp.toList());
		return apiResponse;
	}

	@GetMapping("/data/upload/template")
	public ApiResponse<?> downloadBrandDataDetailsTemplate() {
		ApiResponse<Object> apiResponse = (ApiResponse<Object>) HttpResponseStatusCode.FAILED.getApiResponse();
		String resp = brandService.downloadBrandDataDetailsTemplate();
		if (resp != null) {
			apiResponse = (ApiResponse<Object>) HttpResponseStatusCode.SUCCESS.getApiResponse();
			apiResponse.setResp(resp);
		}
		return apiResponse;
	}

	@GetMapping("/list")
	public ApiResponse<?> listOfBrands() {
		ApiResponse<Object> apiResponse = (ApiResponse<Object>) HttpResponseStatusCode.FAILED.getApiResponse();
		JSONArray resp = brandService.listOfBrands();
		if (resp != null) {
			apiResponse = (ApiResponse<Object>) HttpResponseStatusCode.SUCCESS.getApiResponse();
			apiResponse.setResp(resp.toList());
		}
		return apiResponse;
	}

}
