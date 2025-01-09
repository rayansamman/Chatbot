package chatbot;

public class TextFormatterDecorator extends ResponseDecorator {
    public TextFormatterDecorator(Response response) {
        super(response);
    }

    @Override
    public String getMessage() {
        return " " + response.getMessage() + " ";
    }
}
