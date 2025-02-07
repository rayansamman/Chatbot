package app;

import behavioral.*;
import decorator.EmojiDecorator;
import decorator.TextFormatterDecorator;
import response.Response;
import response.factory.ResponseFactory;
import response.factory.ResponseFactorySelector;

import java.util.Scanner;

public class ChatbotApp {
    private static ResponseStrategy currentStrategy = new SmallTalkStrategy(); // Default strategy
    private static EventManager eventManager = new EventManager();
    private static boolean jokesMode = false;
    private static JokeStrategy jokeStrategy;

    public static void main(String[] args) {
        // Load configuration from a file
        ChatbotConfig.loadConfig("config.properties");
        ChatbotConfig config = ChatbotConfig.getInstance();
        config.printConfig();

        InputAdapter inputAdapter = new UserInputAdapter();
        eventManager.addObserver(new ConsoleLogger());
        eventManager.addObserver(new FileLogger());

        // Initialize joke strategy with API integration
        ExternalAPIAdapter jokeAPIAdapter = new FakeJokeAPIAdapter(); 
        jokeStrategy = new JokeStrategy(jokeAPIAdapter);

        // Welcome message
        ResponseFactory responseFactory = ResponseFactorySelector.getFactory();
        Response welcome = responseFactory.createGreetingResponse();
        welcome = new EmojiDecorator(new TextFormatterDecorator(welcome));
        System.out.println("Chatbot (" + config.getBotName() + "): " + welcome.getMessage());

        System.out.println("Type 'greeting', 'farewell', 'help', 'faq', 'jokes', or 'exit' to quit.");

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
                setStrategy(new FAQStrategy());
                continue;
            }

            if (userInput.equalsIgnoreCase("joke")) {
                setStrategy(jokeStrategy);
                continue;
            }

            // Handle joke mode
            if (jokesMode) {
                System.out.println("Chatbot: " + jokeStrategy.generateResponse(userInput));

                if (userInput.equalsIgnoreCase("exit jokes")) {
                    jokesMode = false;
                    System.out.println("Chatbot: Exiting Jokes Mode.");
                }
                continue;
            }

            if (userInput.equalsIgnoreCase("greeting") || userInput.equalsIgnoreCase("farewell") || userInput.equalsIgnoreCase("help")) {
                handlePredefinedCommands(userInput);
                continue;
            }

            // Generate response using the current strategy
            String response = currentStrategy.generateResponse(userInput);
            System.out.println("Chatbot: " + response);
            eventManager.notifyObservers("User input processed: " + userInput);
        }

        scanner.close();
    }

    public static void setStrategy(ResponseStrategy strategy) {
        currentStrategy = strategy;
        eventManager.notifyObservers("Chatbot strategy changed to: " + strategy.getClass().getSimpleName());
        System.out.println("Chatbot: Switched to " + strategy.getClass().getSimpleName() + " mode.");
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
