package testScriptUI;


import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.qameta.allure.Allure;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import pageObject.*;

@Epic("Create Transaction")
@Feature("Creating Transaction with valid values")
public class CreateTransactionTest extends BaseUiTest {

	createTransaction transaction;
	@Test
	@Severity(SeverityLevel.CRITICAL)
	@Parameters({"Username","Email", "AccountType"})
	public void createTransactionTest(String username, String EmailId, String AccountType)
	{
		try {
			transaction = new createTransaction(driver);
			transaction.CreateTransaction(username, EmailId, AccountType);
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
