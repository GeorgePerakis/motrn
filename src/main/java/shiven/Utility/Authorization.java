package shiven.Utility;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import oracle.jdbc.OracleTypes;
import shiven.DB.DatabaseConnection;

public class Authorization {

    public static boolean check_username(String username) {
        String query = "{CALL dynamic_get_user(?, ?, ?, ?)}";
        try (Connection connection = DatabaseConnection.getConnection(); 
             CallableStatement callableStatement = connection.prepareCall(query)) {
    
            callableStatement.setString(1, "userTable"); 
            callableStatement.setString(2, "name");   
            
            String condition = "= " + "'" + username + "'"  ;  


            callableStatement.setString(3, condition); 
    
            callableStatement.registerOutParameter(4, OracleTypes.CURSOR);
    
            callableStatement.execute();
    
            ResultSet resultSet = (ResultSet) callableStatement.getObject(4);
    
            while (resultSet.next()) {
                String retrievedUsername = resultSet.getString("USERNAME");
    
                if (retrievedUsername.equals(username)) {
                    return true; 
                }
            }
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;  // Username not found
    }
}
