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
                User extracted_User = new User(resultSet.getString("USERNAME"), resultSet.getString("SUBSCRIPTION"), resultSet.getString("IS_TRAINER"));
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
                        resultSet.getString("IS_TRAINER")
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

    public void addUser(String username, String password, boolean isTrainer) {
        String query = "{CALL dynamic_insert_user(?, ?, ?, ?, ?, ?, ?)}"; 
        try (Connection connection = DatabaseConnection.getConnection();
             CallableStatement callableStatement = connection.prepareCall(query)) {

            callableStatement.setString(1, "userTable"); 
            callableStatement.setString(2, "name");    
            callableStatement.setString(3, "pass");     
            callableStatement.setString(4, "train");    
            callableStatement.setString(5, username);  
            callableStatement.setString(6, password);   
            callableStatement.setInt(7, isTrainer ? 1 : 0); 
    
            callableStatement.execute();
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
