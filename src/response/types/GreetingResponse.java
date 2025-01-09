package response.types;

import app.ChatbotConfig;
import response.Response;

public class GreetingResponse implements Response {
    @Override
    public String getMessage() {
        String mood = ChatbotConfig.getInstance().getMood();

        return switch (mood.toLowerCase()) {
            case "happy" -> "Hey there! So happy to see you! 😊";
            case "grumpy" -> "Oh... it's you. What do you want? 🙄";
            default -> "Hello! How can I assist you?";
        };
    }
}
