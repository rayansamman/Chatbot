package behavioral;

public class JokeAPIAdapter implements ExternalAPIAdapter {
    private final JokeAPI jokeAPI;

    public JokeAPIAdapter(JokeAPI jokeAPI) {
        this.jokeAPI = jokeAPI;
    }

    @Override
    public String getResponse(String input) {
        String rawJoke = jokeAPI.fetchJoke();
        if (rawJoke.startsWith("Error")) {
            return rawJoke;
        }
        return parseJokeFromResponse(rawJoke);
    }


    private String parseJokeFromResponse(String rawResponse) {
        try {
            String setup = rawResponse.split("\"setup\":\"")[1].split("\",")[0];
            String punchline = rawResponse.split("\"punchline\":\"")[1].split("\"}")[0];
            return setup + " " + punchline;
        } catch (Exception e) {
            return "Error parsing joke: " + e.getMessage();
        }
    }

}

