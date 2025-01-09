package chatbot.app;

public class ChatbotConfig {
    private static ChatbotConfig instance;
    private String botName;
    private String language;

    private ChatbotConfig() {
        // Default settings
        this.botName = "Chatbot";
        this.language = "English";
    }

    public static ChatbotConfig getInstance() {
        if (instance == null) {
            instance = new ChatbotConfig();
        }
        return instance;
    }

    public String getBotName() {
        return botName;
    }

    public void setBotName(String botName) {
        this.botName = botName;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
