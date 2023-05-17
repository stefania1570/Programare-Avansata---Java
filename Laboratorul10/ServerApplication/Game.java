import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Game {
    private static int gameIdCounter = 1;
    private int id;
    private List<Player> players;
    private Board board;
    private Player currentPlayer;
    private int currentPlayerIndex;
    private boolean gameOver = false;
    private List<PrintWriter> playerWriters;

    public Game() {
        id = gameIdCounter++;
        players = new ArrayList<>();
        board = new Board();
        playerWriters = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public static int getGameIdCounter() {
        return gameIdCounter;
    }

    public Board getBoard() {
        return board;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public synchronized boolean addPlayer(Player player) {
        if (players.size() < 2) {
            Player p = player;
            p.setGame(this); //?? idk daca merge
            players.add(p);
            playerWriters.add(p.getWriter());
            if (players.size() == 2) {
                startGame();
            }
            return true;
        }
        return false;
    }

    public Player findPlayerById(int playerId) {
        for (Player player : players) {
            if (player.getId() == playerId) {
                return player;
            }
        }
        return null;
    }

    public void setCurrentPlayerIndex(int currentPlayerIndex) {
        this.currentPlayerIndex = currentPlayerIndex;
    }

    public int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }

    public void startGame() {
        // Initialize the game and notify players that the game has started
        currentPlayerIndex = 0;
        gameOver = false;
        broadcastMessage("Game started.");

        // Send initial board state to players
        broadcastBoard();

        // Notify the current player to make a move
        Player currentPlayer = players.get(currentPlayerIndex);
        currentPlayer.getWriter().println("Your turn. Make a move.");
    }

    private void broadcastBoard() {
        String boardState = board.getBoardState();
        String aux = "Current board state:" + boardState;
        broadcastMessage(aux);
    }

    private void broadcastMessage(String message) {
        for (PrintWriter writer : playerWriters) {
            writer.println(message);
        }
    }
}
