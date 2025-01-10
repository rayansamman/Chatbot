package response.types;

import app.ChatbotConfig;
import response.Response;

public class FarewellResponse implements Response {
    @Override
    public String getMessage() {
        String mood = ChatbotConfig.getInstance().getMood();

        switch (mood.toLowerCase()) {
            case "happy":
                return "Goodbye! Hope to see you soon!";
            case "grumpy":
                return "Finally, you're leaving. Bye.";
            default:
                return "Goodbye! Have a great day!";
        }
    }
}
