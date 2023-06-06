package Commands;

import Databases.MessageDAO;
import Databases.UserDAO;

import java.io.DataOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class MessageCommand {

    /**
     * This method is used to invoke the method from the class
     * which communicates with the API responsible for adding a
     * message in the database.
     * @param dataOutputStream To send a response to the client.
     * @param p The parameters received from the client.
     */
    public void send(DataOutputStream dataOutputStream, String[] p)
    {
        try {
            if (p.length > 2) {
                String toSend = p[0] + "," + p[1] + "," + p[2];
                    var user = new UserDAO();
                    var message = new MessageDAO();
                    int id1 = user.getIdByUsername(p[0]);
                    int id2 = user.getIdByUsername(p[1]);

                    message.create(id1, id2, p[2]);
                    System.out.println("Message created.");

                    System.out.println("Sending");

                dataOutputStream.writeUTF("Message sent");
            } else dataOutputStream.writeUTF("Can't send message.");
        }
        catch (IOException ioException)
        {
            ioException.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This method is used to get the messages of the user send as
     * a parameter by the client by invoking the corresponding method
     * from the class which communicates with the API.
     * @param dataOutputStream To send a response to the client.
     * @param p The parameters received from the client.
     */
    public void getMessages(DataOutputStream dataOutputStream, String[] p)
    {
        try {
            if (p.length == 2) {
                var user = new UserDAO();
                var message = new MessageDAO();

                Map<Integer, String> idToName = new HashMap<>();
                int id1 = user.getIdByUsername(p[0]);
                int id2 = user.getIdByUsername(p[1]);
                idToName.put(id1, p[0]);
                idToName.put(id2, p[1]);
                String conversation = message.getAllMessages(id1,id2);

                // Matches sequences of non-digit characters followed by a colon
                Pattern pattern = Pattern.compile("\\d+(?=:)");
                String result = pattern.matcher(conversation)
                        .replaceAll(match -> idToName.getOrDefault(Integer.parseInt(match.group()), match.group()));
                dataOutputStream.writeUTF(result);

            } else dataOutputStream.writeUTF("Error [from MessageCommand]");
        }
        catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
