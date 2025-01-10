package tests;

import behavioral.ConsoleLogger;
import behavioral.EventManager;
import behavioral.EventObserver;

public class ObserverTest {
    public static void main(String[] args) {
        // Create Event Manager and Logger
        EventManager eventManager = new EventManager();
        EventObserver logger = new ConsoleLogger();

        // Register the observer
        eventManager.addObserver(logger);

        // Notify events
        System.out.println("Testing Observer Pattern:");
        eventManager.notifyObservers("User entered the chat.");
        eventManager.notifyObservers("User switched to FAQ mode.");
        eventManager.notifyObservers("User exited the chat.");
    }
}
