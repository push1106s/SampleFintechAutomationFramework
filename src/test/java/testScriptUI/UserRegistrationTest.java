package testScriptUI;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import base.BaseUiTest;
import io.qameta.allure.Allure;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import pageObject.UserRegistrationPage;


/**
 * Test class for verifying user registration functionality via UI.
 * Uses TestNG for test execution and Allure for reporting.
 */
@Epic("User Registration") //Allure annotation to group tests under the 'User Registration' epic
@Feature("user registration with valid values") // Allure annotation to specify the feature being tested
public class UserRegistrationTest extends BaseUiTest {

	/**
	 * Constructor that calls the superclass constructor to initialize base test setup.
	 */
	public UserRegistrationTest()
	{
		super();
	}


	/**
	 * Test method to validate user registration with valid input parameters.
	 * Parameters are injected via TestNG XML configuration.
	 *
	 * @param username the name of the user to register
	 * @param EmailId the email address of the user
	 * @param AccountType the type of account to be created
	 */
	UserRegistrationPage user;
	@Test
	@Severity(SeverityLevel.NORMAL) // Allure annotation to indicate the severity level of the test
	@Parameters({"Username","Email", "AccountType"}) // TestNG annotation to inject parameters
	public void userRegistrationTest(String username, String EmailId, String AccountType)
	{
		try {
			user = new UserRegistrationPage(driver);
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
