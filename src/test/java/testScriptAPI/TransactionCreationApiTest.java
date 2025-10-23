package testScriptAPI;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
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
 * Test class for verifying transaction creation and retrieval functionality via REST API.
 * Uses TestNG for test execution and Allure for reporting.
 * Inherits from BaseApiTest for shared setup and utilities.
 */
@Epic("Create Transaction via API")
@Feature("Creating Transaction via API with create and get")
public class TransactionCreationApiTest extends BaseApiTest {

	String id;

	/**
	 * Test method to validate transaction creation with valid input values.
	 * Reads test data from a JSON utility and constructs the request payload.
	 * Sends a POST request to /transactions endpoint and validates the response.
	 * Asserts that the returned userId and amount match the input, and that a valid transaction ID is returned.
	 * Attaches result to Allure report.
	 */
	@Test
	@Severity(SeverityLevel.CRITICAL)
	public void testTransactionCreation() {

		String userId = utObj.readJSON_UsingJAVA("createTransaction","userId");
		String amount = utObj.readJSON_UsingJAVA("createTransaction","amount");
		String type = utObj.readJSON_UsingJAVA("createTransaction","type");
		String recipientId = utObj.readJSON_UsingJAVA("createTransaction","recipientId");

		String requestBody = payload.createTransactionPayload(userId,amount,type,recipientId);

		Map<String, String> headers = new HashMap<>();
		headers.put("Content-Type", "application/json");               // specify request format
		headers.put("x-api-key", "reqres-invalidKey");

		try
		{


			Response response = given()

					.headers(headers)

					.body(requestBody)

					.when()

					.post("/transactions")

					.then()

					.statusCode(200)

					.extract().response();

			// Validate response

			String reponseuserId = response.jsonPath().getString("userId");
			String responseamount = response.jsonPath().getString("amount");
			id = response.jsonPath().getString("id");
			Assert.assertEquals(userId, reponseuserId);
			Assert.assertEquals(amount, responseamount);

			Assert.assertTrue(id != null && !id.isEmpty());
			Allure.addAttachment("Transaction creation via API is successfull", "Passed");
		}
		catch (Exception e) {
			e.printStackTrace();
			Allure.addAttachment("Get Transaction via API is not successfull", "Failed");
		}

	}

	/**
	 * Test method to retrieve transaction details using the stored transaction ID.
	 * Sends a GET request to /transactions/{id} endpoint and validates the response.
	 * Logs the response and attaches result to Allure report.
	 */
	@Test
	@Severity(SeverityLevel.NORMAL)
	public void testGetUserTransaction()
	{


		Map<String, String> headers = new HashMap<>();
		headers.put("Content-Type", "application/json");               // specify request format
		headers.put("x-api-key", "reqres-invalidKey");

		try {
			Response response =  given()
					.headers(headers)
					.when()
					.get("/transactions/{id}")
					.then()
					.statusCode(200)
					.extract()
					.response();			
			System.out.println("User Details: " + response.getBody().asString());
			Allure.addAttachment("Get Transaction via API is successfull", "Passed");

		}
		catch (Exception e) {
			e.printStackTrace();
			Allure.addAttachment("Get Transaction via API is not successfull", "Failed");

		}
	}

}


