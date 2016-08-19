package com.RESTful.Intigration.dao;

import org.springframework.transaction.annotation.Transactional;

import com.RESTful.Intigration.beans.EnquiryResponse;

public interface BaseDAO {
	@Transactional
	public void insertRecord(EnquiryResponse e);
}
