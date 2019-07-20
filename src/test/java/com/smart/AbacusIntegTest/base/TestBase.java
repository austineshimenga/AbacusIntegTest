package com.smart.AbacusIntegTest.base;

import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestBase {
	
	public static RequestSpecification httpRequest;
	public static Response  response;
	
	public  Logger logger;
	
	@BeforeClass
	public void setup() {
		logger = (Logger)LoggerFactory.getLogger(this.getClass());
		logger.setLevel(Level.INFO);
		//RestAssured.baseURI = "https://data.smartapplicationsgroup.com/api/integcombo";
		
		
	}
	

}
