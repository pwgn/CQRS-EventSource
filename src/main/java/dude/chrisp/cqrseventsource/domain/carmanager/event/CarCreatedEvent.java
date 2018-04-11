package dude.chrisp.cqrseventsource.domain.carmanager.event;

import dude.chrisp.cqrseventsource.common.domain.Event;

import java.time.OffsetDateTime;
import java.time.ZoneId;

public class CarCreatedEvent implements Event {

    public String id;
    public int rate;
    public String carModel;
    public boolean available;
    private int version;
    private OffsetDateTime occuredOn;

    public CarCreatedEvent(String id, int rate, String carModel, boolean available, int version) {
        this.id = id;
        this.rate = rate;
        this.carModel = carModel;
        this.available = available;
        this.version = version;
        this.occuredOn = OffsetDateTime.now(ZoneId.of("Z"));
    }

    @Override
    public int eventVersion() {
        return version;
    }

    @Override
    public OffsetDateTime occurredOn() {
        return occuredOn;
    }
}
