package com.RESTful.Intigration.client;

import java.util.Arrays;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import com.RESTful.Intigration.beans.Enquiry;
import com.RESTful.Intigration.beans.EnquiryResponse;
import com.RESTful.Intigration.beans.EnquiryWrapper;

/**
 * IntigrationRESTClient program consumes the webservices using Spring core
 * class RestTeplate
 * 
 * @author Hareesha D
 * @version 1.0
 * 
 */
public class IntigrationRESTClient {

	private RestTemplate client;

	/**
	 * addEnquiry method consumes the service that creates request using Http
	 * GET method and results a string.
	 * 
	 * @param
	 * @return Nothing
	 */
	public void addEnquiry() {
		client = new RestTemplate();
		String uri = "http://localhost:8080/Spring4/enquiry?name=hareesha&comment=hi";
		String res = client.getForObject(uri, String.class);
		System.out.println(res);
	}

	/**
	 * postEnquiry method consumes the RESTful service which creates a comments
	 * and responds back with the uniqueId of the enquiry raised.
	 * 
	 * @param Nothing
	 * @return Nothing
	 */
	public void postEnquiry() {
		
		client = new RestTemplate();

		// RequestObject
		EnquiryWrapper wrap = new EnquiryWrapper();
		Enquiry enq = new Enquiry();
		enq.setComment("Calling from RestClient");
		enq.setName("Andy");
		enq.setPhone("123-123-1234");
		enq.setType("NEW");
		wrap.setEnquiry(enq);

		// Request url
		String url = "http://localhost:8080/Spring4/enquiry";

		// Setting custom headers required to RestTemplate
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		// Create RequestEntity and set custom headers and requestObject
		HttpEntity<EnquiryWrapper> reqEntity = new HttpEntity<EnquiryWrapper>(
				wrap, headers);

		// Using custom HttpHeaders with RestTemplate -- Use exchange method of
		// RestTemplate
		ResponseEntity<EnquiryResponse> res = client.exchange(url,
				HttpMethod.POST, reqEntity, EnquiryResponse.class);

		System.out.println("************Extracting response object**********");
		System.out.println(res.getStatusCode());
		System.out.println(res.getBody().getResposeId());
		System.out.println(res.getHeaders());

	}

	public static void main(String[] args) {
		IntigrationRESTClient obj = new IntigrationRESTClient();
		obj.addEnquiry();
		obj.postEnquiry();
	}

}
