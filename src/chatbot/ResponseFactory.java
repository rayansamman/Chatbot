package chatbot;

public class ResponseFactory {

    public static Response createResponse(String type) {
        switch (type.toLowerCase()) {
            case "greeting":
                return new GreetingResponse();
            case "farewell":
                return new FarewellResponse();
            default:
                throw new IllegalArgumentException("Unknown response type: " + type);
        }
    }
}
