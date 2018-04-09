package dude.chrisp.cqrseventsource.application.carmanager.commands;

public class CheckinCarCommand {
    public String carId;

    public CheckinCarCommand(String carId) {
        this.carId = carId;
    }
}
