import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SharedMemory {
    private List<Token> tokens;

    /**
     * Constructor for the shared memory
     * Adds the tokens and shuffles them
     *
     * @param n int
     */
    public SharedMemory(int n) {
        tokens = new ArrayList<>();

        for (int i = 0; i < n * n * n; i++) {
            tokens.add(new Token(i));
        }
        Collections.shuffle(tokens);
    }

    /**
     * Extracts the tokens
     *
     * @param howMany number of tokens to extract
     * @return list of tokens
     */
    public synchronized List<Token> extractTokens(int howMany) {
        List<Token> extracted = new ArrayList<>();
        for (int i = 0; i < howMany; i++) {
            if (tokens.isEmpty()) {
                break;
            }
            extracted.add(tokens.get(tokens.size() - 1));
            tokens.remove(tokens.size() - 1);
        }
        return extracted;
    }
}
