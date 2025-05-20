package com.adminportal.Application.Constants;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

public class Constants {
	
	public static final int Dealer_Org_Type = 1;
	public static final int Seller_Org_Type = 2;
	public static final int Brand_Org_Type = 3;
	public static final int Vendor_Org_Type = 4;
	
	public static boolean isValidDate(String dateStr) {
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            LocalDate.parse(dateStr, dateTimeFormatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
	
	
	public static boolean isAlphabetic(String str) {
        return str != null && str.matches("[a-zA-Z]+");
    }

    public static boolean isNumeric(String str) {
        return str != null && str.matches("\\d+");
    }

    public static boolean isAlphanumeric(String str) {
        return str != null && str.matches("(?=.*[a-zA-Z])(?=.*\\d)[a-zA-Z\\d]+");
    }
    
    public static boolean isPanNumber(String pan) {
    	 Pattern pattern = Pattern.compile("^[A-Z]{5}[0-9]{4}[A-Z]$");
    	 return pattern.matcher(pan).matches();
    }
    
    public static boolean isValidGST(String gstNumber) {
        String regex = "^[0-9]{2}[A-Z]{5}[0-9]{4}[A-Z]{1}[1-9A-Z]{1}Z[0-9A-Z]{1}$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(gstNumber).matches();
    }

    public static boolean isValidEmail(String email) {
    	return Pattern.compile("\"^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$\"").matcher(email).matches();
    }
}
