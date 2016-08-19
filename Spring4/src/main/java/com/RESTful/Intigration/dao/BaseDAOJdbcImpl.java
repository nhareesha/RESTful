package com.RESTful.Intigration.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.RowMapper;

import com.RESTful.Intigration.beans.EnquiryResponse;

public class BaseDAOJdbcImpl implements BaseDAO {
	
	private JdbcTemplate jdbcTemplate;
	
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}


	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	/*
	 * This method insets the record in to table
	 */
	public void insertRecord(final EnquiryResponse e) {
		String iquery;
		try{
			if(e!=null){
				//iquery= "INSERT INTO enquiry (name,comment,id) VALUES ('"+e.getName()+"','"+e.getComment()+"','"+e.getResposeId()+"')";
				
				iquery= "INSERT INTO enquiry (name,comment,id) VALUES (?,?,?)";
				//int result=jdbcTemplate.update(iquery);
				
				//jdbcTemplate with PreparedStatement 
			Boolean result=jdbcTemplate.execute(iquery,new PreparedStatementCallback<Boolean>(){

					public Boolean doInPreparedStatement(PreparedStatement ps)
							throws SQLException, DataAccessException {
						ps.setString(1, e.getName());
						ps.setString(2, e.getComment());
						ps.setString(3, e.getResposeId());
						return ps.execute();
					}		
				});
				System.out.println("Result of result"+result);
			}else{
				System.out.println("Enquiry object is null");
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	/*
	 * This method extracts the result set using RowMapper
	 */
	public List<EnquiryResponse> getAllEnquiresRowMapper(){
		
		String sql = "Select *from enquiry";
		return jdbcTemplate.query(sql, new RowMapper<EnquiryResponse>(){

			public EnquiryResponse mapRow(ResultSet rs, int rnumber)
					throws SQLException {
				EnquiryResponse er = new EnquiryResponse();
				er.setName(rs.getString("name"));
				er.setComment(rs.getString("comment"));
				er.setResposeId(rs.getString("id"));
				return er;
			}});
	}

}
