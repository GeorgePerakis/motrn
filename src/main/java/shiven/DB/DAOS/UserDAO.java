package shiven.DB.DAOS;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Types;

import oracle.jdbc.OracleTypes;
import shiven.DB.DatabaseConnection;
import shiven.DB.Subscription;
import shiven.DB.User;

public class UserDAO {

    public User getUser(String username) {
        String query = "{CALL dynamic_get_user(?, ?)}";
        try (Connection connection = DatabaseConnection.getConnection(); CallableStatement callableStatement = connection.prepareCall(query)) {

            callableStatement.setString(1, username);

            callableStatement.registerOutParameter(2, OracleTypes.CURSOR);

            callableStatement.execute();

            try (ResultSet resultSet = (ResultSet) callableStatement.getObject(2)) {
                while (resultSet.next()) {
                    User extracted_User = new User(resultSet.getString("USERNAME"), "", "");
                    return extracted_User;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }

    public List<User> getAllUsers() {
        String query = "{CALL dynamic_get_all_users(?)}";
        List<User> UserList = new ArrayList<User>();
        try (Connection connection = DatabaseConnection.getConnection(); CallableStatement callableStatement = connection.prepareCall(query)) {

            callableStatement.registerOutParameter(1, OracleTypes.CURSOR);

            callableStatement.execute();

            try (ResultSet resultSet = (ResultSet) callableStatement.getObject(1)) {
                while (resultSet.next()) {
                    User extractedUser = new User(
                            resultSet.getString("USERNAME"), "", ""
                    );
                    if (extractedUser != null) {
                        UserList.add(extractedUser);
                    }
                }
                return UserList;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void addUser(String username, String password) {
        String query = "{CALL dynamic_insert_user(?, ?)}";
        try (Connection connection = DatabaseConnection.getConnection(); CallableStatement callableStatement = connection.prepareCall(query)) {
            callableStatement.setString(1, username);
            callableStatement.setString(2, password);

            callableStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addDeletedUser(String username) {
        String query = "{CALL dynamic_insert_deleted_user(?)}";
        try (Connection connection = DatabaseConnection.getConnection(); CallableStatement callableStatement = connection.prepareCall(query)) {
            callableStatement.setString(1, username);

            callableStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(String username) {
        String query = "{CALL dynamic_delete_user(?)}";
        try (Connection connection = DatabaseConnection.getConnection(); CallableStatement callableStatement = connection.prepareCall(query)) {
            callableStatement.setString(1, username);

            callableStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addTrainer(String username) {
        String query = "{CALL dynamic_insert_trainer(?)}";
        try (Connection connection = DatabaseConnection.getConnection(); CallableStatement callableStatement = connection.prepareCall(query)) {

            callableStatement.setString(1, username);

            callableStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void initUserGroup(String username) {
        String query = "{CALL dynamic_init_user_group(?)}";
        try (Connection connection = DatabaseConnection.getConnection(); CallableStatement callableStatement = connection.prepareCall(query)) {

            callableStatement.setString(1, username);

            callableStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editUserGroup(String username, boolean trx, boolean crossfit, boolean kickboxing, boolean pilates) {
        String query = "{CALL dynamic_edit_user_group(?,?,?,?,?)}";
        try (Connection connection = DatabaseConnection.getConnection(); CallableStatement callableStatement = connection.prepareCall(query)) {

            callableStatement.setString(1, username);
            callableStatement.setInt(2, trx ? 1 : 0);
            callableStatement.setInt(3, crossfit ? 1 : 0);
            callableStatement.setInt(4, kickboxing ? 1 : 0);
            callableStatement.setInt(5, pilates ? 1 : 0);

            callableStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Subscription getUserGroup(String username) {
        String query = "{CALL dynamic_get_user_group(?, ?, ?, ?, ?)}";
        try (Connection connection = DatabaseConnection.getConnection(); CallableStatement callableStatement = connection.prepareCall(query)) {

            callableStatement.setString(1, username);

            callableStatement.registerOutParameter(2, Types.INTEGER); 
            callableStatement.registerOutParameter(3, Types.INTEGER); 
            callableStatement.registerOutParameter(4, Types.INTEGER); 
            callableStatement.registerOutParameter(5, Types.INTEGER); 

            callableStatement.execute();

            int trx = callableStatement.getInt(2);
            int crossfit = callableStatement.getInt(3);
            int kickboxing = callableStatement.getInt(4);
            int pilates = callableStatement.getInt(5);


            Subscription user_sub = new Subscription(trx, crossfit, kickboxing, pilates);

            
            return  user_sub;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        
        return  null;
    }

    public int getUserTotal(String username) {
        String query = "{CALL dynamic_get_user_total(?, ?)}";
        try (Connection connection = DatabaseConnection.getConnection(); CallableStatement callableStatement = connection.prepareCall(query)) {

            callableStatement.setString(1, username);

            callableStatement.registerOutParameter(2, Types.INTEGER); 

            callableStatement.execute();

            int total = callableStatement.getInt(2);
            
            return  total;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    public List<User> getAllTrainers() {
        String query = "{CALL dynamic_get_all_trainers(?)}";
        List<User> TrainerList = new ArrayList<User>();
        try (Connection connection = DatabaseConnection.getConnection(); CallableStatement callableStatement = connection.prepareCall(query)) {

            callableStatement.registerOutParameter(1, OracleTypes.CURSOR);

            callableStatement.execute();

            try (ResultSet resultSet = (ResultSet) callableStatement.getObject(1)) {
                while (resultSet.next()) {
                    User extractedUser = new User(
                            resultSet.getString("USERNAME"), "", ""
                    );
                    if (extractedUser != null) {
                        TrainerList.add(extractedUser);
                    }
                }
                return TrainerList;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
