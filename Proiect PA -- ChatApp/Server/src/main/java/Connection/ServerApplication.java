package Connection;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * The application that will open a server which will receive new
 * clients and for each of them will start a new thread
 * that will handle the communication
 */

//@SpringBootApplication
public class ServerApplication {
	private static final int PORT = 6666;


	public static void main(String[] args) {

		try {
			ServerSocket serverSocket = new ServerSocket(PORT);
			System.out.println("Server open.");
			while (true) {
				Socket socket = null;
				try {
					socket = serverSocket.accept();
					System.out.println("Client connected.");
					DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
					DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
					ClientHandler client = new ClientHandler(serverSocket, socket, dataInputStream, dataOutputStream);
					Thread thread = new Thread(client);
					thread.start();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}
