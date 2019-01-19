import java.io.IOException;
import org.jibble.pircbot.*;

public class BotProject extends PircBot {

    public BotProject() throws IOException {
        this.setName("Anna");
        this.setVerbose(true);
        try {
            this.connect("port80a.se.quakenet.org");
        } catch (IrcException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
        this.joinChannel("#group1-lernia");
    }

    @Override
    public void onMessage(String channel, String sender,
            String login, String hostname, String message) {
    	String command = message.substring(0, message.indexOf(" ")).toLowerCase();
    	String searchValue = message.substring(message.indexOf(" ")).toLowerCase();
    	
        switch (command) {
		case "time":
			String time = new java.util.Date().toString();
            sendMessage(channel, sender + ": The time is now " + time);
			break;
		case "movie":
			
			break;
		case "tv":
			
			break;

		default:
			break;
		}
    }
}
