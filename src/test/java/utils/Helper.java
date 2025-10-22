package utils;

import java.io.File;
import java.time.Duration;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Helper {
	
	
	// Capture screenshot
	WebDriver driver;
	
	public Helper(WebDriver driver)
	{
		this.driver = driver;
	}

    public void captureScreenShot()
    {
	
    try {
    	File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

    // Save screenshot to desired location

    File destination = new File("screenshot.png");

    FileUtils.copyFile(screenshot, destination);

    System.out.println("Screenshot saved successfully.");
    }
    catch(Exception e)
    {
    	e.printStackTrace();
    }
    
    }

    public void waitForWebElementToAppear(WebElement findBy) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(findBy));

	}

}
