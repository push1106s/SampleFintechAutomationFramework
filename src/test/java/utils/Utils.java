package utils;

import java.io.FileInputStream;
import java.io.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Utils {

	public String readGlobalData(String key)
	{

		String value =null;

		try {

			Properties prop = new Properties();
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/Global.properties");
			prop.load(fis);
			value = prop.getProperty(key);


		}
		catch(Exception e) {e.printStackTrace();}
		return value;


	}
	
	public String readJSON_UsingJAVA(String tests , String key) {
        String value=null;
         JSONParser parser = new JSONParser();
        try {
            // Read the JSON file and parse it
            Object obj = parser.parse(new FileReader(System.getProperty("user.dir") + "/Global.json"));
            // Convert the parsed object to a JSONObject
            JSONObject jsonObject = (JSONObject) obj;
            // Get the value of the "skills" field`
            JSONArray skills = (JSONArray) jsonObject.get(tests);
            //System.out.println(skills);
            // Traverse the list
            for (int i = 0; i < skills.size(); i++) {
                JSONObject obj1 = (JSONObject) skills.get(i);
                value = (String) obj1.get(key);
                //System.out.println(url_);
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return value;
    }


}