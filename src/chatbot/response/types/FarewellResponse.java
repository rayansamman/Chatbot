package chatbot.response.types;

import chatbot.response.Response;

public class FarewellResponse implements Response {
    @Override
    public String getMessage() {
        return "Goodbye! Have a great day!";
    }
}
