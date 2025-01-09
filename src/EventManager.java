import java.util.ArrayList;
import java.util.List;

public class EventManager {
    private List<EventObserver> observers = new ArrayList<>();

    public void addObserver(EventObserver observer) {
        observers.add(observer);
    }

    public void notifyObservers(String event) {
        for (EventObserver observer : observers) {
            observer.onEvent(event);
        }
    }
}
