package dude.chrisp.cqrseventsource.common.domain;

import dude.chrisp.cqrseventsource.common.event.Message;

import java.time.OffsetDateTime;
import java.time.ZoneId;

public class Event implements Message {
    public int version;
    private OffsetDateTime occurredOn;

    public Event() {
        this.occurredOn = OffsetDateTime.now(ZoneId.of("Z"));
    }

    public OffsetDateTime occurredOn() {
        return occurredOn;
    }

}
