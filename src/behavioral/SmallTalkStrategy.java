package behavioral;

public class SmallTalkStrategy implements ResponseStrategy {
    @Override
    public String generateResponse(String input) {
        if (input.contains("how are you")) {
            return "I'm just a bot, but I'm doing great! How can I help you?";
        }
        return "Let's chat! Tell me something interesting.";
    }
}
