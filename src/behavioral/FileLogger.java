package behavioral;
import java.io.FileWriter;
import java.io.IOException;

public class FileLogger implements EventObserver {
    @Override
    public void onEvent(String event) {
        try (FileWriter writer = new FileWriter("chatbot_logs.txt", true)) {
            writer.write("[LOG]: " + event + "\n");
        } catch (IOException e) {
            System.err.println("Error writing to log file: " + e.getMessage());
        }
    }
}
