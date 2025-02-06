package response.types;

import response.Response;

 public class GreetingResponse implements Response {
    private final String message;

    public GreetingResponse(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
