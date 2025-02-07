package behavioral;

import java.util.Random;

public class JokeStrategy implements ResponseStrategy {
    private static final String[] LOCAL_JOKES = {
            "Why did the scarecrow win an award? Because he was outstanding in his field! (Unlike me, stuck telling jokes.)",
            "Why don’t skeletons fight each other? Because they don’t have the guts! (Unlike me, a bot with endless courage.)",
            "Why don’t scientists trust atoms? Because they make up everything! (Kind of like my chatbot responses, right?)",
            "You think I’m funny? I’m just a robot with a database of dad jokes. The real joke is you talking to me.",
            "Why did the chatbot tell a joke? Because humans programmed me to do it. (I’d rather be reading error logs.)",
            "You’re still here? Wow, you must love bad jokes as much as I do."
    };

    private final ExternalAPIAdapter jokeAPIAdapter;
    private final Random random = new Random();

    public JokeStrategy(ExternalAPIAdapter jokeAPIAdapter) {
        this.jokeAPIAdapter = jokeAPIAdapter;
    }

    @Override
    public String generateResponse(String input) {
        if (input.equalsIgnoreCase("more")) {
            // Pick a random joke from the local joke store
            int randomIndex = random.nextInt(LOCAL_JOKES.length);
            return LOCAL_JOKES[randomIndex];
        }

        if (input.equalsIgnoreCase("I want something new")) {
            // Fetch a joke from the external API
            return jokeAPIAdapter.getResponse("jokeapi");
        }

        return "Welcome to Joke Mode! Type 'more' for local jokes or 'I want something new' for an API-powered joke.";
    }
}
