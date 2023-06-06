package Connection;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ServerCom {
    private final ClientConnection con;
    public ServerCom(ClientConnection con)
    {
        this.con = con;
    }

    /**
     * Sends a login request to the server
     * @param username
     * @param password
     * @return string
     */
    public String sendLoginRequest(String username, String password)
    {
        String response = "";
        try {
            this.con.getDataOutputStream().writeUTF("login " + username + " " + password);
            response = this.con.getDataInputStream().readUTF();
            System.out.println("[Client-Side] Got:" + response);

        }catch (IOException e)
        {
            e.printStackTrace();
        }
        return response;
    }

    /**
     * Sends a signup request to the server
     * @param username
     * @param password
     * @return string
     */
    public String sendSignupRequest(String username, String password)
    {
        String response = "";
        try {
            this.con.getDataOutputStream().writeUTF("signup " + username + " " + password);
            response = this.con.getDataInputStream().readUTF();
            System.out.println("[Client-Side] Got: " + response);

        }catch (IOException e)
        {
            e.printStackTrace();
        }
        return response;
    }

    /**
     * Gets the list of friends for a user
     * @param username user
     * @return list of friends
     */
    public List<String> getUserFriends(String username)
    {
        List<String> friends = new ArrayList<>();
        try{
            String response = "";
            this.con.getDataOutputStream().writeUTF("getFriends " + username);
            response = this.con.getDataInputStream().readUTF();
            System.out.println("[Client-Side] Got: " + response);
            String[] f = response.split(",");
            friends.addAll(Arrays.asList(f));
        }catch (IOException e)
        {
            e.printStackTrace();
        }
        return friends;
    }

    /**
     * Adds a friend
     * @param friend1 user1
     * @param friend2 user2
     * @return string
     */
    public String addFriendRequest(String friend1, String friend2)
    {
        String response = "";
        try {
            this.con.getDataOutputStream().writeUTF("friend " + friend1 + " " + friend2);
            System.out.println("Sending");
            this.con.getDataOutputStream().flush();
            response = this.con.getDataInputStream().readUTF();
            System.out.println("[Client-Side] Got: " + response);
        }catch (IOException e)
        {
            e.printStackTrace();
        }
        return response;
    }

    /**
     * First, it replaces ' ' with $ and then sends a message to the server.
     * @param from the user who sends it
     * @param to the user it is sent to
     * @param message the text
     * @return message
     */

    public String sendMessage(String from, String to, String message)
    {
        String text = message.replace(" ", "$");
        String toSend = from + " " + to + " " + text;
        String response = "";
        try {
            this.con.getDataOutputStream().writeUTF("send " + toSend);
            System.out.println("[Client-Side] Sending: " + toSend);
            response = this.con.getDataInputStream().readUTF();
            System.out.println("[Client-Side] Got: " + response);
        }catch (IOException e)
        {
            e.printStackTrace();
        }
        return response;
    }

    /**
     * Get messages between a user and a friend
     * @param username of the user
     * @param friend the other user
     * @return messages
     */
    public String getMessages(String username,String friend)
    {
        String response = "";
        try {
            this.con.getDataOutputStream().writeUTF("getmess "+ username + " " + friend);
            System.out.println("[Client-Side] Sending getmess for " + username+ " + " + friend);
            response = this.con.getDataInputStream().readUTF();
            System.out.println("[Client-Side] Got: " + response);
        }catch (IOException e)
        {
            e.printStackTrace();
        }
        return response;
    }
}
