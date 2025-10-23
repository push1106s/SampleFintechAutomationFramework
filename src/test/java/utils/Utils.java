package utils;

import java.io.FileInputStream;

import java.io.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.util.Properties;

/**
 * Utility class providing methods to read configuration data from property and JSON files.
 */

public class Utils {


	/**
	 * Reads a value associated with a given key from the Global.properties file.
	 * @return the value associated with the key, or null if not found or an error occurs
	 */
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

	/**
	 * Reads a value from a JSON array within the Global.json file.
	 * @return the value associated with the key, or null if not found or an error occurs
	 */
	public String readJSON_UsingJAVA(String tests , String key) {
		String value=null;
		JSONParser parser = new JSONParser();
		try {

			Object obj = parser.parse(new FileReader(System.getProperty("user.dir") + "/Global.json"));

			JSONObject jsonObject = (JSONObject) obj;

			JSONArray skills = (JSONArray) jsonObject.get(tests);

			for (int i = 0; i < skills.size(); i++) {
				JSONObject obj1 = (JSONObject) skills.get(i);
				value = (String) obj1.get(key);

			}
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
		return value;
	}


}