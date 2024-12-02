package shiven.DB.DAOS;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import oracle.jdbc.OracleTypes;
import shiven.DB.DatabaseConnection;
import shiven.DB.User;

public class UserDAO {

    public User getUser(String username) {
        String query = "{CALL dynamic_get_user(?, ?, ?, ?)}";
        try (Connection connection = DatabaseConnection.getConnection(); CallableStatement callableStatement = connection.prepareCall(query)) {

            callableStatement.setString(1, "userTable");
            callableStatement.setString(2, "name");

            String condition = "= " + "'" + username + "'";

            callableStatement.setString(3, condition);

            callableStatement.registerOutParameter(4, OracleTypes.CURSOR);

            callableStatement.execute();

            ResultSet resultSet = (ResultSet) callableStatement.getObject(4);
            while (resultSet.next()) {
                User extracted_User = new User(resultSet.getString("USERNAME"), resultSet.getString("SUBSCRIPTION"), resultSet.getString("TRAINER"));
                return extracted_User;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            
        }
        return null;
    }

    public List<User> getAllUsers() {
        String query = "{CALL dynamic_get_all_users(?, ?)}";
        List<User> UserList = new ArrayList<User>();
        try (Connection connection = DatabaseConnection.getConnection(); CallableStatement callableStatement = connection.prepareCall(query)) {

            callableStatement.setString(1, "userTable");

            callableStatement.registerOutParameter(2, OracleTypes.CURSOR);

            callableStatement.execute();

            ResultSet resultSet = (ResultSet) callableStatement.getObject(2);

            while (resultSet.next()) {
                User extractedUser = new User(
                        resultSet.getString("USERNAME"),
                        resultSet.getString("SUBSCRIPTION"),
                        resultSet.getString("TRAINER")
                );
                if (extractedUser != null) {
                    UserList.add(extractedUser);
                }
            }
            return UserList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void addUser(String username, String password) {
        String query = "INSERT INTO users (username, password) VALUES (?, ?)";

        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
