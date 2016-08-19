package com.RESTful.Intigration.beans;

import com.google.gson.Gson;

public class Test {

	public static void main(String[] args) {
		EnquiryWrapper wrap = new EnquiryWrapper();
		Enquiry t = new Enquiry();
		wrap.setEnquiry(t);
		Gson gson = new Gson();
		t.setComment("c");
	
		t.setName("hareesha");
		t.setPhone("6023301250");
		t.setType("query");
		System.out.println(gson.toJson(wrap));

	}

}
