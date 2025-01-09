import behavioral.FAQStrategy;
import behavioral.ResponseStrategy;
import behavioral.SmallTalkStrategy;

public class StrategyTest {
    public static void main(String[] args) {
        ResponseStrategy smallTalk = new SmallTalkStrategy();
        ResponseStrategy faq = new FAQStrategy();

        System.out.println("Testing Strategy Pattern:");

        // Small Talk Strategy
        System.out.println("Small Talk: 'how are you' → " + smallTalk.generateResponse("how are you"));
        System.out.println("Small Talk: 'tell me something' → " + smallTalk.generateResponse("tell me something"));

        // FAQ Strategy
        System.out.println("FAQ: 'help' → " + faq.generateResponse("help"));
        System.out.println("FAQ: 'pricing' → " + faq.generateResponse("pricing"));
    }
}
