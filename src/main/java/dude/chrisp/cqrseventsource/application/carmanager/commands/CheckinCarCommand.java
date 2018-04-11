package dude.chrisp.cqrseventsource.application.carmanager.commands;

public class CheckinCarCommand {
    public final String carId;
    public final int originalVersion;

    public CheckinCarCommand(String carId, int originalVersion) {
        this.carId = carId;
        this.originalVersion = originalVersion;
    }
}
