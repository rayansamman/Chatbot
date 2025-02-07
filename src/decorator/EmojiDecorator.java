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
        String mood = ChatbotConfig.getInstance().getMood();
        String message = response.getMessage();

        return switch (mood.toLowerCase()) {
            case "happy" -> message + " 😊";
            case "grumpy" -> message + " 🙄";
            default -> message;
        };
    }
}
