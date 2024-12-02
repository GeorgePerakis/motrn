package shiven.DB;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class User {
    private final StringProperty name;
    private final StringProperty subscription;
    private final StringProperty trainer;

    public User(String name, String trainer, String subscription ) {
        this.name = new SimpleStringProperty(name);
        this.subscription = new SimpleStringProperty(subscription);
        this.trainer = new SimpleStringProperty(trainer);
    }

    public StringProperty nameProperty() {
        return name;
    }

    public StringProperty subscriptionProperty() {
        return subscription;
    }

    public StringProperty trainerProperty() {
        return trainer;
    }

    public String getName() {
        return name.get();
    }

    public String getsubscription() {
        return subscription.get();
    }

    public String gettrainer() {
        return trainer.get();
    }
}
