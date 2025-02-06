package app;

import decorator.EmojiDecorator;
import decorator.TextFormatterDecorator;
import response.Response;
import response.factory.ResponseFactory;
import response.factory.ResponseFactorySelector;
import response.types.GreetingResponse;
import behavioral.InputAdapter;
import behavioral.UserInputAdapter;
import behavioral.ResponseStrategy;
import behavioral.SmallTalkStrategy;
import behavioral.FAQStrategy;
import behavioral.EventManager;
import behavioral.ConsoleLogger;
import behavioral.ExternalAPIAdapter;
import behavioral.FakeJokeAPIAdapter;
import behavioral.JokeStrategy;
import behavioral.FileLogger;

import java.util.Scanner;

public class ChatbotApp {
    public static void main(String[] args) {
        // Load configuration from a file
        ChatbotConfig.loadConfig("config.properties");

        // Print the loaded settings
        ChatbotConfig config = ChatbotConfig.getInstance();
        config.printConfig();

        InputAdapter inputAdapter = new UserInputAdapter();
        ResponseStrategy currentStrategy = new SmallTalkStrategy();
        EventManager eventManager = new EventManager();
        eventManager.addObserver(new ConsoleLogger());
        eventManager.addObserver(new FileLogger());

        // Initial welcome message with mood-based response
        ResponseFactory responseFactory = ResponseFactorySelector.getFactory();
        Response welcome = responseFactory.createGreetingResponse();
        welcome = new EmojiDecorator(new TextFormatterDecorator(welcome));  // Apply dynamic decorators
        System.out.println("Chatbot (" + config.getBotName() + "): " + welcome.getMessage());

        System.out.println("Type 'greeting', 'farewell', 'help', 'faq', 'joke', 'jokeapi', or 'exit' to quit.");

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

            // Switch to Joke strategy
            if (userInput.equalsIgnoreCase("joke")) {
                currentStrategy = new JokeStrategy();
                eventManager.notifyObservers("Switched to Joke mode.");
                System.out.println("Chatbot: Switched to Joke mode.");
                continue;
            }

            // Handle joke using ExternalAPIAdapter
            if (userInput.equalsIgnoreCase("jokeapi")) {
                ExternalAPIAdapter jokeAPI = new FakeJokeAPIAdapter();
                System.out.println("Chatbot: " + jokeAPI.getResponse(userInput));
                continue;
            }

            // Handle decorated responses for predefined commands
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
        ResponseFactory responseFactory = ResponseFactorySelector.getFactory();

        Response response;
        switch (input.toLowerCase()) {
            case "greeting":
                response = responseFactory.createGreetingResponse();
                break;
            case "farewell":
                response = responseFactory.createFarewellResponse();
                break;
            case "help":
                response = responseFactory.createHelpResponse();
                break;
            default:
                System.out.println("Chatbot: I didn't understand that command.");
                return;
        }

        // Apply decorators dynamically based on mood
        Response decoratedResponse = new EmojiDecorator(new TextFormatterDecorator(response));
        System.out.println("Chatbot: " + decoratedResponse.getMessage());
    }
}
