package com.adminportal.Application.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adminportal.Application.Constants.Constants;
import com.adminportal.Application.Constants.CustomCheckedException;
import com.adminportal.Application.model.Organisation;
import com.adminportal.Application.repository.BrandRepository;

@Service
public class BrandService {
	
	@Autowired
	private BrandRepository brandRepository;
	
	public JSONArray getOrganisationType(){
		JSONArray resArray = new JSONArray();
		List<Object> list = brandRepository.getOrgType();
		System.out.println(list.size());
		Iterator<Object> itr = list.iterator();
		while(itr.hasNext()) {
			Object[] obj = (Object[]) itr.next();
			JSONObject res = new JSONObject();
			res.put("id", Integer.parseInt(String.valueOf(obj[0])));
			res.put("type", String.valueOf(obj[1]));
			resArray.put(res);
		}
		System.out.println(resArray.toString());
		return resArray;
	}

	@Transactional(rollbackFor = CustomCheckedException.class)
	public String addBrandDetails(JSONObject req) {
		String companyName = req.getString("companyName");
		int orgType = Constants.Brand_Org_Type;
		String panNo = req.getString("panNo");
		String gstnNo = req.getString("gstnNo");
		String startDate = req.getString("startDate");
		String addressLine1 = req.getString("addressLine1");
		String addressLine2 = req.getString("addressLine2");
		String city = req.getString("city");
		String state = req.getString("state");
		String pincode = req.getString("pincode");
		int userId = req.getInt("userId");

		if (companyName.isBlank() || companyName.isEmpty()) {
			return "Company name is cannot be Empty";
		} else if (panNo.isBlank() || panNo.isEmpty() || !Constants.isPanNumber(panNo)) {
			return "Pan Number is cannot be Empty or Not valid";
		} else if (gstnNo.isBlank() || gstnNo.isEmpty() || !Constants.isValidGST(gstnNo)) {
			return "Gst Number is cannot be Empty or Not valid";
		} else if (startDate.isBlank() || startDate.isEmpty() || !Constants.isValidDate(startDate)) {
			return "startDate is Not valid";
		} else if (addressLine1.isBlank() || addressLine1.isEmpty() || addressLine2.isBlank()
				|| addressLine2.isEmpty()) {
			return "addressLine1 or addressLine1 is cannot be Empty";
		} else if (city.isBlank() || city.isEmpty() || !Constants.isAlphabetic(city)) {
			return "City is cannot be Empty or Not valid";
		} else if (state.isBlank() || state.isEmpty() || !Constants.isAlphabetic(state)) {
			return "State is cannot be Empty or Not valid";
		} else if (pincode.isBlank() || pincode.isEmpty() || !Constants.isNumeric(pincode)) {
			return "Pincode is cannot be Empty or Not valid";
		} else {
			Organisation organisation = new Organisation();
			organisation.setOrgName(companyName);
			organisation.setOrgTypeId(orgType);
			organisation.setPanNo(panNo);
			organisation.setStartDate(LocalDate.parse(startDate));
			organisation.setActiveInd(1);
			organisation.setCreatedBy(userId);
			organisation.setCreatedOn(LocalDateTime.now());
			organisation.setUpdatedBy(userId);
			organisation.setUpdatedOn(LocalDateTime.now());
			brandRepository.save(organisation);
			
			
			return null;
		}

	}

}
