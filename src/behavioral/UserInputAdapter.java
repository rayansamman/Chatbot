package behavioral;

public class UserInputAdapter implements InputAdapter {
    @Override
    public String adaptInput(String input) {
        return input.trim().toLowerCase(); // Trim spaces and convert to lowercase
    }
}
