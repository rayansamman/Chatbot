package chatbot.response.types;

import chatbot.response.Response;

public class HelpResponse implements Response {
    @Override
    public String getMessage() {
        return "Here are some things I can do: say 'hello', say 'bye', or ask for help.";
    }
}
