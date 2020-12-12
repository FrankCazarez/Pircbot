//Imports
import java.io.*;
import java.net.*;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class WeatherBot2 {
	
	//getTemp Method runs if "zip" is recognized
	public static String getTemp(String Zipcode) throws IOException {
		//Web address for API
		//https://api.openweathermap.org/data/2.5/weather?zip={Zipcode},us&appid={Key}&units=imperial
		
		//Constructing the custom web address using the API key and the users inputed zip code
		String myAPIurl = "https://api.openweathermap.org/data/2.5/weather?zip="; 
		String myAPItoken = ",us&appid=a79b93d2956bc7ddb29f49280c2655cb";
		String weatherURL = myAPIurl + Zipcode + myAPItoken + "&units=imperial";
		String result = "";
		
		//Connecting to the Web address
		URL url = new URL(weatherURL);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//		conn.addRequestProperty("User-Agent", "Chrome");
		conn.setRequestMethod("GET");
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

        // Get the object under the "main" key
        JsonObject main = object.getAsJsonObject("main");
       
        //Get the city name from the object
        String city = object.get("name").getAsString();

        // Get the temperatures from "main" object
        double temp = main.get("temp").getAsDouble();
        double temp_min = main.get("temp_min").getAsDouble();
        double temp_max = main.get("temp_max").getAsDouble();
        
        //returns message with the city name, temperature, low, and high to be saved in the string named message
        return "The Temperature In " + city + " Is " + temp + " Degrees Fahrenheit With A Low Of " + temp_min + " And A High Of " + temp_max;
		
	}
}
