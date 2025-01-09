package app;

import decorator.EmojiDecorator;
import decorator.TextFormatterDecorator;
import response.Response;
import response.ResponseFactory;
import response.types.GreetingResponse;
import behavioral.InputAdapter;
import behavioral.UserInputAdapter;
import behavioral.ResponseStrategy;
import behavioral.SmallTalkStrategy;
import behavioral.FAQStrategy;
import behavioral.EventManager;
import behavioral.ConsoleLogger;

import java.util.Scanner;

public class ChatbotApp {
    public static void main(String[] args) {
        // Initialize chatbot configuration
        ChatbotConfig config = ChatbotConfig.getInstance();
        config.setBotName("AssistantBot");

        // Initialize behavioral.InputAdapter, Strategy, and behavioral.EventManager
        InputAdapter inputAdapter = new UserInputAdapter();
        ResponseStrategy currentStrategy = new SmallTalkStrategy(); // Default strategy
        EventManager eventManager = new EventManager();
        eventManager.addObserver(new ConsoleLogger());

        // Welcome message with decorators
        Response welcome = new EmojiDecorator(new TextFormatterDecorator(new GreetingResponse()));
        System.out.println("Chatbot (" + config.getBotName() + "): " + welcome.getMessage());
        System.out.println("Type 'greeting', 'farewell', 'help', 'faq', or 'exit' to quit.");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("You: ");
            String userInput = scanner.nextLine();

            userInput = inputAdapter.adaptInput(userInput);

            if (userInput.equalsIgnoreCase("exit")) {
                eventManager.notifyObservers("User exited the chatbot.");
                System.out.println("Chatbot: Goodbye!");
                break;
            }

            // Switch to FAQ strategy
            if (userInput.equalsIgnoreCase("faq")) {
                currentStrategy = new FAQStrategy();
                eventManager.notifyObservers("Switched to FAQ mode.");
                System.out.println("Chatbot: Switched to FAQ mode.");
                continue;
            }

            // Handle decorated responses for predefined commands
            if (userInput.equalsIgnoreCase("greeting") || userInput.equalsIgnoreCase("farewell") || userInput.equalsIgnoreCase("help")) {
                handlePredefinedCommands(userInput);
                continue;
            }

            String response = currentStrategy.generateResponse(userInput);
            System.out.println("Chatbot: " + response);

            eventManager.notifyObservers("User input processed: " + userInput);
        }

        scanner.close();
    }

    // Handle predefined commands with decorators
    private static void handlePredefinedCommands(String input) {
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
