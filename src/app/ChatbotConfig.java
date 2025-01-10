package app;

public class ChatbotConfig {
    private static ChatbotConfig instance; // Singleton instance
    private String botName;               // Name of the chatbot
    private String language;              // Language setting for the chatbot

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

    @Override
    public String toString() {
        return "ChatbotConfig {" +
                "botName='" + botName + '\'' +
                ", language='" + language + '\'' +
                '}';
    }
}