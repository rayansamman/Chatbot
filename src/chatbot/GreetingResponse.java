package chatbot;

public class GreetingResponse implements Response {
    @Override
    public String getMessage() {
        return "Hello! How can I help you today?";
    }
}
