package com.smart.AbacusIntegTest.GetToken;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.smart.AbacusIntegTest.base.TestBase;
import com.smart.AbacusIntegTest.dataprovider.ExcelUtil;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class TC004_SendBenefits extends TestBase {
	
	@Test(dataProvider="benefitsdataprovider")
	void sendBenefitstoSmart(
			String benefitDesc,
			String policyNumber,
			String benTypeId,
			String subLimitAmt,
			String serviceType,
			String memAssignedBenefit,
			String userId,
			String clnPolCode,
			String catCode,
			String clnBenCode,
			String benTypDesc,
			String benLinked2Tqcode,
			String genderApplicability
			) throws InterruptedException {
		
		logger.info("::::::: Started TC002_Send_Categories_to_Smart :::::");		
		
		RestAssured.baseURI = "https://data.smartapplicationsgroup.com/api/integcombo";
		
		/*
		response = RestAssured.given()
			.header("X-Gravitee-API-Key","5790e360-7d1f-4748-85a3-27c7d8eed0ac")
			.queryParam("benefitDesc",benefitDesc)
			.queryParam("policyNumber",policyNumber)
			.queryParam("benTypeId",benTypeId)
			.queryParam("subLimitAmt",subLimitAmt)
			.queryParam("serviceType",serviceType)
			.queryParam("memAssignedBenefit",memAssignedBenefit)
			.queryParam("userId",userId)
			.queryParam("clnPolCode",clnPolCode)
			.queryParam("catCode",catCode)
			.queryParam("clnBenCode",clnBenCode)
			.queryParam("benTypDesc",benTypDesc)
			.queryParam("benLinked2Tqcode",benLinked2Tqcode)
			.queryParam("genderApplicability",genderApplicability)
			.queryParam("coPayAmt",0)
			.queryParam("coPayPercent",0)
			.queryParam("limitAmt",0)
			.queryParam("waitingPeriod",0)
			.queryParam("clnBenClauseCode",clnBenCode)
			.queryParam("country","ZM")
			.queryParam("format","JSON")
			.queryParam("access_token", "1702b306d04579533}-056645dg44r7525892-GFeH4rGG3A}83VF83G-2658sbga6d66192140")
			.queryParam("customerid","7323213240943842574547387193")
			.request().log().uri()
			.when()
			.post("/benefits")
			.then()
			.extract()
			.response();
			*/
		
		httpRequest =  RestAssured.given();
		
		Map<String, Object> queryparams = new HashMap<String, Object>();
		
		//JSONObject queryparams = new JSONObject();
		queryparams.put("benefitDesc",benefitDesc);
		queryparams.put("policyNumber",policyNumber);
		queryparams.put("benTypeId",benTypeId);
		queryparams.put("subLimitAmt",subLimitAmt);
		queryparams.put("serviceType",serviceType);
		queryparams.put("memAssignedBenefit",memAssignedBenefit);
		queryparams.put("userId",userId);
		queryparams.put("clnPolCode",clnPolCode);
		queryparams.put("catCode", catCode);
		queryparams.put("clnBenCode", clnBenCode);
		queryparams.put("benTypDesc", benTypDesc);
		queryparams.put("benLinked2Tqcode", benLinked2Tqcode);
		queryparams.put("genderApplicability", genderApplicability);
		queryparams.put("coPayAmt", 0);
		queryparams.put("coPayPercent", 0);
		queryparams.put("limitAmt", 0);
		queryparams.put("waitingPeriod", 0);
		queryparams.put("clnBenClauseCode", clnBenCode);
		queryparams.put("country", "ZM");
		queryparams.put("access_token", "0110sd1521e8404r13-e61186500d65088340-{D1r24b{eAgbF43D52-15{94e8g93e4711adb");
		queryparams.put("customerid","7323213240943842574547387193" );
				
		httpRequest.request().queryParams(queryparams);
		httpRequest.header("X-Gravitee-API-Key","5790e360-7d1f-4748-85a3-27c7d8eed0ac");
		logger.info(":::::: >------------ Logging the request ------->:::::");
		
		httpRequest.log().uri();
		response = httpRequest.request(Method.POST,"/benefits");
				
		
		
		String responseBody = response.getBody().asString();
		//System.out.println("Response body: " +responseBody);
		logger.info("Response Body ===> "+responseBody);
		Assert.assertTrue(responseBody!=null);
		
		logger.info("***************Checking status **************");	
		
		int statusCode = response.getStatusCode();
		//System.out.println("status code is: " +statusCode);
		logger.info("status code ===> " +statusCode);
		Assert.assertEquals(statusCode, 200);
		
	}
	
	@DataProvider(name="benefitsdataprovider")
	String [][]getScheme() throws IOException{
		
		String path ="H:/Afyaplus/AbacusIntegTest/src/test/java/com/smart/AbacusIntegTest/dataprovider/Benefits.xlsx";
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
