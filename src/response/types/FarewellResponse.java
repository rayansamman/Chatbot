package response.types;

import response.Response;

public class FarewellResponse implements Response {
    private final String message;

    public FarewellResponse(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
