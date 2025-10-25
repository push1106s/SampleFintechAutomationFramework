package testScriptAPI;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import base.BaseApiTest;
import static io.restassured.RestAssured.given;

import io.qameta.allure.Allure;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.response.Response;


/**
 * Test class for verifying user creation functionality via REST API.
 * Uses TestNG for test execution and Allure for reporting.
 * Inherits from BaseApiTest for shared setup and utilities.
 */
@Epic("Create user via API") //Groups this test under the 'Create Transaction' epic in Allure reports
@Feature("Creating User via API with different scenarios") //Specifies the feature being tested
public class UserCreationApiTest extends BaseApiTest {

	String id;

	/**
	 * Test method to validate user creation with valid input parameters.
	 * Parameters are injected via TestNG XML configuration.
	 *
	 * @param name the name of the user to be created
	 * @param email the email address of the user
	 * @param accountType the type of account to be created
	 */
	@Test(priority= 1)
	@Severity(SeverityLevel.CRITICAL)
	@Parameters({"name","email","accountType"})
	public void testCreateUser(String name, String email, String accountType) {

		String requestBody = payload.createUserPayload(name, email, accountType);
		
		Map<String, String> headers = new HashMap<>();
		headers.put("Content-Type", "application/json");               
		headers.put("token", token);

		try
		{
			Response response = given()

					.headers(headers)

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

	
	/**
	 * Test method to retrieve user details using stored user ID.
	 * Sends a GET request and validates the response.
	 */
	@Test(priority= 2)
	@Severity(SeverityLevel.NORMAL)
	public void testGetUser()
	{

		Map<String, String> headers = new HashMap<>();
		headers.put("Content-Type", "application/json");               // specify request format
		headers.put("token", token);
		try {
			Response response =  given()
					.headers(headers)
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

	/**
	 * Test method to update user email using API.
	 * Parameters are injected via TestNG XML configuration.
	 *
	 * @param updatedemail the new email address to update
	 */
	@Test(priority= 3)
	@Severity(SeverityLevel.NORMAL)
	@Parameters ({"updatedemail"})
	public void testUpdateUser(String updatedemail)
	{
		String requestBodyUpdate = payload.updateUserPayload(updatedemail);

		Map<String, String> headers = new HashMap<>();
		headers.put("Content-Type", "application/json");               // specify request format
		headers.put("token", token);

		try {
			Response response =  given()
					.headers(headers)
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

	/**
	 * Test method to validate API behavior with an invalid API key.
	 * Expects a 401 Unauthorized response.
	 *
	 * @param name the name of the user
	 * @param email the email address of the user
	 * @param accountType the type of account to be created
	 */
	@Test(priority= 4)
	@Severity(SeverityLevel.NORMAL)
	public void testUnauthorizedCreateUser(String name, String email, String accountType) {



		String requestBody = payload.createUserPayload(name, email, accountType);

		// POST request to create user
		Map<String, String> headers = new HashMap<>();
		headers.put("Content-Type", "application/json");               // specify request format
		headers.put("token", token);

		try
		{
			Response response = given()

					.headers(headers)

					.body(requestBody)

					.when()

					.post("/users")

					.then()

					.statusCode(401)

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


	/**
	 * Test method to validate API response for an invalid endpoint.
	 * Expects a 404 Not Found response.
	 *
	 * @param name the name of the user
	 * @param email the email address of the user
	 * @param accountType the type of account to be created
	 */
	@Test(priority= 5)
	@Severity(SeverityLevel.NORMAL)
	public void testInvalidPage(String name, String email, String accountType) {


		// Request payload

		String requestBody = payload.createUserPayload(name, email, accountType);

		// POST request to create user
		Map<String, String> headers = new HashMap<>();
		headers.put("Content-Type", "application/json");               // specify request format
		headers.put("token", token);

		try
		{
			Response response = given()

					.headers(headers)

					.body(requestBody)

					.when()

					.post("/pushpa")

					.then()

					.statusCode(404)

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
}
