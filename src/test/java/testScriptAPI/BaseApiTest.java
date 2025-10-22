package testScriptAPI;

import org.testng.annotations.BeforeSuite;

import io.restassured.RestAssured;
import utils.Payload;
import utils.Utils;

public class BaseApiTest {
	
	 Utils utObj;
	Payload payload;
	
	@BeforeSuite
	public void preTestSetup()
	{
		utObj = new Utils();
		 RestAssured.baseURI = utObj.readGlobalData("baseapiurl");
		payload = new Payload();
	}
	
	

}

