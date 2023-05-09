import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class GameClient {
    public static void main(String[] args) throws IOException {
        String serverHostname = "localhost";
        int serverPort = 2023;

        Socket clientSocket = new Socket(serverHostname, serverPort);
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

        String userInputLine;
        System.out.println("Introduceti comanda:");
        while ((userInputLine = userInput.readLine()) != null) {

            if (userInputLine.equals("exit")) {
                out.println(userInputLine);
                break;
            } else if (userInputLine.startsWith("create")) {
                out.println(userInputLine);
                System.out.println(in.readLine());
            } else if (userInputLine.startsWith("join")) {
                out.println(userInputLine);
                System.out.println(in.readLine());
            } else {
                out.println(userInputLine);
                String response = in.readLine();
                System.out.println("Server response: " + response);
                if (response.equals("Server stopped")) {
                    break;
                }
            }
            System.out.println("Introduceti comanda:");
        }

        out.close();
        in.close();
        userInput.close();
        clientSocket.close();
    }
}
