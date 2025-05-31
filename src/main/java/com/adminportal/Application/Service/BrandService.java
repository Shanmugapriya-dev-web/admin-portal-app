package com.adminportal.Application.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.adminportal.Application.Constants.Constants;
import com.adminportal.Application.Constants.CustomCheckedException;
import com.adminportal.Application.model.Organisation;
import com.adminportal.Application.model.OrganisationAddress;
import com.adminportal.Application.model.OrganisationContact;
import com.adminportal.Application.model.OrganisationGst;
import com.adminportal.Application.repository.BrandRepository;
import com.adminportal.Application.repository.OrganisationAddressRepository;
import com.adminportal.Application.repository.OrganisationContactRepository;
import com.adminportal.Application.repository.OrganisationGstRepository;

@Service
public class BrandService {

	DecimalFormat df = new DecimalFormat("#.00");

	@Autowired
	private BrandRepository brandRepository;

	@Autowired
	private OrganisationAddressRepository addressRepository;

	@Autowired
	private OrganisationGstRepository gstRepository;

	@Autowired
	private OrganisationContactRepository contactRepository;

	@Autowired
	private UserService userService;

	public JSONArray getOrganisationType(int id) {
		JSONArray resArray = new JSONArray();
		List<Object> list = new ArrayList<Object>();
		if (id == 0)
			list = brandRepository.getOrgType();
		else
			list = brandRepository.getOrgTypeByType();
		System.out.println(list.size());
		Iterator<Object> itr = list.iterator();
		while (itr.hasNext()) {
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
	public String addBrandDetails(JSONArray jArr) {
		String companyName = "", panNo = "", mobileNo = "", email = "", gstnNo = "", startDate = "", addressLine1 = "",
				addressLine2 = "", city = "", state = "", pincode = "";
		for (int i = 0; i < jArr.length(); i++) {
			JSONObject req = jArr.getJSONObject(i);
			int userId = req.getInt("userId");
			System.out.println("request : " + req.toString());
			companyName = req.getString("companyName");
			panNo = req.getString("panNo");
			mobileNo = req.getString("mobileNo");
			email = req.getString("email");
			gstnNo = req.getString("gstnNo");
			startDate = req.getString("startDate");
			addressLine1 = req.has("addressLine1") ? req.getString("addressLine1")
					: req.has("address") ? req.getString("address") : "";
			addressLine2 = req.has("addressLine2") ? req.getString("addressLine2") : "";
			String avgPurchase = req.has("avgPurchase") ? req.getString("avgPurchase") : "0.00";
			String firstMonth = req.has("firstMonth") ? req.getString("firstMonth") : "";
			String firstmonthPurchase = req.has("firstmonthPurchase") ? req.getString("firstmonthPurchase") : "0.00";
			String secondMonth = req.has("secondMonth") ? req.getString("secondMonth") : "";
			String secondMonthPurchase = req.has("secondMonthPurchase") ? req.getString("secondMonthPurchase") : "0.00";
			String thirdMonth = req.has("thirdMonth") ? req.getString("thirdMonth") : "";
			String thirdmonthPurchase = req.has("thirdmonthPurchase") ? req.getString("thirdmonthPurchase") : "0.00";
			String fourthMonth = req.has("fourthMonth") ? req.getString("fourthMonth") : "";
			String fourthmonthPurchase = req.has("fourthmonthPurchase") ? req.getString("fourthmonthPurchase") : "0.00";
			String fifthMonth = req.has("fifthMonth") ? req.getString("fifthMonth") : "";
			String fifthmonthPurchase = req.has("fifthmonthPurchase") ? req.getString("fifthmonthPurchase") : "0.00";
			String sixthMonth = req.has("sixthMonth") ? req.getString("sixthMonth") : "";
			String sixthmonthPurchase = req.has("sixthmonthPurchase") ? req.getString("sixthmonthPurchase") : "0.00";
			String name = req.getString("name");
			city = req.getString("city");
			state = req.getString("state");
			pincode = req.getString("pincode");
			userId = req.getInt("userId");
			int orgType = req.has("orgType") ? req.getInt("orgType") : Constants.Brand_Org_Type;
			int roleId = orgType == Constants.Brand_Org_Type ? Constants.Anchor_Role
					: orgType == Constants.Seller_Org_Type ? Constants.Seller_Role
							: orgType == Constants.Dealer_Org_Type ? Constants.Anchor_Role : 0;

			if (companyName.isBlank() || companyName.isEmpty()) {
				return "Company name is cannot be Empty";
			} else if (panNo.isBlank() || panNo.isEmpty() || !Constants.isPanNumber(panNo)) {
				return "Pan Number is cannot be Empty or Not valid";
			} else if (gstnNo.isBlank() || gstnNo.isEmpty() || !Constants.isValidGST(gstnNo)) {
				return "Gst Number is cannot be Empty or Not valid";
			} else if (startDate.isBlank() || startDate.isEmpty() || !Constants.isValidDate(startDate)) {
				return "startDate is Not valid";
			} else if (orgType == Constants.Brand_Org_Type) {
				if (addressLine1.isBlank() || addressLine1.isEmpty() || addressLine2.isBlank()
						|| addressLine2.isEmpty()) {
					return "addressLine1 or addressLine1 is cannot be Empty";
				}
			} else if (city.isBlank() || city.isEmpty()) {
				return "City is cannot be Empty or Not valid";
			} else if (state.isBlank() || state.isEmpty()) {
				return "State is cannot be Empty or Not valid";
			} else if (pincode.isBlank() || pincode.isEmpty()) {
				return "Pincode is cannot be Empty or Not valid";
			} else {
				Optional<OrganisationContact> contactDetails = contactRepository.findByMobileNo(mobileNo);
				if (!contactDetails.isPresent()) {
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
					Organisation orgData = brandRepository.save(organisation);
					if (brandRepository.existsById(orgData.getOrgId())) {
						OrganisationAddress address = new OrganisationAddress();
						address.setOrgId(organisation.getOrgId());
						address.setAddLine1(addressLine1);
						address.setAddLine2(addressLine2);
						address.setCity(city);
						address.setState(state);
						address.setPincode(pincode);
						address.setActiveInd(1);
						address.setCreatedBy(userId);
						address.setCreatedOn(LocalDateTime.now());
						address.setUpdatedBy(userId);
						address.setUpdatedOn(LocalDateTime.now());
						OrganisationAddress orgAddData = addressRepository.save(address);
						if (addressRepository.existsById(orgAddData.getOrgAddId())) {
							OrganisationGst gst = new OrganisationGst();
							gst.setOrgId(organisation.getOrgId());
							gst.setGstNo(gstnNo);
							gst.setAverageBrandPurchase(new BigDecimal(avgPurchase));
							gst.setLastFirstMonth(firstMonth);
							gst.setLastFirstMonthPurchase(new BigDecimal(firstmonthPurchase));
							gst.setLastSecondMonth(secondMonth);
							gst.setLastSecondMonthPurchase(new BigDecimal(secondMonthPurchase));
							gst.setLastThirdMonth(thirdMonth);
							gst.setLastThirdMonthPurchase(new BigDecimal(thirdmonthPurchase));
							gst.setLastFourthMonth(fourthMonth);
							gst.setLastFourthMonthPurchase(new BigDecimal(fourthmonthPurchase));
							gst.setLastFifthMonth(fifthMonth);
							gst.setLastFifthMonthPurchase(new BigDecimal(fifthmonthPurchase));
							gst.setLastSixthMonth(sixthMonth);
							gst.setLastSixthMonthPurchase(new BigDecimal(sixthmonthPurchase));
							gst.setActiveInd(1);
							gst.setCreatedBy(userId);
							gst.setCreatedOn(LocalDateTime.now());
							gst.setUpdatedBy(userId);
							gst.setUpdatedOn(LocalDateTime.now());
							OrganisationGst data = gstRepository.save(gst);
							if (gstRepository.existsById(data.getOrgGstId())) {
								OrganisationContact contact = new OrganisationContact();
								contact.setOrgId(organisation.getOrgId());
								contact.setUserName(name);
								contact.setMobileNo(mobileNo);
								contact.setEmailId(email);
								contact.setActiveInd(1);
								contact.setCreatedBy(userId);
								contact.setCreatedOn(LocalDateTime.now());
								contact.setUpdatedBy(userId);
								contact.setUpdatedOn(LocalDateTime.now());
								OrganisationContact contactData = contactRepository.save(contact);
								if (contactRepository.existsById(contactData.getOrgContactId())) {
									JSONObject userReq = new JSONObject();
									userReq.put("userName", name);
									userReq.put("password", mobileNo);
									userReq.put("roleId", roleId);
									userReq.put("userId", userId);
									userService.addUser(userReq);
								}
								return "Organisation Details Added Successfully";
							} else {
								return "Organisation Gst Data Not Added";
							}
						} else {
							return "Organisation Address Not Added";
						}
					} else {
						return "Organisation Not Added";
					}
				} else {
					return "Mobile No Already Existed";
				}
			}
		}
		return null;
	}

	public JSONArray uploadBrandDataDetails(int brandId, int userId, int orgType, MultipartFile file)
			throws IOException {
		String companyName = "", gstNo = "", panNo = "", address = "", city = "", state = "", pincode = "", name = "",
				mobileNo = "", email = "", companyStartDate = "";
		String avgPurchase = "", firstMonth = "", firstmonthPurchase = "", secondMonth = "", secondMonthPurchase = "",
				thirdMonth = "", thirdmonthPurchase = "", fourthMonth = "", fourthmonthPurchase = "", fifthMonth = "",
				fifthmonthPurchase = "", sixthMonth = "", sixthmonthPurchase = "";
		String remarks = "";
		JSONArray respArr = new JSONArray();
		if (file.isEmpty()) {
			remarks = "Please upload a valid .xls file.";
		}
		System.out.println("brandId : " + brandId + ", userId : " + userId + " ,orgType : " + orgType);
		try (XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream())) {
			XSSFSheet sheet = workbook.getSheetAt(0);
			int firstRow = sheet.getFirstRowNum();
			int lastRow = sheet.getLastRowNum();
			Map<String, Object> columnNames = new HashMap<String, Object>();
			Row headerRow = sheet.getRow(firstRow);
			for (int i = 0; i < headerRow.getLastCellNum(); i++) {
				Cell cell = headerRow.getCell(i);
				columnNames.put(cell.getStringCellValue().trim(), i);
			}
			System.out.println(columnNames.toString());
			for (int i = firstRow + 1; i <= lastRow; i++) {
				Row row = sheet.getRow(i);
				int checkIndex = 0;
				for (Cell cell : row) {
					String cellValue = "";
					switch (cell.getCellType()) {
					case STRING:
						cellValue = cellValue + cell.getStringCellValue();
						break;
					case NUMERIC:
						if (DateUtil.isCellDateFormatted(cell)) {
							Date date = cell.getDateCellValue();
							cellValue = new SimpleDateFormat("yyyy-MM-dd").format(date);
						} else {
							cellValue = cellValue + cell.getNumericCellValue();
						}
						break;
					case BOOLEAN:
						cellValue = cellValue + cell.getBooleanCellValue();
						break;
					default:
						cellValue = cellValue + "";
					}
					if ((Integer) columnNames.get("Organisation name") == checkIndex)
						companyName = cellValue;
					if ((Integer) columnNames.get("Gst No") == checkIndex)
						gstNo = cellValue;
					if ((Integer) columnNames.get("Pan No") == checkIndex)
						panNo = cellValue;
					if ((Integer) columnNames.get("Address") == checkIndex)
						address = cellValue;
					if ((Integer) columnNames.get("City") == checkIndex)
						city = cellValue;
					if ((Integer) columnNames.get("State") == checkIndex)
						state = cellValue;
					if ((Integer) columnNames.get("Pincode") == checkIndex)
						pincode = cellValue;
					if ((Integer) columnNames.get("Person Name") == checkIndex)
						name = cellValue;
					if ((Integer) columnNames.get("Mobile No") == checkIndex)
						mobileNo = cellValue;
					if ((Integer) columnNames.get("Email") == checkIndex)
						email = cellValue;
					if ((Integer) columnNames.get("Company Start Date") == checkIndex)
						companyStartDate = cellValue;
					if ((Integer) columnNames.get("Average purchase value") == checkIndex)
						avgPurchase = cellValue;
					if ((Integer) columnNames.get("last first month") == checkIndex)
						firstMonth = cellValue;
					if ((Integer) columnNames.get("last first month purchase") == checkIndex)
						firstmonthPurchase = cellValue;
					if ((Integer) columnNames.get("last second month") == checkIndex)
						secondMonth = cellValue;
					if ((Integer) columnNames.get("last second month purchase") == checkIndex)
						secondMonthPurchase = cellValue;
					if ((Integer) columnNames.get("last third month") == checkIndex)
						thirdMonth = cellValue;
					if ((Integer) columnNames.get("last third month purchase") == checkIndex)
						thirdmonthPurchase = cellValue;
					if ((Integer) columnNames.get("last fourth month") == checkIndex)
						fourthMonth = cellValue;
					if ((Integer) columnNames.get("last fourth month purchase") == checkIndex)
						fourthmonthPurchase = cellValue;
					if ((Integer) columnNames.get("last fifth month") == checkIndex)
						fifthMonth = cellValue;
					if ((Integer) columnNames.get("last fifth month purchase") == checkIndex)
						fifthmonthPurchase = cellValue;
					if ((Integer) columnNames.get("last sixth month") == checkIndex)
						sixthMonth = cellValue;
					if ((Integer) columnNames.get("last sixth month purchase") == checkIndex)
						sixthmonthPurchase = cellValue;
					checkIndex++;
				}
				Optional<Organisation> existData = null;

				if (companyName.isBlank() || companyName.isEmpty()) {
					remarks = remarks + "Company name is cannot be Empty\n";
				} else {
					existData = brandRepository.findByCompanyName(companyName);
					if (existData.isPresent()) {
						remarks = remarks + "Company Name Already Existed\n";
					}
				}
				if (panNo.isBlank() || panNo.isEmpty() || !Constants.isPanNumber(panNo)) {
					remarks = remarks + "Pan Number is cannot be Empty or Not valid\n";
				}
				if (gstNo.isBlank() || gstNo.isEmpty() || !Constants.isValidGST(gstNo)) {
					remarks = remarks + "Gst Number is cannot be Empty or Not valid\n";
				}
				if (name.isBlank() || name.isEmpty()) {
					remarks = remarks + "name is cannot be Empty\n";
				}
				Optional<OrganisationContact> existingContactData = null;
				String mobileStr = new BigDecimal(Double.parseDouble(mobileNo)).toPlainString();
				if (mobileNo.isBlank() || mobileNo.isEmpty() || !Constants.isNumeric(mobileStr)
						|| mobileStr.length() != 10) {
					remarks = remarks + "Mobile Number is cannot be Empty or Not valid\n";
				} else {
					existingContactData = contactRepository.findByMobileNo(mobileStr);
					if (existingContactData.isPresent()) {
						remarks = remarks + "Mobile Already Existed\n";
					}
				}
				if (email.isBlank() || email.isEmpty() || !Constants.isValidEmail(email)) {
					remarks = remarks + "Email is cannot be Empty or Not valid\n";
				} else {
					existingContactData = contactRepository.findByMailId(email);
					if (existingContactData.isPresent()) {
						remarks = remarks + "MailId Already Existed\n";
					}
				}
				try {
					LocalDate startDate = LocalDate.parse(companyStartDate);
					if (companyStartDate.isBlank() || companyStartDate.isEmpty()
							|| !startDate.isBefore(LocalDate.now())) {
						remarks = remarks + "Company Start Date is seems to be incorrect\n";
					}
				} catch (DateTimeParseException e) {
					remarks = remarks + "Company Start Date format is wrong\n";
				}
				if (avgPurchase.isBlank() || avgPurchase.isEmpty() || Double.parseDouble(avgPurchase) == 0) {
					remarks = remarks + "avgPurchase is Not valid or Zero\n";
				}
				if (firstMonth.isBlank() || firstMonth.isEmpty() || !Constants.isValidDate(firstMonth)) {
					remarks = remarks + "firstMonthDate is Not valid\n";
				}
				if (firstmonthPurchase.isBlank() || firstmonthPurchase.isEmpty()
						|| Double.parseDouble(firstmonthPurchase) == 0) {
					remarks = remarks + "firstmonthPurchase is Not valid or Zero\n";
				}
				if (secondMonth.isBlank() || secondMonth.isEmpty() || !Constants.isValidDate(secondMonth)) {
					remarks = remarks + "secondMonthDate is Not valid\n";
				}
				if (secondMonthPurchase.isBlank() || secondMonthPurchase.isEmpty()
						|| Double.parseDouble(secondMonthPurchase) == 0) {
					remarks = remarks + "secondMonthPurchase is Not valid or Zero\n";
				}
				if (thirdMonth.isBlank() || thirdMonth.isEmpty() || !Constants.isValidDate(thirdMonth)) {
					remarks = remarks + "firstMonthDate is Not valid\n";
				}
				if (thirdmonthPurchase.isBlank() || thirdmonthPurchase.isEmpty()
						|| Double.parseDouble(thirdmonthPurchase) == 0) {
					remarks = remarks + "thirdmonthPurchase is Not valid or Zero\n";
				}
				if (fourthMonth.isBlank() || fourthMonth.isEmpty() || !Constants.isValidDate(fourthMonth)) {
					remarks = remarks + "fourthMonthDate is Not valid\n";
				}
				if (fourthmonthPurchase.isBlank() || fourthmonthPurchase.isEmpty()
						|| Double.parseDouble(fourthmonthPurchase) == 0) {
					remarks = remarks + "fourthmonthPurchase is Not valid or Zero\n";
				}
				if (fifthMonth.isBlank() || fifthMonth.isEmpty() || !Constants.isValidDate(fifthMonth)) {
					remarks = remarks + "fifthMonthDate is Not valid\n";
				}
				if (fifthmonthPurchase.isBlank() || fifthmonthPurchase.isEmpty()
						|| Double.parseDouble(fifthmonthPurchase) == 0) {
					remarks = remarks + "fifthmonthPurchase is Not valid or Zero\n";
				}
				if (sixthMonth.isBlank() || sixthMonth.isEmpty() || !Constants.isValidDate(sixthMonth)) {
					remarks = remarks + "sixthMonthDate is Not valid\n";
				}
				if (sixthmonthPurchase.isBlank() || sixthmonthPurchase.isEmpty()
						|| Double.parseDouble(sixthmonthPurchase) == 0) {
					remarks = remarks + "sixthmonthPurchase is Not valid or Zero\n";
				}
				if (address.isBlank() || address.isEmpty()) {
					remarks = remarks + "addressLine1 or addressLine1 is cannot be Empty\n";
				}
				if (city.isBlank() || city.isEmpty()) {
					remarks = remarks + "City is cannot be Empty or Not valid\n";
				}
				if (state.isBlank() || state.isEmpty()) {
					remarks = remarks + "State is cannot be Empty or Not valid\n";
				}
				if (pincode.isBlank() || pincode.isEmpty()) {
					remarks = remarks + "Pincode is cannot be Empty or Not valid";
				}

				JSONObject respObj = new JSONObject();
				respObj.put("userId", userId);
				respObj.put("startDate", companyStartDate);
				respObj.put("companyName", companyName);
				respObj.put("panNo", panNo);
				respObj.put("gstnNo", gstNo);
				respObj.put("name", name);
				respObj.put("mobileNo", mobileStr);
				respObj.put("email", email);
				respObj.put("avgPurchase", avgPurchase);
				respObj.put("firstMonth", firstMonth);
				respObj.put("firstmonthPurchase", firstmonthPurchase);
				respObj.put("secondMonth", secondMonth);
				respObj.put("secondMonthPurchase", secondMonthPurchase);
				respObj.put("thirdMonth", thirdMonth);
				respObj.put("thirdmonthPurchase", thirdmonthPurchase);
				respObj.put("fourthMonth", fourthMonth);
				respObj.put("fourthmonthPurchase", fourthmonthPurchase);
				respObj.put("fifthMonth", fifthMonth);
				respObj.put("fifthmonthPurchase", fifthmonthPurchase);
				respObj.put("sixthMonth", sixthMonth);
				respObj.put("sixthmonthPurchase", sixthmonthPurchase);
				respObj.put("address", address);
				respObj.put("city", city);
				respObj.put("state", state);
				respObj.put("pincode", pincode);
				respObj.put("orgType", orgType);
				if (remarks.isEmpty()) {
					remarks = "Success";
				}
				respObj.put("remarks", remarks);
				respArr.put(respObj);
			}
		}
		return respArr;
	}

	public String downloadBrandDataDetailsTemplate() {
		return Constants.Template_File_Path;
	}

	public JSONArray listOfBrands() {
		JSONArray respArr = new JSONArray();
		List<Object> list = brandRepository.getBrandOrganisation();
		Iterator<Object> itr = list.iterator();
		while (itr.hasNext()) {
			Object[] obj = (Object[]) itr.next();
			JSONObject res = new JSONObject();
			res.put("id", Integer.parseInt(String.valueOf(obj[0])));
			res.put("name", String.valueOf(obj[1]));
			respArr.put(res);
		}
		System.out.println(respArr.toString());
		return respArr;
	}

}
