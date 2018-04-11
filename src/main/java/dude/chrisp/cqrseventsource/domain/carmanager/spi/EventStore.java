package dude.chrisp.cqrseventsource.domain.carmanager.spi;

import dude.chrisp.cqrseventsource.common.domain.Event;

import java.util.List;

public interface EventStore {
    void saveEvents(String aggregateId, List<Event> events, int expectedVersion);
    List<Event> getEventsForAggregate(String aggregateId);
}
