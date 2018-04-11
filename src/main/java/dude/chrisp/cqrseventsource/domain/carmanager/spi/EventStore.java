package dude.chrisp.cqrseventsource.domain.carmanager.spi;

import dude.chrisp.cqrseventsource.common.domain.Event;

import java.util.List;

public interface EventStore {
    void saveEvents(String aggregateId, List<Event> events, int expectedVersion) throws Exception;
    List<Event> getEventsForAggregate(String aggregateId) throws Exception;
}
