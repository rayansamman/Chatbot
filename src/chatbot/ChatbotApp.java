package chatbot;

import java.util.Scanner;

public class ChatbotApp {
    public static void main(String[] args) {
        // Initialize chatbot configuration
        ChatbotConfig config = ChatbotConfig.getInstance();
        config.setBotName("AssistantBot");

        // Welcome message
        Response welcome = new EmojiDecorator(new TextFormatterDecorator(new GreetingResponse()));
        System.out.println("Chatbot (" + config.getBotName() + "): " + welcome.getMessage());
        System.out.println("Type 'greeting', 'farewell', 'help', or 'exit' to quit.");

        Scanner scanner = new Scanner(System.in);
        String input;

        while (true) {
            System.out.print("You: ");
            input = scanner.nextLine();

            // Exit condition
            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Chatbot: Goodbye!");
                break;
            }

            // Handle user input
            handleUserInput(input);
        }

        scanner.close();
    }

    private static void handleUserInput(String input) {
        try {
            Response response = ResponseFactory.createResponse(input);

            // Dynamically decorate the response
            Response decoratedResponse = new EmojiDecorator(new TextFormatterDecorator(response));
            System.out.println("Chatbot: " + decoratedResponse.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Chatbot: Sorry, I didn't understand that. Type 'help' for a list of commands.");
        }
    }
}
