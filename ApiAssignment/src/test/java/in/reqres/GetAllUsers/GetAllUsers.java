package in.reqres.GetAllUsers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
/**
 * 
 * @author David Bala
 *
 */

public class GetAllUsers 

{	
	@Test
	public void allUsers() throws IOException {
		
		//To get the data from the Property file 
		
		FileInputStream fis = new FileInputStream("./Properties/env.properties");
		
		
		Properties prop = new Properties();
		prop.load(fis);
		
		//Get the BaseURI from the Property file and in case the change is needed in future it can be changed in Property file
		
		RestAssured.baseURI=prop.getProperty("baseuri");
		
		RequestSpecification request = RestAssured.given();
		
		//Get the Specified Page number from the Property file and also the endpoint where the actions are to be performed
		
		Response res = request.queryParam("page",prop.getProperty("page")).get(prop.getProperty("endpoint"));
		
		//Get the Actual Status 
		
		int actual=res.getStatusCode();
		
		//Validate the Response 
		
		Assert.assertEquals(actual, 200);
		
		System.out.println("Status Code : " + actual);
	}
	
	
}
