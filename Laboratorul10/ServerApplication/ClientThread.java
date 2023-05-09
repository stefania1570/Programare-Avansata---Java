import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

class ClientThread extends Thread {
    private Socket clientSocket;
    private GameServer gameServer;

    public ClientThread(Socket socket, GameServer gameServer) {
        clientSocket = socket;
        this.gameServer = gameServer;
    }

    public void run() {
        try {
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                switch (inputLine) {
                    case "stop":
                        out.println("Server stopped");
                        sleep(2000);
                        gameServer.stop();
                        break;
                    case "create":
                        out.println("Game created");
                        break;
                    case "join":
                        out.println("Game joined");
                        break;
                    default:
                        out.println("Unknown command: " + inputLine);
                        break;
                }
            }

            out.close();
            in.close();
            clientSocket.close();
        } catch (IOException e) {
            System.out.println("Error handling client request: " + e.getMessage());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
