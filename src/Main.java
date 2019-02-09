import com.sipi.groupOne.*;
import com.sipi.groupOne.ui.MainFrame;

public class Main {

    public static void main(String[] args) {
        BotProject bot;
        Thread t = new Thread(bot = new BotProject("port80a.se.quakenet.org", "[GUI]Anna", "#group1-lernia", true));
        // String[] args for cmdline or GUI?
        MainFrame ui = new MainFrame(bot);
        bot.setUi(ui);
        t.start();
    }
}