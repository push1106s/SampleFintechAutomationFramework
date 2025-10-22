package testScriptUI;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import utils.*;

public class BaseUiTest  {

	WebDriver driver;
	Utils utObj;
	Helper helpObj;



	public WebDriver getDriver(String env, String browser)
	{

		WebDriver driver = null;
		String url = null;

		try {
			if (env.equalsIgnoreCase("qa")) {url = utObj.readGlobalData("baseqaurl");}
			if (env.equalsIgnoreCase("dev")) {url = utObj.readGlobalData("basedevurl");}
			if (env.equalsIgnoreCase("prod")) {url = utObj.readGlobalData("baseprodurl");}


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
	@BeforeSuite
	public void preTestSetup()
	{
		utObj = new Utils();
		helpObj = new Helper(driver);
	}

	@BeforeClass
	public void SetUp()
	{

		driver = getDriver(utObj.readGlobalData("env"),utObj.readGlobalData("browser"));
	}


	@AfterClass
	public void tearDown()
	{
		driver.quit();
	}

	@AfterSuite
	public void postTestSetup()
	{
		//Close JDBC connection
	}
}
