package testScriptUI;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.qameta.allure.Allure;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import pageObject.UserRegistration;

@Epic("User Registration")
@Feature("user registration with valid values")
public class UserRegistrationTest extends BaseUiTest {

	UserRegistration user;
	@Test
	@Severity(SeverityLevel.NORMAL)
	@Parameters({"Username","Email", "AccountType"})
	public void userRegistrationTest(String username, String EmailId, String AccountType)
	{
		try {
			user = new UserRegistration(driver);
			user.userRegistration(username, EmailId, AccountType);
			Allure.addAttachment("User Registration is sucessfull", "Passed");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			captureScreenShot();
			Allure.addAttachment("User Registration failed", "Failed");
		}
	}

}
