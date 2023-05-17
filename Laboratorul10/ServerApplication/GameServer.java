import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class GameServer {
    private ServerSocket serverSocket;
    private boolean isRunning = true;
    private ExecutorService executorService;
    private List<Game> games;
    private List<ClientThread> clientThreads;

    public GameServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        executorService = Executors.newCachedThreadPool();
        games = new ArrayList<>();
        clientThreads = new ArrayList<>();
    }

    public List<Game> getGames() {
        return games;
    }

    public List<ClientThread> getClientThreads() {
        return clientThreads;
    }

    public void addGame(Game game) {
        games.add(game);
    }

    public void start() {
        System.out.println("Server started on port " + serverSocket.getLocalPort());
        while (isRunning) {
            try {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected: " + clientSocket);
                ClientThread clientThread = new ClientThread(clientSocket, this);
                clientThreads.add(clientThread);
                executorService.execute(clientThread);
            } catch (IOException e) {
                System.out.println("Error accepting client connection: " + e.getMessage());
            }
        }
    }

    public void stop() {
        isRunning = false;
        try {
            // Stop all client threads TODO CA NU MERGE
//            for (ClientThread clientThread : clientThreads) {
//                System.out.println(clientThread.toString());
//                clientThread.getClientSocket().close();
//            }
            serverSocket.close();
            executorService.shutdownNow();
        } catch (IOException e) {
            System.out.println("Error stopping server: " + e.getMessage());
        }
    }

    public synchronized void removeClientThread(ClientThread clientThread) {
        clientThreads.remove(clientThread);
    }

    public static void main(String[] args) throws IOException {
        GameServer server = new GameServer(2023);
        server.start();
    }
}