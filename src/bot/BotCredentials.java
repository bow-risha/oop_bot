package bot;

public class BotCredentials {
    private  String botName;
    private String botKey;
    public BotCredentials(){

    }
    public BotCredentials(String botName, String botKey){
        this.botKey = botKey;
        this.botName = botName;
    }

    public String getBotKey() {
        return botKey;
    }

    public void setBotKey(String botKey) {
        this.botKey = botKey;
    }

    public String getBotName() {
        return botName;
    }

    public void setBotName(String botName) {
        this.botName = botName;
    }
}
