package behavioral;

public class SmallTalkStrategy implements ResponseStrategy {
    @Override
    public String generateResponse(String input) {
        return "I'm just a bot, but I'm doing great! How can I help you?";
    }
}
