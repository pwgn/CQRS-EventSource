package dude.chrisp.cqrseventsource.infrastructure.eventstore.inmemory;

import dude.chrisp.cqrseventsource.common.domain.Event;
import dude.chrisp.cqrseventsource.domain.carmanager.spi.EventStore;
import dude.chrisp.cqrseventsource.infrastructure.eventstore.EventDescriptor;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class InMemoryEventStore implements EventStore {

    private Map<String, List<EventDescriptor>> eventStore;

    public InMemoryEventStore() {
        eventStore = new HashMap<>();
    }

    @Override
    public void saveEvents(String aggregateId, List<Event> events, int expectedVersion) throws Exception {
        List<EventDescriptor> eventDescriptors = eventStore.get(aggregateId);

        if(eventDescriptors == null) {
            eventDescriptors = new ArrayList<>();
            eventStore.put(aggregateId, eventDescriptors);
        }
        else if (eventDescriptors.get(eventDescriptors.size() - 1).version != expectedVersion
                && expectedVersion != -1) {
            throw new Exception("concurrency error");
        }

        int i = expectedVersion;
        for(Event event : events) {
            i++;
            event.version = i;
            eventDescriptors.add(new EventDescriptor(aggregateId, event, i));
        }
    }

    @Override
    public List<Event> getEventsForAggregate(String aggregateId) throws Exception {
        List<EventDescriptor> eventDescriptors = eventStore.get(aggregateId);

        if(eventDescriptors == null) {
            throw new Exception("aggregateId not found");
        }

        return eventDescriptors.stream()
                .map(eventDescriptor -> eventDescriptor.event)
                .collect(Collectors.toList());
    }
}
