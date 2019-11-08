package com.example.eurekaserviceRegister.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.eurekaserviceRegister.dto.RequestUserRegistration;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
//@RequestMapping("/mainservice")
public class EurekaservicesController {
	
private static final Log Logger= LogFactory.getLog(EurekaservicesController.class);

private String mapToJason(Object object) throws JsonProcessingException{
	ObjectMapper objectmapper=new ObjectMapper();
	objectmapper.setSerializationInclusion(Include.NON_NULL);
	
	return objectmapper.writeValueAsString(object);
	
}
	@Autowired
    RestTemplate restTemplate;
	
	 @Value 
	 ("${service.accountregistration.service}") 
	 private String getAllUser;
	 
	 @Value
	 ("${service.backupaccountregistration.service}")
	 private String backUpGetAllUser;
	 
	 
	
	 
	@HystrixCommand(fallbackMethod = "callGetServiceFallBack")
	@GetMapping(value="/getUserDetails")
	public String getRegisterationDetail()  {
		Logger.info("get method starts");
		HttpHeaders headers = new HttpHeaders();
	      headers.setContentType(MediaType.APPLICATION_JSON);
	      HttpEntity <String> entity = new HttpEntity<String>(headers);
	      
	      return restTemplate.exchange(getAllUser, HttpMethod.GET, entity, String.class).getBody();
	    		  //return throw new RuntimeException("404");
	   }
	
	
	
	  public String callGetServiceFallBack() {
	  Logger.info("Calling fall get back service failed " );
	  HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_JSON);
      HttpEntity <String> entity = new HttpEntity<String>(headers);
      
      return restTemplate.exchange(backUpGetAllUser, HttpMethod.GET, entity, String.class).getBody();
	  //String response="Fall back response get failed"; 
      		//return response;
	  }
	 
	 
	
	@PostMapping(value="/newUser")
	public String savingNewUser(@RequestBody RequestUserRegistration requestUserRegistration) {
		Logger.info("post method starts");
		HttpHeaders headers = new HttpHeaders();
	      headers.setContentType(MediaType.APPLICATION_JSON);
	      HttpEntity<RequestUserRegistration> entity = new HttpEntity<RequestUserRegistration>(requestUserRegistration,headers);
	      	return restTemplate.postForObject("http://localhost:8080/account/saveUser", entity, String.class);
	    
	      // restTemplate.exchange("http://localhost:8080/account/saveUser", HttpMethod.POST, entity, String.class).getBody();
	}
	 
		
	}


