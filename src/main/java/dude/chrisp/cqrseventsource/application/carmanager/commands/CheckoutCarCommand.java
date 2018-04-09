package dude.chrisp.cqrseventsource.application.carmanager.commands;

public class CheckoutCarCommand {
    public String carId;

    public CheckoutCarCommand(String carId) {
        this.carId = carId;
    }
}
