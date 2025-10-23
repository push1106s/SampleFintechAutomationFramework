package pageObject;

import java.time.Duration;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.BaseUiTest;

/**
 * Page Object class for handling user registration functionality via UI.
 * Encapsulates web elements and actions related to the registration form.
 * Inherits from BaseUiTest to access shared driver and utilities.
 */
public class UserRegistrationPage extends BaseUiTest {

	/**
	 * Constructor to initialize the page elements using PageFactory.
	 * 
	 * @param driver the WebDriver instance used to interact with the browser
	 */
	public UserRegistrationPage(WebDriver driver)
	{
		UserRegistrationPage.driver = driver;
		PageFactory.initElements(driver, this); // // Initialize elements with driver context
	}

	// ============================
	// Web Elements on Registration Page
	// ============================

	/** Input field for entering the user's name */
	@FindBy(xpath = "//input[@id ='name']")
	private WebElement usernameField;

	/** Input field for entering the user's email address */
	@FindBy(xpath = "//input[@id='name']" )
	private WebElement emailField;

	/** Input field for selecting the account type (e.g., premium) */
	@FindBy(xpath = "//input[@value='premium']" )
	private WebElement AccountTypeField;

	/** Button to submit the registration form */
	@FindBy(xpath = "//input[@id='accountType']" )
	private WebElement createUserButton;


	/**
	 * Method to perform user registration by filling out the form and submitting it.
	 * 
	 * @param username the name of the user
	 * @param emailId the email address of the user
	 * @param AccountType the type of account to be created
	 */
	public void userRegistration(String username, String emailId, String AccountType)
	{
		usernameField.sendKeys(username);
		emailField.sendKeys(emailId);
		AccountTypeField.sendKeys(AccountType);
		createUserButton.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));		
	}

}
