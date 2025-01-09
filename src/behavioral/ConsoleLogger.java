package behavioral;

public class ConsoleLogger implements EventObserver {
    @Override
    public void onEvent(String event) {
        System.out.println("[LOG]: " + event);
    }
}
