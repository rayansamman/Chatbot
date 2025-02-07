package app;

import behavioral.*;
import decorator.EmojiDecorator;
import decorator.TextFormatterDecorator;
import response.Response;
import response.factory.ResponseFactory;
import response.factory.ResponseFactorySelector;

import java.util.Scanner;

public class ChatbotApp {
    private static ResponseStrategy currentStrategy = new SmallTalkStrategy();
    private static final EventManager eventManager = new EventManager();

    public static void main(String[] args) {
        // Load configuration
        ChatbotConfig.loadConfig("config.properties");
        ChatbotConfig config = ChatbotConfig.getInstance();
        config.printConfig();

        InputAdapter inputAdapter = new UserInputAdapter();
        eventManager.addObserver(new ConsoleLogger());
        eventManager.addObserver(new FileLogger());

        // Welcome message
        ResponseFactory responseFactory = ResponseFactorySelector.getFactory();
        Response welcome = responseFactory.createGreetingResponse();
        welcome = new EmojiDecorator(new TextFormatterDecorator(welcome));
        System.out.println("Chatbot (" + config.getBotName() + "): " + welcome.getMessage());

        Scanner scanner = new Scanner(System.in);
        boolean jokesMode = false;

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
                eventManager.notifyObservers("Chatbot name changed to: " + newName);
                System.out.println("Chatbot: You can now call me " + newName + "!");
                continue;
            }

            if (userInput.startsWith("set mood ")) {
                String newMood = userInput.replace("set mood ", "");
                ChatbotConfig.getInstance().setMood(newMood);
                eventManager.notifyObservers("Chatbot mood changed to: " + newMood);
                System.out.println("Chatbot: Mood changed to " + newMood + ".");
                continue;
            }

            if (userInput.equalsIgnoreCase("faq")) {
                setStrategy(new FAQStrategy());  // ✅ Uses setStrategy()
                System.out.println("Chatbot: Switched to FAQ mode.");
                continue;
            }

            if (userInput.equalsIgnoreCase("joke")) {
                setStrategy(new JokeStrategy());  // ✅ Uses setStrategy()
                System.out.println("Chatbot: Switched to Joke mode.");
                continue;
            }

            // Handle predefined commands
            if (userInput.equalsIgnoreCase("greeting") || userInput.equalsIgnoreCase("farewell") || userInput.equalsIgnoreCase("help")) {
                handlePredefinedCommands(userInput);
                continue;
            }

            // Generate a response using the current strategy
            String response = currentStrategy.generateResponse(userInput);
            System.out.println("Chatbot: " + response);
            eventManager.notifyObservers("User input processed: " + userInput);
        }
        scanner.close();
    }

    // ✅ New method for dynamic strategy switching
    private static void setStrategy(ResponseStrategy strategy) {
        eventManager.notifyObservers("Strategy changed to: " + strategy.getClass().getSimpleName());
        currentStrategy = strategy;
    }

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

        Response decoratedResponse = new EmojiDecorator(new TextFormatterDecorator(response));
        System.out.println("Chatbot: " + decoratedResponse.getMessage());
    }
}
