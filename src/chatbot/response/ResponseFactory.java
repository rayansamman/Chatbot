package chatbot.response;

import chatbot.response.types.HelpResponse;
import chatbot.response.types.FarewellResponse;
import chatbot.response.types.GreetingResponse;

public class ResponseFactory {
    public static Response createResponse(String type) {
        switch (type.toLowerCase()) {
            case "greeting":
                return new GreetingResponse();
            case "farewell":
                return new FarewellResponse();
            case "help":
                return new HelpResponse();
            default:
                throw new IllegalArgumentException("Unknown response type: " + type);
        }
    }
}
