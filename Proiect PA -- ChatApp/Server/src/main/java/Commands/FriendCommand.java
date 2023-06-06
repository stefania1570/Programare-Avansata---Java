package Commands;

import Databases.UserDAO;
import Databases.FriendDAO;

import java.io.DataOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FriendCommand {

    /**
     * This method is used to execute an add friend request.
     * @param dataOutputStream To send a response to the client.
     * @param p The parameters received from the client.
     */
    public void friend(DataOutputStream dataOutputStream, String[] p)
    {
        try {
            var user = new UserDAO();
            var friendship = new FriendDAO();
            int id1 = user.getIdByUsername(p[0]);
            int id2 = user.getIdByUsername(p[1]);
            if (p.length == 2) {
                if (id2 != 0 &&
                        !friendship.exists(id1, id2)) {
                    System.out.println(p[1] + " exists.");
                    friendship.create(id1, id2);
                    friendship.create(id2, id1);
                    System.out.println("Friendship created.");
                    dataOutputStream.writeUTF("Friendship successful");
                    System.out.println("Sending");
                } else dataOutputStream.writeUTF("User does not exist/ already friends");
            } else {
                dataOutputStream.writeUTF("Not enough data.");
                System.out.println("Sending");
            }
        } catch (IOException | SQLException ioException)
        {
            System.out.println("Error adding friend.");
        }
    }

    /**
     * This method is used to execute a get friends request.
     * It sends a csv string to the client of the users implicated
     * in a friendship with the user given as a parameter.
     * @param dataOutputStream To send a response to the client.
     * @param p The parameters received from the client.
     */
    public void getFriends(DataOutputStream dataOutputStream, String[] p)
    {
        var user = new UserDAO();
        var friendship = new FriendDAO();
        try {
            if (p.length == 1) {
                List<Integer> ids = friendship.getAllFriends(user.getIdByUsername(p[0]));
                List<String> usernames = new ArrayList<>();
                for (Integer fid : ids)
                    usernames.add(user.getUsernameById(fid));
                String sendFriends = "";
                for (String friends : usernames)
                    sendFriends = sendFriends.concat(friends + ",");
                dataOutputStream.writeUTF(sendFriends);
            } else dataOutputStream.writeUTF("error");
        }
        catch (IOException ioException)
        {
            System.out.println("Error getting friends.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
