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

@Epic("Create Transaction via API")
@Feature("Creating Transaction via API with valid values")
public class TransactionCreationApi extends BaseApiTest {
	
	String id;
	
	@Test
	@Severity(SeverityLevel.CRITICAL)
    public void testTransactionCreation() {

    
        // Request payload
 
        // POST request to create user
		
        try
        {
        String userId = utObj.readJSON_UsingJAVA("createTransaction","userId");
        String amount = utObj.readJSON_UsingJAVA("createTransaction","amount");
        String type = utObj.readJSON_UsingJAVA("createTransaction","type");
        String recipientId = utObj.readJSON_UsingJAVA("createTransaction","recipientId");
        
        String requestBody = payload.createTransactionPayload(userId,amount,type,recipientId);
        
        	Response response = given()

            .header("Content-Type", "application/json")

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
        Assert.assertEquals(amount, amount);

        Assert.assertTrue(id != null && !id.isEmpty());
        Allure.addAttachment("Transaction creation via API is successfull", "Passed");
        }
        catch (Exception e) {
        	e.printStackTrace();
        	Allure.addAttachment("Get Transaction via API is not successfull", "Failed");
        }

    }

	public void testGetUserTransaction()
	{
		
		try {
		Response response =  given()
				.header("Accept", "application/json")
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
	
	/*@Test
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
				
		}
		catch (Exception e) {e.printStackTrace();}*/
	}


