package decorator;

import response.Response;
import response.ResponseDecorator;

public class TextFormatterDecorator extends ResponseDecorator {
    public TextFormatterDecorator(Response response) {
        super(response);
    }

    @Override
    public String getMessage() {
        return " " + response.getMessage() + " ";
    }
}
