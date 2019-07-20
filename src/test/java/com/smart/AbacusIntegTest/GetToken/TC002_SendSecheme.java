package com.smart.AbacusIntegTest.GetToken;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.smart.AbacusIntegTest.base.TestBase;
import com.smart.AbacusIntegTest.dataprovider.ExcelUtil;

import io.restassured.RestAssured;
import io.restassured.http.Method;


public class TC002_SendSecheme extends TestBase {
	
	//private String token;
	
	
	@Test(dataProvider="schemedataprovider")
	void sendSchemetoSmart(
			String POL_NAME,
			String START_DATE,
			String END_DATE,
			String POL_TYPE,
			String CLN_POL_CODE			
			) throws InterruptedException {
		
		logger.info("<*************** Started TC002_Send_Schemes_to_Smart **************>");		
		RestAssured.baseURI = "https://data.smartapplicationsgroup.com/api/integcombo";
		
		response = RestAssured.given()
			.header("X-Gravitee-API-Key","5790e360-7d1f-4748-85a3-27c7d8eed0ac")
			.queryParam("companyName",POL_NAME)
			.queryParam("clnPolCode",CLN_POL_CODE)
			.queryParam("countryCode","ZM")
			.queryParam("anniv","0")
			.queryParam("policyCurrencyId","ZMW")
			.queryParam("polTypeId",POL_TYPE)
			.queryParam("userID","SMART TEST")
			.queryParam("clnPolType","1")
			.queryParam("startDate",START_DATE)
			.queryParam("endDate",END_DATE)
			.queryParam("policyConvRate","1")
			.queryParam("policyStatus","1")
			.queryParam("country","ZM")
			.queryParam("format","JSON")
			.queryParam("access_token", "5e37595398d300de43-760}5e}e5n552g7ia6-NDPND81J39D1Y1J6G0-b1{b3580{13503d550")
			.queryParam("customerid","7323213240943842574547387193")
			.request().log().uri()
			.when()
			.post("/schemes")
			.then()
			.statusCode(400)
			.extract()
			.response();
		
		// response = httpRequest.request(Method.POST,"/schemes");
		
		logger.info("***************Checking Response Body **************");	
		
		String responseBody = response.getBody().asString();
		//System.out.println("Response body: " +responseBody);
		logger.info("Response Body ===> "+responseBody);
		Assert.assertTrue(responseBody!=null);
		
	}
	/*
	@Test
	void checkResponseBody() throws InterruptedException {
		
	logger.info("***************Checking Response Body **************");	
		
	String responseBody = response.getBody().asString();
	//System.out.println("Response body: " +responseBody);
	logger.info("Response Body ===> "+responseBody);
	Assert.assertTrue(responseBody!=null);
	
	Thread.sleep(3);
	}
	
	/* @Test
	void checkSchemesStatusCode() {
		
	logger.info("***************Checking Schemes endpoint status schcode **************");	
	
	int statusCode = response.getStatusCode();
	//System.out.println("status code is: " +statusCode);
	logger.info("status code ===> " +statusCode);
	Assert.assertEquals(statusCode, 200);
	}
	//tests
	
	/* check status line
	 * check server type
	 * check content type
	 */
	
	@DataProvider(name="schemedataprovider")
	String [][]getScheme() throws IOException{
		
		String path ="H:/Afyaplus/AbacusIntegTest/src/test/java/com/smart/AbacusIntegTest/dataprovider/Schemes.xlsx";
		int rowcount = ExcelUtil.getRowCount(path, "Sheet1");
		int collcount = ExcelUtil.geCellCount(path, "Sheet1", 1);
		
		String schemedata[][] = new String[rowcount][collcount];
		for (int i=1; i<=rowcount;i++) {
			for(int j=0;j<collcount;j++) {
				schemedata[i-1][j]=ExcelUtil.getCellData(path, "Sheet1", i, j);
			}
		}
		
		return schemedata;
		
	}

}
