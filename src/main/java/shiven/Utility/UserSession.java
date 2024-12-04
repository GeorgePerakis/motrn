package shiven.Utility;

public class UserSession {

    private static UserSession instance;

    private String username;

    private boolean is_trainer;

    private UserSession() {}

    public static UserSession getInstance() {
        if (instance == null) {
            instance = new UserSession();
        }
        return instance;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean getis_trainer() {
        return is_trainer;
    }

    public void setis_trainer(boolean is_trainer) {
        this.is_trainer = is_trainer;
    }
}