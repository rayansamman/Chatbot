package chatbot.response;

public abstract class ResponseDecorator implements Response {
    protected Response response;

    public ResponseDecorator(Response response) {
        this.response = response;
    }
}
