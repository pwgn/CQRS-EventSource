package dude.chrisp.cqrseventsource.domain.carmanager.event;

import dude.chrisp.cqrseventsource.common.domain.Event;

public class CarCreatedEvent extends Event {

    public String id;
    public int rate;
    public String carModel;
    public boolean available;

    public CarCreatedEvent(String id, int rate, String carModel, boolean available) {
        this.id = id;
        this.rate = rate;
        this.carModel = carModel;
        this.available = available;
    }
}
