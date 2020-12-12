//Main File
public class MyBotMain {
    
    public static void main(String[] args) throws Exception {
        
        //Instance of bot created
        MyBot bot = new MyBot();
        
        //Enable debugging output.
        bot.setVerbose(true);
        
        //Connect to the IRC server.
        bot.connect("irc.freenode.net");

        //Join the #fac channel.
        bot.joinChannel("#fac");
        
        //Send Message once bot joins the channel
        bot.sendMessage("#fac", "Hello I Am A Bot, You Can Ask Me For The Weather (Using The 'City' Or 'Zip' Code) Or 'Chuck' Norris Facts!");
    }
    
}