package com.smart.AbacusIntegTest.GetToken;

import java.io.IOException;
import java.io.*;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.smart.AbacusIntegTest.base.TestBase;
import com.smart.AbacusIntegTest.dataprovider.ExcelUtil;

import io.restassured.RestAssured;

public class TC003_SendCategory extends TestBase {
	@Test(dataProvider="schemedataprovider")
	void sendCategorytoSmart(
			String catDesc,
			String clnCatCode,
			String medCatg,
			String userId,
			String clnPolCode			
			) throws InterruptedException {
		
		logger.info("::::::: Started TC002_Send_Categories_to_Smart :::::");		
		
		RestAssured.baseURI = "https://data.smartapplicationsgroup.com/api/integcombo";
		
		response = RestAssured.given()
			.header("X-Gravitee-API-Key","5790e360-7d1f-4748-85a3-27c7d8eed0ac")
			.queryParam("catDesc",catDesc)
			.queryParam("clnCatCode",clnCatCode)
			.queryParam("medCatg",medCatg)
			.queryParam("userId",userId)
			.queryParam("clnPolCode",clnPolCode)
			.queryParam("waitingPeriod",0)
			.queryParam("country","ZM")
			.queryParam("format","JSON")
			.queryParam("access_token", "241e5ba387}g4b1791-877g15374g5t}e}52b-2D{242GFe{GSSJFD}1-e0678503ib7955d8d5")
			.queryParam("customerid","7323213240943842574547387193")
			.request().log().uri()
			.when()
			.post("/benefitCategories")
			.then()
			.statusCode(400)
			.extract()
			.response();
		
		// response = httpRequest.request(Method.POST,"/schemes");
		
		logger.info("::::::::  Checking Response Body :::::::");	
		
		String responseBody = response.getBody().asString();
		//System.out.println("Response body: " +responseBody);
		logger.info("Response Body ===> "+responseBody);
		Assert.assertTrue(responseBody!=null);
		
	}
	
	@DataProvider(name="schemedataprovider")
	String [][]getScheme() throws IOException{
		
		String path ="H:/Afyaplus/AbacusIntegTest/src/test/java/com/smart/AbacusIntegTest/dataprovider/catg.xlsx";
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
