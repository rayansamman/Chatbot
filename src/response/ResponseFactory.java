package response;

import java.util.HashMap;
import java.util.Map;

public class ResponseFactory {
    private static final Map<String, Class<? extends Response>> registeredResponses = new HashMap<>();

    // Register a response type
    public static void registerResponse(String type, Class<? extends Response> responseClass) {
        registeredResponses.put(type.toLowerCase(), responseClass);
    }

    // Create a response dynamically
    public static Response createResponse(String type) {
        Class<? extends Response> responseClass = registeredResponses.get(type.toLowerCase());
        if (responseClass == null) {
            return new DefaultResponse(); // Return a default response if the type is not found
        }

        try {
            return responseClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Failed to create response of type: " + type, e);
        }
    }

    // Optionally, preload some default responses
    static {
        registerResponse("greeting", response.types.GreetingResponse.class);
        registerResponse("farewell", response.types.FarewellResponse.class);
        registerResponse("help", response.types.HelpResponse.class);
    }
}
