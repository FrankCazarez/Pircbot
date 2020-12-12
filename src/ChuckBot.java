//Imports
import java.io.*; 
import java.net.*;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ChuckBot {

	//getFact Method runs if "chuck" is recognized
	public static String getFact() throws IOException {
		//Web address for API, no key required
		//https://api.chucknorris.io/jokes/random
		
		String myAPIurl = "https://api.chucknorris.io/jokes/random";
		String result = "";
		
		//Connecting to the Web address
		URL url = new URL(myAPIurl);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//Extra Property added due to "GET" not working, works the same, except Google Chrome has to be used to be able to work		
		conn.addRequestProperty("User-Agent", "Chrome");
//		conn.setRequestMethod("GET");
		//Reading the information in the web address
		BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		result = rd.readLine();
		//Sending information read to the parseJsonFunction to get individual components and saving it to the string message
		String message = parseJsonFunction(result);
		
		//returns message as a string to be displayed as the message sent to the channel
		return message;
	}
	
	//parseJsonFunction gets the values stored in selected key words
	public static String parseJsonFunction(String json) {
		
		// Parse entire JSON string and convert to object
		JsonObject object = new JsonParser().parse(json).getAsJsonObject();
		
		//Get the fact from the object
		String value = object.get("value").getAsString();
		
		//returns message with the fact to be saved in the string named message
		return "Chuck Norris Fact: " + value; 
	}
}


