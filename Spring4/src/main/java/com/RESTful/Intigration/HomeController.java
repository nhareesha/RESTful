package com.RESTful.Intigration;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.RESTful.Intigration.CustomExceptions.CustomException;
import com.RESTful.Intigration.CustomExceptions.ErrorResponse;
import com.RESTful.Intigration.beans.Enquiry;
import com.RESTful.Intigration.beans.EnquiryResponse;
import com.RESTful.Intigration.beans.EnquiryWrapper;

@RestController
public class HomeController {
	
	@Autowired
	private EnquiryResponse response;
	
	@Autowired
	private ErrorResponse error;
	
	@RequestMapping(value="/enquiry", method=RequestMethod.GET, produces="aplication/json")
	public String addEnquiry( @RequestParam(value="name", required=true)String name,
							  @RequestParam(value="comment",required=true)String comment){
		return name+comment;
	}
	
	@RequestMapping(value="/enquiry", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE, 
			consumes=MediaType.APPLICATION_JSON_VALUE)
	public EnquiryResponse postEnquiry(@RequestBody EnquiryWrapper enqWrap) throws CustomException{
		Enquiry enq=null;
		String responseId = UUID.randomUUID().toString();//creating unique id
		if(enqWrap!=null){
			enq = enqWrap.getEnquiry();
			if(enq!=null){
				response.setComment(enq.getComment());
				response.setName(enq.getName());
				response.setResposeId(responseId);
				//error=null;
				//error.getErrorCode();
			}else
			{
				throw new CustomException("Enquiry object not present");
			}
		}else
		{
			throw new CustomException("Enquiry object not present");
		}
		return response;
		
	}
	
	/*
	 * Exception Handler for CustomException
	 */
	@ExceptionHandler(CustomException.class)
	public ErrorResponse errorHandler(Exception ex){
		
		error.setErrorCode(HttpStatus.PRECONDITION_FAILED.value());
		error.setErrorMsg(ex.getMessage());
		return error;
		
	}
	
	/*
	 * This method is used to create a fallback handler that takes care of requests that do not match any
	 * handler method.
	 */
	@RequestMapping("*")
	public String fallbackHandler(){
		return "Requested resource do not exist";
	}

}
