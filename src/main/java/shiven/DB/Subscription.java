package shiven.DB;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Subscription {
    private final StringProperty trx;
    private final StringProperty crossfit;
    private final StringProperty kickboxing;
    private final StringProperty pilates;

    public Subscription(int trx, int crossfit, int kickboxing, int pilates) {
        this.trx = new SimpleStringProperty(trx == 1 ? "Yes" : "No");
        this.crossfit = new SimpleStringProperty(crossfit == 1 ? "Yes" : "No");
        this.kickboxing = new SimpleStringProperty(kickboxing == 1 ? "Yes" : "No");
        this.pilates = new SimpleStringProperty(pilates == 1 ? "Yes" : "No");
    }

    // Getters
    public String getTrx() {
        return trx.get();
    }

    public String getCrossfit() {
        return crossfit.get();
    }

    public String getKickboxing() {
        return kickboxing.get();
    }

    public String getPilates() {
        return pilates.get();
    }

    // Setters
    public void setTrx(int trx) {
        this.trx.set(trx == 1 ? "Yes" : "No");
    }

    public void setCrossfit(int crossfit) {
        this.crossfit.set(crossfit == 1 ? "Yes" : "No");
    }

    public void setKickboxing(int kickboxing) {
        this.kickboxing.set(kickboxing == 1 ? "Yes" : "No");
    }

    public void setPilates(int pilates) {
        this.pilates.set(pilates == 1 ? "Yes" : "No");
    }

}