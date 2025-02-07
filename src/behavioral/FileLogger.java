package behavioral;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileLogger implements EventObserver {
    private static final String LOG_FILE = "chatbot_logs.txt";
    private static final String ERROR_LOG_FILE = "chatbot_errors.txt";
    private static final DateTimeFormatter TIMESTAMP_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public void onEvent(String event) {
        String timestamp = LocalDateTime.now().format(TIMESTAMP_FORMAT);
        String logEntry = "[" + timestamp + "] [LOG]: " + event;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE, true))) {
            writer.write(logEntry);
            writer.newLine();
            writer.flush(); // Ensure immediate write
        } catch (IOException e) {
            logError("Error writing to log file: " + e.getMessage());
        }
    }

    private void logError(String errorMessage) {
        String timestamp = LocalDateTime.now().format(TIMESTAMP_FORMAT);
        String errorEntry = "[" + timestamp + "] [ERROR]: " + errorMessage;

        try (BufferedWriter errorWriter = new BufferedWriter(new FileWriter(ERROR_LOG_FILE, true))) {
            errorWriter.write(errorEntry);
            errorWriter.newLine();
            errorWriter.flush();
        } catch (IOException e) {
            System.err.println("Critical error: Unable to write to error log.");
            e.printStackTrace();
        }
    }
}
