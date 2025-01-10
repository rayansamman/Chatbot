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
        // Load configuration from a file
        ChatbotConfig.loadConfig("config.properties");

        // Print the loaded settings
        ChatbotConfig config = ChatbotConfig.getInstance();
        config.printConfig();

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
                System.out.println("Chatbot: Saving your preferences...");
                ChatbotConfig.getInstance().saveConfig("config.properties");
                System.out.println("Chatbot: Preferences saved. Goodbye!");
                break;
            }

            if (userInput.startsWith("set name ")) {
                String newName = userInput.replace("set name ", "");
                ChatbotConfig.getInstance().setBotName(newName);
                System.out.println("Chatbot: You can now call me " + newName + "!");
                continue;
            }

            if (userInput.startsWith("set mood ")) {
                String newMood = userInput.replace("set mood ", "");
                ChatbotConfig.getInstance().setMood(newMood);
                System.out.println("Chatbot: Mood changed to " + newMood + ".");
                continue;
            }

            if (userInput.equalsIgnoreCase("faq")) {
                currentStrategy = new FAQStrategy();
                eventManager.notifyObservers("Switched to FAQ mode.");
                System.out.println("Chatbot: Switched to FAQ mode.");
                continue;
            }

            if (userInput.equalsIgnoreCase("greeting") || userInput.equalsIgnoreCase("farewell") || userInput.equalsIgnoreCase("help")) {
                handlePredefinedCommands(userInput);
                continue;
            }

            String response = currentStrategy.generateResponse(userInput);
            System.out.println("Chatbot: " + response);

            // Notify observers about the processed input
            eventManager.notifyObservers("User input processed: " + userInput);
        }

        scanner.close();
    }

    // Handle predefined commands with decorators
    private static void handlePredefinedCommands(String input) {
        // Use the factory to create a response
        Response response = ResponseFactory.createResponse(input);

        Response decoratedResponse = new EmojiDecorator(new TextFormatterDecorator(response));
        System.out.println("Chatbot: " + decoratedResponse.getMessage());
    }
}