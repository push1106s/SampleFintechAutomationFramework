package testScriptAPI;

import org.testng.annotations.BeforeSuite;

import io.restassured.RestAssured;
import utils.Payload;
import utils.Utils;

public class BaseApiTest {
	
	 Utils utObj;
	Payload payload;
	
	/**
	 * This method is annotated with @BeforeSuite, which means it will be executed
	 * once before any tests in the suite begin. It's typically used for global setup tasks.
	 */
	@BeforeSuite
	public void preTestSetup() {
	    
	    /**
	     * Create an instance of the Utils class.
	     * This utility object provides access to helper methods such as reading configuration data.
	     */
	    utObj = new Utils();
	    
	    /**
	     * Set the base URI for RestAssured API requests.
	     * The URI is fetched from a global configuration using the utility method.
	     */
	    RestAssured.baseURI = utObj.readGlobalData("baseapiurl");
	    
	    /**
	     * Initialize the Payload object.
	     * This object will be used to construct request bodies for API calls.
	     */
	    payload = new Payload();
	}

	

}

