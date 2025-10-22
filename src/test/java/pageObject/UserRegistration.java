package pageObject;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UserRegistration {
	
	WebDriver driver;
	
	public UserRegistration(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this); // Initialize elements
	}
	
	
	//Page elements
    @FindBy(xpath = "//input[@id ='name']")
    WebElement usernameField;
    
    @FindBy(xpath = "//input[@id='name']" )
    WebElement emailField;
    
  
    @FindBy(xpath = "input[@value='premium']" )
    WebElement AccountTypeField;
    
    @FindBy(xpath = "//input[@id='accountType']" )
    WebElement createUserButton;
    
    
    public void userRegistration(String username, String emailId, String AccountType)
    {
    	usernameField.sendKeys(username);
    	emailField.sendKeys(emailId);
    	AccountTypeField.sendKeys(AccountType);
    	createUserButton.click();
    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));		
    }
    
}
