package dude.chrisp.cqrseventsource.domain.carmanager.event;

import dude.chrisp.cqrseventsource.common.domain.Event;

public class CarCheckedinEvent extends Event {

    public static final String CAR_CHECKEDIN_EVENT_MESSAGE_KEY = "CarCheckedinEvent";

    public String id;

    public CarCheckedinEvent(String id) {
        super(CAR_CHECKEDIN_EVENT_MESSAGE_KEY);
        this.id = id;
    }
}
