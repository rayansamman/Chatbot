package chatbot;

public class EmojiDecorator extends ResponseDecorator {
    public EmojiDecorator(Response response) {
        super(response);
    }

    @Override
    public String getMessage() {
        return response.getMessage() + " 😊";
    }
}