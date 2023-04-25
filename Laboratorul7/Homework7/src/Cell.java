import java.util.ArrayList;
import java.util.List;

public class Cell {
    private boolean isVisited = false;
    private List<Token> tokens = new ArrayList<>();

    public boolean isVisited() {
        return isVisited;
    }

    public void setVisited(boolean visited) {
        isVisited = visited;
    }

    public synchronized void addTokens(List<Token> newTokens) {
        tokens.addAll(newTokens);
    }

    public List<Token> getTokens() {
        return tokens;
    }

    public void setTokens(List<Token> tokens) {
        this.tokens = tokens;
    }

}

