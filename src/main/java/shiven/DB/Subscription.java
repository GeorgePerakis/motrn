package shiven.DB;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Subscription {
    private final IntegerProperty trx;
    private final IntegerProperty crossfit;
    private final IntegerProperty kickboxing;
    private final IntegerProperty pilates;

    public Subscription(int trx, int crossfit, int kickboxing, int pilates) {
        this.trx = new SimpleIntegerProperty(trx);
        this.crossfit = new SimpleIntegerProperty(crossfit);
        this.kickboxing = new SimpleIntegerProperty(kickboxing);
        this.pilates = new SimpleIntegerProperty(pilates);
    }

    public int getTrx() {
        return trx.get();
    }

    public int getCrossfit() {
        return crossfit.get();
    }

    public int getKickboxing() {
        return kickboxing.get();
    }

    public int getPilates() {
        return pilates.get();
    }

    public void setTrx(int trx) {
        this.trx.set(trx);
    }

    public void setCrossfit(int crossfit) {
        this.crossfit.set(crossfit);
    }

    public void setKickboxing(int kickboxing) {
        this.kickboxing.set(kickboxing);
    }

    public void setPilates(int pilates) {
        this.pilates.set(pilates);
    }

    public IntegerProperty trxProperty() {
        return trx;
    }

    public IntegerProperty crossfitProperty() {
        return crossfit;
    }

    public IntegerProperty kickboxingProperty() {
        return kickboxing;
    }

    public IntegerProperty pilatesProperty() {
        return pilates;
    }
}