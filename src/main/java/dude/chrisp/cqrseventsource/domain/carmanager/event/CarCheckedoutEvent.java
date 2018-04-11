package dude.chrisp.cqrseventsource.domain.carmanager.event;

import dude.chrisp.cqrseventsource.common.domain.Event;

public class CarCheckedoutEvent extends Event {

    public String id;

    public CarCheckedoutEvent(String id, int version) {
        super(version);
        this.id = id;
    }
}