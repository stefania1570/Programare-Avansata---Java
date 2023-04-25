import java.util.ArrayList;
import java.util.List;

public class SharedMemory {

    private final List<Token> tokens = new ArrayList<>();

    public SharedMemory(int n) {
        for (int i = 0; i < n; i++) {
            int randomNr = (int) (Math.random() * 1000);
            Token localToken = new Token(randomNr);
            tokens.add(localToken);
        }
    }

    public synchronized List<Token> extractTokens(int howMany, Robot robot) {
        List<Token> extracted = new ArrayList<>();
        for (int i = 0; i < howMany; i++) {
            if (tokens.isEmpty()) {
                break;
            }
            extracted.add(tokens.get(i));
            robot.getExtractedTokens().add(tokens.get(i));
            tokens.remove(i);
        }
        return extracted;
    }
}