package testScriptAPI;

import static org.testng.Assert.*;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

import io.qameta.allure.Allure;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import testScriptUI.BaseUiTest;

@Epic("Create user via API")
@Feature("Creating User via API with valid values")
public class UserCreationApi extends BaseApiTest {
	
	String id;
	
	@Test
	@Severity(SeverityLevel.CRITICAL)
@Parameters({"name","email","accountType"})
    public void testCreateUser(String name, String email, String accountType) {

    
        // Request payload

        String requestBody = payload.createUserPayload(name, email, accountType);
 
        // POST request to create user

        try
        {
        Response response = given()

            .header("Content-Type", "application/json")

            .body(requestBody)

        .when()

            .post("/users")

        .then()

            .statusCode(200)

            .extract().response();
 
        // Validate response

       String reponsename = response.jsonPath().getString("name");
        String responseEmail = response.jsonPath().getString("email");
        id = response.jsonPath().getString("id");
        Assert.assertEquals(name, reponsename);
        Assert.assertEquals(email, responseEmail);

        Assert.assertTrue(id != null && !id.isEmpty());
        Allure.addAttachment("User creation via API is successfull", "Passed");
        }
        catch (Exception e) {
        	e.printStackTrace();
        	Allure.addAttachment("User creation via API is not successfull", "Failed");
        	}
        

    }

	public void testGetUser()
	{
		
		try {
		Response response =  given()
				.header("Accept", "application/json")
					.when()
				 .get("/users/{id}")
				.then()
				 .statusCode(200)
				 .extract()
				.response();			
		System.out.println("User Details: " + response.getBody().asString());
		Allure.addAttachment("Get user via API is successfull", "Passed");
				
		}
		catch (Exception e) {
			e.printStackTrace();
			Allure.addAttachment("Get user via API is not successfull", "Failed");
			}
	}
	
	@Test
	@Parameters ({"updatedemail"})
	public void testUpdateUser(String updatedemail)
	{
		 String requestBodyUpdate = payload.updateUserPayload(updatedemail);
		try {
		Response response =  given()
				.header("Accept", "application/json")
				.body(requestBodyUpdate)
					.when()
				 .get("/users/{id}")
				.then()
				 .statusCode(200)
				 .extract()
				.response();			
		System.out.println("User Details: " + response.getBody().asString());
		Allure.addAttachment("user update via API is successfull", "Passed");
				
		}
		catch (Exception e) {
			e.printStackTrace();
			Allure.addAttachment("user update via API is not successfull", "Failed");
			}
	}

}
