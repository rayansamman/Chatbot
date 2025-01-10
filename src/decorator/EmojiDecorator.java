package decorator;

import app.ChatbotConfig;
import response.Response;

public class EmojiDecorator implements Response {
    private final Response response;

    public EmojiDecorator(Response response) {
        this.response = response;
    }

    @Override
    public String getMessage() {
        ChatbotConfig config = ChatbotConfig.getInstance();
        String mood = config.getMood();

        String message = response.getMessage();
        return switch (mood.toLowerCase()) {
            case "happy" -> message + " 😊";
            case "grumpy" -> "Ugh... " + message + " 🙄";
            default -> // Neutral
                    message;
        };
    }
}
