package dude.chrisp.cqrseventsource.domain.carmanager.event;

import dude.chrisp.cqrseventsource.common.domain.Event;

public class CarCheckedinEvent extends Event {

    public String id;

    public CarCheckedinEvent(String id, int version) {
        super(version);
        this.id = id;
    }
}
