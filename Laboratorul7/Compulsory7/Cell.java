import java.util.ArrayList;
import java.util.List;

public class Cell {
    List<Token> tokens;

    /**
     * Constructor for the cell
     */
    Cell() {
        this.tokens = new ArrayList<Token>();
    }

    ;

    /**
     * Getter for the list of tokens
     * @return list of tokens
     */
    List<Token> getTokens() {
        return this.tokens;
    }

    /**
     * Adds a new token to the list
     * @param t Token
     */
    public void add(Token t) {
        this.tokens.add(t);

    }

    /**
     * Overridden toString method to show the tokens list as a string
     * @return string
     */
    @Override
    public String toString() {
        return tokens.toString();
    }
}
