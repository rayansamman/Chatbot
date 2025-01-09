package response;

public class DefaultResponse implements Response {
    @Override
    public String getMessage() {
        return "Sorry, I don't understand that command. Try 'help' for a list of commands.";
    }
}

