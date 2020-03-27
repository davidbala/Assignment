package in.reqres.GetAllUsers;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
/**
 * 
 * @author David Bala
 *
 */
public class InvalidUser 
{
	
	@Test
	public void invalidUser() throws IOException {
		FileInputStream fis = new FileInputStream("./Properties/env.properties");
		Properties prop = new Properties();
		prop.load(fis);
		
		//Get the BaseURI from the Property file and in case the change is needed in future it can be changed in Property file
		
		RestAssured.baseURI=prop.getProperty("baseuri");
		
		Response res = RestAssured.given()
		
		.when().get("api/users/15")
		.then().assertThat().statusCode(404)
		.extract().response();
		
		//Get the Actual Status 
		
		int actual=res.getStatusCode();
		
		//Validate the Response
		
		Assert.assertEquals(actual, 404);
		System.out.println("Status Code : " + actual);
	}

}
