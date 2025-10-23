package testScriptUI;


import org.testng.annotations.Parameters;

import org.testng.annotations.Test;

import base.BaseUiTest;
import io.qameta.allure.Allure;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import pageObject.*;


/**
 * Test class for validating the transaction creation functionality via UI.
 * Utilizes TestNG for test execution and Allure for enhanced reporting.
 */
@Epic("Create Transaction") //Groups this test under the 'Create Transaction' epic in Allure reports
@Feature("Creating Transaction with valid values") // Specifies the feature being tested
public class CreateTransactionTest extends BaseUiTest {

	// Page object representing the transaction creation page
	CreateTransactionPage transaction;

	/**
	 * Test method to verify that a transaction can be successfully created with valid input values.
	 * Parameters are injected via TestNG XML configuration.
	 *
	 * @param userid the ID of the user initiating the transaction
	 * @param recipientId the ID of the recipient user
	 * @param amount the amount to be transferred
	 */
	@Test
	@Severity(SeverityLevel.CRITICAL)
	@Parameters({"Username","Email", "AccountType"})
	public void createTransactionTest(String userid, String recipientId, String amount)
	{
		try {
			transaction = new CreateTransactionPage(driver);
			transaction.CreateTransaction(userid, recipientId, amount);
			Allure.addAttachment("Transaction is sucessfull", "Passed");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			captureScreenShot();
			Allure.addAttachment("Transaction is not sucessfull", "Failed");
		}
	}

}
