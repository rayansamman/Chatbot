package chatbot;

public class ChatbotApp {
    public static void main(String[] args) {
        Response greeting = ResponseFactory.createResponse("greeting");
        System.out.println(greeting.getMessage());

        // Test Farewell Response
        Response farewell = ResponseFactory.createResponse("farewell");
        System.out.println(farewell.getMessage());

        // Test Unknown Response
        try {
            Response unknown = ResponseFactory.createResponse("unknown");
            System.out.println(unknown.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
