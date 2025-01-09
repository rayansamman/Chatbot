package chatbot.response.types;

import chatbot.response.Response;

public class GreetingResponse implements Response {
    @Override
    public String getMessage() {
        return "Hello! How can I help you today?";
    }
}
