package decorator;

import response.Response;

public class UppercaseDecorator implements Response {
    private final Response response;

    public UppercaseDecorator(Response response) {
        this.response = response;
    }

    @Override
    public String getMessage() {
        return response.getMessage().toUpperCase();
    }
}
