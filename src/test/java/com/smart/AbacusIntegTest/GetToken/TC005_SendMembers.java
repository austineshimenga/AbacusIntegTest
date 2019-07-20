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

public class TC005_SendMembers  extends TestBase {
	
	@Test(dataProvider="membershipdataprovider")
	void sendMembers_2_Smart(
			String clnPolCode,
			String membershipNumber,
			String staffNumber,
			String familyCode,
			String otherNumber,
			String clnCatCode,
			String memType,
			String surname,
			String secondName,
			String thirdName,
			String otherNames,
			String idNumber,
			String dob,
			String gender,
			String nhifNumber,
			String schemeStartDate,
			String schemeEndDate,
			String userID,
			String roamingCountries
			){
		
		logger.info("::::::: Started TC002_Send_members_to_Smart :::::");		
		
		RestAssured.baseURI = "https://data.smartapplicationsgroup.com/api/integcombo";
		httpRequest =  RestAssured.given();
				
		Map<String, Object> memberQueryParams = new HashMap<String, Object>();
			memberQueryParams.put("clnPolCode",clnPolCode);
			memberQueryParams.put("membershipNumber",membershipNumber);
			memberQueryParams.put("staffNumber",staffNumber);
			memberQueryParams.put("familyCode",familyCode);
			memberQueryParams.put("otherNumber",otherNumber);
			memberQueryParams.put("clnCatCode",clnCatCode);
			memberQueryParams.put("memType",memType);
			memberQueryParams.put("surname",surname);
			memberQueryParams.put("secondName",secondName);
			memberQueryParams.put("thirdName",thirdName);
			memberQueryParams.put("otherNames",otherNames);
			memberQueryParams.put("idNumber",idNumber);
			memberQueryParams.put("dob",dob);
			memberQueryParams.put("gender",gender);
			memberQueryParams.put("nhifNumber",nhifNumber);
			memberQueryParams.put("schemeStartDate",schemeStartDate);
			memberQueryParams.put("schemeEndDate",schemeEndDate);
			memberQueryParams.put("userID",userID);
			memberQueryParams.put("roamingCountries",roamingCountries);
			memberQueryParams.put("country","ZM");
			memberQueryParams.put("format","JSON");
			memberQueryParams.put("access_token", "b6d2}e24980}12w543-r85254784803647777-26g33dS08T4DTGF3SD-3180}41538n8t1ed64");
			memberQueryParams.put("customerid","7323213240943842574547387193");
			
			httpRequest.request().queryParams(memberQueryParams);
			httpRequest.header("X-Gravitee-API-Key","5790e360-7d1f-4748-85a3-27c7d8eed0ac");
			
			logger.info(":::::: >------------ Logging the request ------->:::::");
			
			httpRequest.log().uri();			

			response = httpRequest.request(Method.POST,"/members");
					
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
	
	@DataProvider(name="membershipdataprovider")
	String [][]getScheme() throws IOException{
		
		String path ="H:/Afyaplus/AbacusIntegTest/src/test/java/com/smart/AbacusIntegTest/dataprovider/Members.xlsx";
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
