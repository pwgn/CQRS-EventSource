package dude.chrisp.cqrseventsource.domain.car.event;

import dude.chrisp.cqrseventsource.common.domain.Event;

public class CarCheckedoutEvent extends Event {

    public static final String CAR_CHECKEDOUT_EVENT_MESSAGE_KEY = "CarCheckedoutEvent";

    public String id;

    public CarCheckedoutEvent(String id) {
        super(CAR_CHECKEDOUT_EVENT_MESSAGE_KEY);
        this.id = id;
    }
}
