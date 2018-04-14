package dude.chrisp.cqrseventsource.domain.carmanager.event;

import dude.chrisp.cqrseventsource.common.domain.Event;

public class CarCreatedEvent extends Event {

    public static final String CAR_CREATED_EVENT_MESSAGE_KEY = "CarCreatedEvent";

    public String id;
    public int rate;
    public String carModel;
    public boolean available;

    public CarCreatedEvent(String id, int rate, String carModel, boolean available) {
        super(CAR_CREATED_EVENT_MESSAGE_KEY);
        this.id = id;
        this.rate = rate;
        this.carModel = carModel;
        this.available = available;
    }
}
