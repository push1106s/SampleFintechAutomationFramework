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

/**
 * Helper class providing utility methods for Selenium WebDriver operations,
 * such as capturing screenshots and waiting for elements to appear.
 */
public class Helper {


	// WebDriver instance used for browser interactions
	WebDriver driver;

	/**
	 * Constructor to initialize the Helper class with a WebDriver instance.
	 *
	 * @param driver the WebDriver instance to be used by this helper
	 */
	public Helper(WebDriver driver)
	{
		this.driver = driver;
	}

	/**
	 * Captures a screenshot of the current browser window and saves it as "screenshot.png"
	 * in the project root directory.
	 */
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

	/**
	 * Waits up to 5 seconds for a specific WebElement to become visible on the page.
	 *
	 * @param findBy the WebElement to wait for
	 */
	public void waitForWebElementToAppear(WebElement findBy) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(findBy));

	}

}
