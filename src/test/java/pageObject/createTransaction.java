package pageObject;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class createTransaction {
	
	
	
WebDriver driver;
	
	public createTransaction(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this); // Initialize elements
	}
	
	
	//Page elements
    @FindBy(css = "input#userId")
    WebElement useridField;
    
    @FindBy(xpath = "//input[@id='recipientId']" )
    WebElement recipientidField;
    
    
    @FindBy(xpath = "//input[@id='accountType']" )
    WebElement amountField;
    
    @FindBy(xpath = "//button[@type='submit']")
    WebElement createTransactionButton;
    
    
    public void CreateTransaction(String userId, String recepienetId, String amount)
    {
    	useridField.sendKeys(userId);
    	recipientidField.sendKeys(recepienetId);
    	amountField.sendKeys(amount);
    	createTransactionButton.click();
    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));		
    }
    

}
