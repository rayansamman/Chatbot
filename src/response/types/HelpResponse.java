package response.types;

import response.Response;

public class HelpResponse implements Response {
    @Override
    public String getMessage() {
        return """
                Here are some things I can do:
                - Say 'greeting' to get a warm welcome.
                - Say 'farewell' to say goodbye.
                - Say 'help' to see this message again.
                - Use 'set name [name]' to change my name.
                - Use 'set mood [mood]' to change my mood (e.g., happy, grumpy).
                - Type 'faq' to switch to FAQ mode.
                - Type 'Tell me jokes' to switch to Joke mode.
                - Type 'exit' to leave the chat.
                """;
    }
}
