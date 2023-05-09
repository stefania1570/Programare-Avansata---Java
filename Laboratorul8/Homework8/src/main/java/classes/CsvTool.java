package classes;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CsvTool {
    private static final String CSV_FILE_PATH = "C:\\Users\\Stefania\\OneDrive\\Desktop\\Laboratorul-8\\src\\main\\albumlist.csv";

    public static void main(String[] args) {
        try (CSVReader reader = new CSVReader(new FileReader(CSV_FILE_PATH))) {
            String[] columns = reader.readNext();
            String[] row;

            while ((row = reader.readNext()) != null) {
                String sql = "INSERT INTO albums(id, release_year, title, artist) " +
                        "VALUES (?, ?, ?, ?)";
                try (var connection = Database.getConnection();
                     PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setInt(1, Integer.parseInt(row[0])); // id
                    statement.setInt(2, Integer.parseInt(row[1])); // release_year
                    statement.setString(3, row[2]); // title
                    statement.setString(4, row[3]); // artist

                    statement.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
