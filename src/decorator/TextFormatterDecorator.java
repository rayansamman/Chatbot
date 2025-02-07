package decorator;

import response.Response;
import response.ResponseDecorator;
import app.ChatbotConfig;

public class TextFormatterDecorator extends ResponseDecorator {

    public TextFormatterDecorator(Response response) {
        super(response);
    }

    @Override
    public String getMessage() {
        String mood = ChatbotConfig.getInstance().getMood();
        String message = response.getMessage();

        return switch (mood.toLowerCase()) {
            case "happy" -> "~" + message + "~";
            case "grumpy" -> new UppercaseDecorator(response).getMessage();
            default -> "<<<" + message + ">>>";
        };
    }
}
