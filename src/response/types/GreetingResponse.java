package response.types;

import response.Response;

public class GreetingResponse implements Response {
    @Override
    public String getMessage() {
        return "Hello! How can I help you today?";
    }
}
