package Databases;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MessageDAO {

    public void create(int id_user1, int id_user2,String message) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        try(PreparedStatement pstmt = connection.prepareStatement(
                "INSERT INTO messages (id_user1, id_user2, mess ) VALUES (?, ?, ?)")){
            pstmt.setInt(1, id_user1);
            pstmt.setInt(2, id_user2);
            pstmt.setString(3, message);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            connection.close();
        }
    }

    /**
     * Gets all messages and replaces the '$' character with ' ' before sending it to the client.
     * It builds a string: "sender_username:message;sender_username:message;" etc.
     */
    public String getAllMessages(int id_user1, int id_user2) throws SQLException {
        StringBuilder allMessages = new StringBuilder();
        Connection connection = DatabaseConnection.getConnection();
        String mess = "";
        try (PreparedStatement pstmt = connection.prepareStatement(
                "SELECT id_user1, mess FROM messages WHERE (id_user1 = ? AND id_user2 = ?) OR (id_user1 = ? AND id_user2 = ?)"
        )) {
            pstmt.setInt(1, id_user1);
            pstmt.setInt(2, id_user2);
            pstmt.setInt(3, id_user2);
            pstmt.setInt(4, id_user1);
            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                int senderId = resultSet.getInt("id_user1");
                mess = resultSet.getString("mess");
                String modifiedMess = mess.replace("$", " ");
                allMessages.append(senderId);
                allMessages.append(":");
                allMessages.append(modifiedMess);
                allMessages.append(";");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }

        return allMessages.toString();
    }

}