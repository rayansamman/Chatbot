package behavioral;

import java.util.ArrayList;
import java.util.List;

public class EventManager {
    private final List<EventObserver> observers = new ArrayList<>();

    public void addObserver(EventObserver observer) {
        observers.add(observer);
    }

    public void notifyObservers(String event) {
        System.out.println("[EventManager]: Broadcasting event - " + event); // Debugging log
        for (EventObserver observer : observers) {
            observer.onEvent(event);
        }
    }
}
