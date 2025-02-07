package behavioral;

import java.io.FileWriter;
import java.io.IOException;

public class FileLogger implements EventObserver {
    private static final String FILE_NAME = "chatbot_logs.txt";

    @Override
    public void onEvent(String event) {
        try (FileWriter writer = new FileWriter(FILE_NAME, true)) {
            writer.write("[LOG]: " + event + "\n");
        } catch (IOException e) {
            System.err.println("Error writing to log file: " + e.getMessage());
        }
    }
}
