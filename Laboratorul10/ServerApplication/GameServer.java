import java.net.*;
import java.io.*;

public class GameServer {
    private ServerSocket serverSocket;
    private boolean isRunning = true;

    public GameServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
    }


    public void start() {
        System.out.println("Server started on port " + serverSocket.getLocalPort());
        while (isRunning) {
            try {
                Socket clientSocket = serverSocket.accept();
                ClientThread clientThread = new ClientThread(clientSocket, this);
                clientThread.start();
            } catch (IOException e) {
                System.out.println("Error accepting client connection: " + e.getMessage());
            }
        }
    }

    public void stop() {
        isRunning = false;
        try {
            serverSocket.close();
        } catch (IOException e) {
            System.out.println("Error stopping server: " + e.getMessage());
        }
    }

    public static void main(String[] args) throws IOException {
        GameServer server = new GameServer(2023);
        server.start();
    }
}