package dude.chrisp.cqrseventsource.application.carmanager.commands;

public class AddCarCommand {
    public int rate;
    public String carModel;

    public AddCarCommand() {

    }

    public AddCarCommand(int rate, String carModel) {
        this.rate = rate;
        this.carModel = carModel;
    }
}
