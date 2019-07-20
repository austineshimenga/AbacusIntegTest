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

public class TC007_SchemeActivation extends TestBase {
	

	@Test(dataProvider="schemeActivate")
	void ActivateScheme_on_Smart(
			String clnPolCode,
			String userId,
			String anniv
			/*String id*/
			){
		
		logger.info("::::::: Started TC002_Send_scheme_Actions_to_Smart :::::");		
		
		RestAssured.baseURI = "https://data.smartapplicationsgroup.com/api/integcombo";
		httpRequest =  RestAssured.given();
		
		/*Map<String, Object> pathParams = new HashMap<String, Object>();
			pathParams.put("id",id);*/
				
		Map<String, Object> queryParams = new HashMap<String, Object>();
			queryParams.put("clnPolCode",clnPolCode);
			queryParams.put("userId",userId);
			queryParams.put("anniv",anniv);
			queryParams.put("country","ZM");
			queryParams.put("format","JSON");
			queryParams.put("access_token", "d732d0517}51297446-66ggee157r20376873-3VD13G28C30F3eJVFd-2de43632d794836g93");
			queryParams.put("customerid","7323213240943842574547387193");
			
			httpRequest.request().queryParams(queryParams);
			httpRequest.header("X-Gravitee-API-Key","5790e360-7d1f-4748-85a3-27c7d8eed0ac");
			
			logger.info(":::::: >------------ Logging the request ------->:::::");
			
			httpRequest.log().uri();			

			response = httpRequest.request(Method.PUT,"/schemes/activations/1");
					
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
	
	@DataProvider(name="schemeActivate")
	String [][]getScheme() throws IOException{
		
		String path ="H:/Afyaplus/AbacusIntegTest/src/test/java/com/smart/AbacusIntegTest/dataprovider/schemeactivate.xlsx";
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
