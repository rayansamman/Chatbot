import behavioral.InputAdapter;
import behavioral.UserInputAdapter;

public class AdapterTest {
    public static void main(String[] args) {
        InputAdapter adapter = new UserInputAdapter();

        System.out.println("Testing Adapter Pattern:");
        System.out.println("Original: '  Hello  ' → Adapted: '" + adapter.adaptInput("  Hello  ") + "'");
        System.out.println("Original: 'BYE' → Adapted: '" + adapter.adaptInput("BYE") + "'");
        System.out.println("Original: '   Mixed Case   ' → Adapted: '" + adapter.adaptInput("   Mixed Case   ") + "'");
    }
}
