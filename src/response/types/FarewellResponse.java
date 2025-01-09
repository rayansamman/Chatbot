package response.types;

import response.Response;

public class FarewellResponse implements Response {
    @Override
    public String getMessage() {
        return "Goodbye! Have a great day!";
    }
}
