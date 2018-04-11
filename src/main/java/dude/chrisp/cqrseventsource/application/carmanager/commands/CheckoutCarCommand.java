package dude.chrisp.cqrseventsource.application.carmanager.commands;

public class CheckoutCarCommand {
    public final String carId;
    public final int originalVersion;

    public CheckoutCarCommand(String carId, int originalVersion) {
        this.carId = carId;
        this.originalVersion = originalVersion;
    }
}
