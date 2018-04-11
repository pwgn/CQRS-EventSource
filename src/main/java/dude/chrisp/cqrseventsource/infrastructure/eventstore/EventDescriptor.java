package dude.chrisp.cqrseventsource.infrastructure.eventstore;

import dude.chrisp.cqrseventsource.common.domain.Event;

public class EventDescriptor {
    public final Event event;
    public final String id;
    public final int version;

    public EventDescriptor(String id, Event event, int version) {
        this.id = id;
        this.event = event;
        this.version = version;
    }
}
