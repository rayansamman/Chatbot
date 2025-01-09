public class FakeJokeAPIAdapter implements ExternalAPIAdapter {
    private static final String[] JOKES = {
            "Why don't skeletons fight each other? Because they don’t have the guts!",
            "Why did the scarecrow win an award? Because he was outstanding in his field!",
            "Why don’t scientists trust atoms? Because they make up everything!"
    };

    @Override
    public String getResponse(String input) {
        if (input.equalsIgnoreCase("jokeapi")) {
            int randomIndex = (int) (Math.random() * JOKES.length);
            return JOKES[randomIndex];
        }
        return "No joke found for this input.";
    }
}
