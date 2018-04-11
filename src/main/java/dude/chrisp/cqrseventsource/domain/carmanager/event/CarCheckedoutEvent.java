package dude.chrisp.cqrseventsource.domain.carmanager.event;

import dude.chrisp.cqrseventsource.common.domain.Event;

import java.time.OffsetDateTime;
import java.time.ZoneId;

public class CarCheckedoutEvent implements Event {

    public String id;
    private int version;
    private OffsetDateTime occurredOn;

    public CarCheckedoutEvent(String id, int version) {
        this.id = id;
        this.version = version;
        this.occurredOn = OffsetDateTime.now(ZoneId.of("Z"));
    }

    @Override
    public int eventVersion() {
        return version;
    }

    @Override
    public OffsetDateTime occurredOn() {
        return occurredOn;
    }
}
