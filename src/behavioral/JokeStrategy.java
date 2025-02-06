package behavioral;

public class JokeStrategy implements ResponseStrategy {
    private final ExternalAPIAdapter jokeAPIAdapter;

    // Constructor to inject API adapter (optional if you prefer API delegation)
    public JokeStrategy(ExternalAPIAdapter jokeAPIAdapter) {
        this.jokeAPIAdapter = jokeAPIAdapter;
    }

    @Override
    public String generateResponse(String input) {
        if (input.equalsIgnoreCase("jokeapi")) {
            return jokeAPIAdapter.getResponse(input);
        }

        // Default fallback: hard-coded joke if not using API
        return "Why did the scarecrow win an award? Because he was outstanding in his field!";
    }
}
