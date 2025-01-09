package behavioral;

public class FAQStrategy implements ResponseStrategy {
    @Override
    public String generateResponse(String input) {
        if (input.contains("help")) {
            return "Sure! I can help you with your questions. Please ask.";
        } else if (input.contains("pricing")) {
            return "Our pricing details are available on the website.";
        } else if (input.contains("support")) {
            return "You can contact support at support@example.com.";
        }
        return "I'm here to assist with FAQs. What do you need help with?";
    }
}
