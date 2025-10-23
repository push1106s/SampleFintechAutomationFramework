package pageObject;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import base.BaseUiTest;

/**
 * Page Object class for handling transaction creation functionality via UI.
 * Encapsulates the web elements and actions related to the transaction form.
 * Inherits from BaseUiTest to utilize shared WebDriver and utilities.
 */
public class CreateTransactionPage extends BaseUiTest {
	
	
	
	/**
     * Constructor to initialize the page elements using PageFactory.
     *
     * @param driver the WebDriver instance used to interact with the browser
     */
	public CreateTransactionPage(WebDriver driver)
	{
		CreateTransactionPage.driver = driver;
		PageFactory.initElements(driver, this); // Initialize elements with driver context
	}
	
	// ============================
    // Web Elements on Transaction Page
    // ============================
	
	/** Input field for entering the user ID */
    @FindBy(css = "input#userId")
    private WebElement useridField;
    
    /** Input field for entering the recipient ID */
    @FindBy(xpath = "//input[@id='recipientId']" )
    private WebElement recipientidField;
    
    /** Input field for entering the transaction amount */
    @FindBy(xpath = "//input[@id='accountType']" )
    private WebElement amountField;
    
    /** Button to submit the transaction form */
    @FindBy(xpath = "//button[@type='submit']")
    private WebElement createTransactionButton;
    
    /** Success message for user Transaction */
	@FindBy(xpath = "//label[text()='Transaction is sucessfull'" )
	private WebElement sucessMessage;
    
    /**
     * Method to perform transaction creation by filling out the form and submitting it.
     *
     * @param userId the ID of the user initiating the transaction
     * @param recipientId the ID of the recipient receiving the transaction
     * @param amount the amount to be transferred
     */
    public void CreateTransaction(String userId, String recipientId, String amount)
    {
    	useridField.sendKeys(userId);
    	recipientidField.sendKeys(recipientId);
    	amountField.sendKeys(amount);
    	createTransactionButton.click();
    	Assert.assertTrue(sucessMessage.isDisplayed());
    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));		
    }
    

}
