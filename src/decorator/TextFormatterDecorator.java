package decorator;

import response.Response;
import response.ResponseDecorator;
import app.ChatbotConfig;

public class TextFormatterDecorator extends ResponseDecorator {
    private final String prefix;
    private final String suffix;

    public TextFormatterDecorator(Response response) {
        super(response);
        // Default prefix/suffix
        this.prefix = "<<<";
        this.suffix = ">>>";
    }

    public TextFormatterDecorator(Response response, String prefix, String suffix) {
        super(response);
        this.prefix = prefix;
        this.suffix = suffix;
    }

    @Override
    public String getMessage() {
        String mood = ChatbotConfig.getInstance().getMood();

        if ("formal".equalsIgnoreCase(mood)) {
            return "[" + response.getMessage() + "]";
        } else if ("casual".equalsIgnoreCase(mood)) {
            return "~" + response.getMessage() + "~";
        } else if ("grumpy".equalsIgnoreCase(mood)) {
            return new UppercaseDecorator(response).getMessage();
        }

        // Default formatting
        return prefix + response.getMessage() + suffix;
    }
}
