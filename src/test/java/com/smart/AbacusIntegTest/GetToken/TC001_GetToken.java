package com.smart.AbacusIntegTest.GetToken;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.smart.AbacusIntegTest.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;


public class TC001_GetToken extends TestBase {
	
	public String token;

	@BeforeClass 
	void getTokenfromSmart() throws InterruptedException {
		
		logger.info("*************** Started TC001_Get_token_from_Smart **************");
		
		RestAssured.baseURI = "https://data.smartapplicationsgroup.com/api/integcombo/oauth";
		httpRequest = RestAssured.given();
		httpRequest.header("Content-Type", "application/json");
		httpRequest.header("X-Gravitee-API-Key", "5790e360-7d1f-4748-85a3-27c7d8eed0ac");
		response = httpRequest.request(Method.POST,"/token?client_id=85755756501837565738264759573647283656312443&client_secret=PRUDJFDFDHF83234HFDGFDFD83231498NVFJDFGDSFVJDSFYGRYT13483JVHSGFDDGA32434GDGALCMB3&grant_type=client_credentials&country=KE&format=JSON");
		
		Thread.sleep(3);
	}
		@Test
		void checkResponseBody() {
			
		logger.info("***************Checking Response Body **************");	
			
		String responseBody = response.getBody().asString();
		//System.out.println("Response body: " +responseBody);
		logger.info("Response Body ===> "+responseBody);
		Assert.assertTrue(responseBody!=null);
		}
		
		@Test
		void checkStatusCode() {
			
		logger.info("***************Checking status **************");	
		
		int statusCode = response.getStatusCode();
		//System.out.println("status code is: " +statusCode);
		logger.info("status code ===> " +statusCode);
		Assert.assertEquals(statusCode, 200);
		}
		
		@Test
		void successMessage() {
			
		logger.info("***************Checking suceess message **************");	
		
		Boolean success = response.jsonPath().get("success");
		//System.out.println("Suceess is : " +success);
		logger.info("sucess ===> " +success);
		Assert.assertTrue(success);
		//Assert.assertEquals(success, true);
		}
		
		@Test
		void checkTokenType() {
			
			logger.info("***************Checking token type **************");	
			
			String tokentype = response.jsonPath().get("token_type");
			logger.info("token type ===> " +tokentype);
			Assert.assertEquals(tokentype, "bearer");
			System.out.println();
			logger.info("***************token**************");	
			String token1 = this.returnToken();
			logger.info(token1);	
			
			
			}
		
		public String returnToken() {
			String token = response.jsonPath().get("access_token");			
			return token;
			
		}
}
		
	

	

