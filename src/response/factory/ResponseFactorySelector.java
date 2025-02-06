package response.factory;

import app.ChatbotConfig;

public class ResponseFactorySelector {
    public static ResponseFactory getFactory() {
        String mood = ChatbotConfig.getInstance().getMood().toLowerCase();

        switch (mood) {
            case "happy":
                return new HappyResponseFactory();
            case "grumpy":
                return new GrumpyResponseFactory();
            default:
                return new NeutralResponseFactory();
        }
    }
}
