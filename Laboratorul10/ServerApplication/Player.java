import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Player {

    private static int playerIdCounter = 1;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    private int id;
    private Game game;
    private char symbol;
    public Player(Socket clientSocket) {
        id = playerIdCounter++;
        this.clientSocket = clientSocket;
        try {
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException e) {
            System.out.println("Error setting up player streams: " + e.getMessage());
        }
        //symbol = ; // Initialize symbol to be set later by the game
    }

    public int getId() {
        return id;
    }

    public Socket getClientSocket() {
        return clientSocket;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public PrintWriter getWriter(){
        return out;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

}
