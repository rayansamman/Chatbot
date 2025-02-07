package app;

import behavioral.*;
import decorator.EmojiDecorator;
import decorator.TextFormatterDecorator;
import response.Response;
import response.factory.ResponseFactory;
import response.factory.ResponseFactorySelector;

import java.util.Scanner;

public class ChatbotApp {
    private ResponseStrategy currentStrategy;
    private final EventManager eventManager;

    public ChatbotApp() {
        this.currentStrategy = new SmallTalkStrategy(); // Default strategy
        this.eventManager = new EventManager();
        this.eventManager.addObserver(new ConsoleLogger());
        this.eventManager.addObserver(new FileLogger());
    }

    public void setStrategy(ResponseStrategy strategy) {
        this.currentStrategy = strategy;
        eventManager.notifyObservers("Chatbot strategy changed to: " + strategy.getClass().getSimpleName());
    }

    public void run() {
        // Load configuration from a file
        ChatbotConfig.loadConfig("config.properties");

        // Print the loaded settings
        ChatbotConfig config = ChatbotConfig.getInstance();
        config.printConfig();

        InputAdapter inputAdapter = new UserInputAdapter();
        
        // Joke strategy with API integration
        ExternalAPIAdapter jokeAPIAdapter = new JokeAPIAdapter(new JokeAPI());
        JokeStrategy jokeStrategy = new JokeStrategy(jokeAPIAdapter);

        // Initial welcome message with mood-based response
        ResponseFactory responseFactory = ResponseFactorySelector.getFactory();
        Response welcome = responseFactory.createGreetingResponse();
        welcome = new EmojiDecorator(new TextFormatterDecorator(welcome));
        System.out.println("Chatbot (" + config.getBotName() + "): " + welcome.getMessage());
        
        System.out.println("Type 'greeting', 'farewell', 'help', 'faq', 'jokes', or 'exit' to quit.");
        
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
                eventManager.notifyObservers("Bot name changed to: " + newName);
                System.out.println("Chatbot: You can now call me " + newName + "!");
                continue;
            }

            if (userInput.startsWith("set mood ")) {
                String newMood = userInput.replace("set mood ", "");
                ChatbotConfig.getInstance().setMood(newMood);
                eventManager.notifyObservers("Bot mood changed to: " + newMood);
                System.out.println("Chatbot: Mood changed to " + newMood + ".");
                continue;
            }

            if (userInput.equalsIgnoreCase("faq")) {
                setStrategy(new FAQStrategy());
                System.out.println("Chatbot: Switched to FAQ mode.");
                continue;
            }

            // Switch to Joke strategy
            if (userInput.equalsIgnoreCase("Tell me jokes")) {
                jokesMode = true;
                System.out.println("Chatbot: Welcome to Jokes Mode! Type 'more' for local jokes or 'I want something new' for API jokes.");
                continue;
            }

            // Handle joke-related input in Jokes Mode
            if (jokesMode) {
                String jokeResponse = jokeStrategy.generateResponse(userInput);
                System.out.println("Chatbot: " + jokeResponse);
                if (userInput.equalsIgnoreCase("exit jokes")) {
                    jokesMode = false;
                    System.out.println("Chatbot: Exiting Jokes Mode.");
                }
                continue;
            }

            // Handle decorated responses for predefined commands
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

    // Handle predefined commands with decorators
    private void handlePredefinedCommands(String input) {
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

    public static void main(String[] args) {
        ChatbotApp chatbotApp = new ChatbotApp();
        chatbotApp.run();
    }
}
