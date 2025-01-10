package app;




import java.io.*;
import java.util.Properties;

public class ChatbotConfig implements Serializable {
    private static final long serialVersionUID = 1L;
    private static volatile ChatbotConfig instance;

    // Configuration properties
    private String botName;
    private String mood;

    private ChatbotConfig() {
        this.botName = "Kato";
        this.mood = "Neutral";
    }

    public static ChatbotConfig getInstance() {
        if (instance == null) {
            synchronized (ChatbotConfig.class) {
                if (instance == null) {
                    instance = new ChatbotConfig();
                }
            }
        }
        return instance;
    }

    public String getBotName() {
        return botName;
    }

    public void setBotName(String botName) {
        logChange("Bot name", this.botName, botName);
        this.botName = botName;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        logChange("Mood", this.mood, mood);
        this.mood = mood;
    }

    // Log changes to configurations
    private void logChange(String property, String oldValue, String newValue) {
        System.out.println(property + " changed from '" + oldValue + "' to '" + newValue + "'.");
    }

    // Save configuration to a file
    public void saveConfig(String filePath) {
        Properties properties = new Properties();
        properties.setProperty("botName", this.botName);
        properties.setProperty("mood", this.mood);

        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            properties.store(fos, "Chatbot Configuration");
            System.out.println("Configuration saved to " + filePath);
        } catch (IOException e) {
            System.err.println("Error saving configuration: " + e.getMessage());
        }
    }

    public static void loadConfig(String filePath) {
        try (FileInputStream fis = new FileInputStream(filePath)) {
            Properties properties = new Properties();
            properties.load(fis);

            ChatbotConfig config = getInstance();
            config.setBotName(properties.getProperty("botName", "DefaultBot"));
            config.setMood(properties.getProperty("mood", "Neutral"));

            System.out.println("Configuration loaded from " + filePath);
        } catch (IOException e) {
            System.err.println("Error loading configuration: " + e.getMessage());
        }
    }

    private Object readResolve() {
        return getInstance();
    }

    public void printConfig() {
        System.out.println("Chatbot Configuration:");
        System.out.println("Bot Name: " + botName);
        System.out.println("Mood: " + mood);
    }

    @Override
    public String toString() {
        return "ChatbotConfig {" +
                "botName='" + botName + '\'' +
                ", language='" + language + '\'' +
                '}';
    }
}
