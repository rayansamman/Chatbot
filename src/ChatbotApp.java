import java.util.Scanner;

public class ChatbotApp {
    public static void main(String[] args) {
        InputAdapter inputAdapter = new UserInputAdapter();
        ResponseStrategy currentStrategy = new SmallTalkStrategy(); // Default strategy
        EventManager eventManager = new EventManager();
        eventManager.addObserver(new ConsoleLogger());

        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Chatbot! Type 'exit' to quit. Type 'faq' to switch to FAQ mode.");

        while (true) {
            System.out.print("You: ");
            String userInput = scanner.nextLine();

            // Adapt the input
            userInput = inputAdapter.adaptInput(userInput);

            if (userInput.equals("exit")) {
                eventManager.notifyObservers("User exited the chatbot.");
                System.out.println("Chatbot: Goodbye!");
                break;
            }

            if (userInput.equals("faq")) {
                currentStrategy = new FAQStrategy();
                eventManager.notifyObservers("Switched to FAQ mode.");
                System.out.println("Chatbot: Switched to FAQ mode.");
                continue;
            }

            // Generate a response using the current strategy
            System.out.println("Chatbot: " + currentStrategy.generateResponse(userInput));
        }

        scanner.close();
    }
}
