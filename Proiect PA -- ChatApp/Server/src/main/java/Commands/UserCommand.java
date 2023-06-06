package Commands;

import Databases.UserDAO;

import java.io.DataOutputStream;
import java.io.IOException;
import java.sql.SQLException;

public class UserCommand {
    private static int id = 1;
    /**
     * This method is used to invoke the method from the class
     * responsible with the connection to the 'users' table from the
     * database to verify if the provided parameters exist.
     * @param dataOutputStream To send a response to the client.
     * @param p The parameters received from the client.
     */
    public void login(DataOutputStream dataOutputStream, String[] p)
    {
        var user = new UserDAO();
        try {
            if (p.length == 2 && user.findUser(p[0], p[1]))//searching for user in the db
            {
                dataOutputStream.writeUTF("Login successful.");
                System.out.println("Sending");

            } else {
                dataOutputStream.writeUTF("Wrong username or password.");
                System.out.println("Sending");
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This method is used to invoke the method from the class
     * responsible with the connection to the 'users' table from the
     * database to verify if the provided parameters can be used to
     * create a new user.
     * @param dataOutputStream To send a response to the client.
     * @param p The parameters received from the client.
     */
    public void signup(DataOutputStream dataOutputStream, String[] p)
    {
        var user = new UserDAO();
        try {
            if (p.length == 0 || user.getIdByUsername(p[0]) != 0) {
                dataOutputStream.writeUTF("Username already exists/Not enough data.");
                System.out.println("Sending");
            } else {
                user.create(id++, p[0], p[1]);
                if (user.findUser(p[0], p[1])) {
                    dataOutputStream.writeUTF("Signup successful.");
                    System.out.println("Sending");
                } else {
                    dataOutputStream.writeUTF("Signup error.");
                    System.out.println("Sending");
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
