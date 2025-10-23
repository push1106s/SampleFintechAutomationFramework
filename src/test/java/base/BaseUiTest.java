package base;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import utils.*;


/**
 * Base class for UI test automation.
 * Provides setup and teardown methods for WebDriver initialization and test lifecycle management.
 * Also includes utility and helper object initialization.
 */
public class BaseUiTest  {

	public static WebDriver driver; // Shared WebDriver instance for all UI tests
	Utils utObj; // Utility object for reading global data and configurations
	Helper helpObj; // Helper object for additional UI operations


	/**
	 * Initializes WebDriver based on environment and browser type.
	 * Reads base URL from global configuration and launches the browser.
	 *
	 * @param env the target environment (e.g., qa, dev, prod)
	 * @param browser the browser to use (e.g., Chrome, Firefox, Edge)
	 * @return initialized WebDriver instance
	 */
	public WebDriver getDriver(String env, String browser)
	{

		String url = null;

		try {
			// Read base URL based on environment
			if (env.equalsIgnoreCase("qa")) {url = utObj.readGlobalData("baseqaurl");}
			if (env.equalsIgnoreCase("dev")) {url = utObj.readGlobalData("basedevurl");}
			if (env.equalsIgnoreCase("prod")) {url = utObj.readGlobalData("baseprodurl");}

			// Initialize WebDriver based on browser type
			if (browser.equalsIgnoreCase("Chrome"))
			{
				driver = new ChromeDriver();
			}
			else if(browser.equalsIgnoreCase("Firefox"))
			{
				driver = new FirefoxDriver();
			}
			else if(browser.equalsIgnoreCase("Edge"))
			{
				driver = new EdgeDriver();
			}
			else {
				throw new IllegalArgumentException("Unsupported browser: " + browser);
			}

			// Configure browser settings
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));	
			driver.get(url);
		}
		catch(Exception e) {e.printStackTrace();}
		return driver;


	}

	public void captureScreenShot()
	{

	}
	/**
	 * Executed before the entire test suite starts.
	 * Initializes utility and helper objects.
	 */
	@BeforeSuite
	public void preTestSetup()
	{
		utObj = new Utils();
		helpObj = new Helper(driver);
	}

	/**
	 * Executed before each test class.
	 * Initializes WebDriver using environment and browser settings.
	 */
	@BeforeClass
	public void SetUp()
	{

		driver = getDriver(utObj.readGlobalData("env"),utObj.readGlobalData("browser"));
	}


	/**
	 * Executed after each test class.
	 * Quits the WebDriver instance to close the browser.
	 */
	@AfterClass
	public void tearDown()
	{
		driver.quit();
	}

	/**
	 * Executed after the entire test suite finishes.
	 * Can be used to close database connections or clean up resources.
	 */
	@AfterSuite
	public void postTestSetup()
	{
		//Close JDBC connection
	}
}
