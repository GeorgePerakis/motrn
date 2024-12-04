package shiven.Utility;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import oracle.jdbc.OracleTypes;
import shiven.DB.DatabaseConnection;

public class Authorization {

    public static boolean check_username(String username) {
        String query = "{CALL dynamic_auth_user(?, ?)}";
        try (Connection connection = DatabaseConnection.getConnection(); 
             CallableStatement callableStatement = connection.prepareCall(query)) {
    
            callableStatement.setString(1, username);   
    
            callableStatement.registerOutParameter(2, OracleTypes.CURSOR);
    
            callableStatement.execute();
    
            ResultSet resultSet = (ResultSet) callableStatement.getObject(2);
    
            while (resultSet.next()) {
                String retrievedUsername = resultSet.getString("USERNAME");
    
                if (retrievedUsername.equals(username)) {
                    return true; 
                }
            }
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;  
    }

    public static boolean check_password(String username,String password) {
        String query = "{CALL dynamic_get_pass(?, ?)}";
        try (Connection connection = DatabaseConnection.getConnection(); 
             CallableStatement callableStatement = connection.prepareCall(query)) {
    
            callableStatement.setString(1, username);
    
            callableStatement.registerOutParameter(2, OracleTypes.CURSOR);
    
            callableStatement.execute();
    
            ResultSet resultSet = (ResultSet) callableStatement.getObject(2);
    
            while (resultSet.next()) {
                String retrievedPassword = resultSet.getString("PASSWORD");
    
                if (retrievedPassword.equals(password)) {
                    return true; 
                }
            }
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;  
    }
}
