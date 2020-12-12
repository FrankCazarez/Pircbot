//Imports 
import org.jibble.pircbot.*;
import java.io.IOException;

//Using methods from PircBot parent
public class MyBot extends PircBot {
   
	//Variables for waiting for message
	boolean waitingForCity;
	boolean waitingForZip;
	
	//Default constructor with values for naming the bot, and setting wait statements as false
    public MyBot() {
        this.setName("FrankBot");
        waitingForCity = false;
        waitingForZip = false;
    }
    
    //On Message method, responds once a message is typed in chat
    public void onMessage(String channel, String sender,String login, String hostname, String message) {
    	
    	//Converts message to lower case for easier recognition
    	message = message.toLowerCase();
////////////////////////////////////////////////////////////////////////////////////////////////////////////	//WeatherBot using the city name
    	
    	//After waitingForCity becomes true new message is recorded and used here
    	if (waitingForCity) {
    		//Stops so the user is able to enter a new message with the city name
    		waitingForCity = false;
    		try {
    			//Calls the WeatherBot getTemp method with the new message that contains the city name
				sendMessage(channel, WeatherBot.getTemp(message));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	
    	//Recognizes the key word "city"
    	if (message.contains("city")) {
         	sendMessage(channel, "Enter City Name: ");
         	//Makes waitingForCity boolean expression true so that upper if statement is able to run
         	waitingForCity = true;	
         }
    	
////////////////////////////////////////////////////////////////////////////////////////////////////////////	//WeatherBot using the Zip Code
    	
    	//After waitingForZip becomes true new message is recorded and used here
    	if (waitingForZip) {
    		//Stops so the user is able to enter a new message with the zip code
    		waitingForZip = false;
    		try {
    			//Calls the WeatherBot getTemp method with the new message that contains the zip code
				sendMessage(channel, WeatherBot2.getTemp(message));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	
    	//Recognizes the key word "zip"
    	if (message.contains("zip")) {
    		//Makes waitingForZip boolean expression true so that upper if statement is able to run
         	sendMessage(channel, "Enter Zipcode: ");
         	waitingForZip = true;	
         }
////////////////////////////////////////////////////////////////////////////////////////////////////////////	//Calls time method
       
    	//Calls time method if time is recognized in a sentence
    	if (message.contains("time")) {
            String time = new java.util.Date().toString();
            sendMessage(channel, sender + ": The time is now " + time);
        }
        
////////////////////////////////////////////////////////////////////////////////////////////////////////////	//ChuckBot  
       
    	//Calls ChuckBot getFact method if the word "chuck" is recognized
    	if (message.contains("chuck")) {
        try {
			sendMessage(channel, ChuckBot.getFact());
		} catch (IOException e) {
			
			e.printStackTrace();
			}
        }
////////////////////////////////////////////////////////////////////////////////////////////////////////////	//Hello
        
    	//Tells the user Hello using their name
    	if (message.contains("hello")) {
        	sendMessage(channel, "Hey " + sender + "!");
        }
////////////////////////////////////////////////////////////////////////////////////////////////////////////
    }       
}