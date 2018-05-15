package dude.chrisp.cqrseventsource.infrastructure.eventstore.inmemory;

import dude.chrisp.cqrseventsource.common.domain.Event;
import dude.chrisp.cqrseventsource.domain.car.event.CarCreatedEvent;
import dude.chrisp.cqrseventsource.domain.car.spi.EventStore;
import dude.chrisp.cqrseventsource.infrastructure.eventstore.bus.EventPublisher;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class InMemoryEventStore implements EventStore {

    private EventPublisher eventPublisher;
    private Map<String, List<EventDescriptor>> eventStore;

    public InMemoryEventStore(EventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;

        eventStore = new HashMap<>();

        List<EventDescriptor> car1 = new ArrayList<>();
        car1.add(new EventDescriptor("1", new CarCreatedEvent("1", 10, "Audi A5", true), 0));
        eventStore.put("1", car1);

        List<EventDescriptor> car2 = new ArrayList<>();
        car2.add(new EventDescriptor("2", new CarCreatedEvent("2", 20, "BMW 118i", true), 0));
        eventStore.put("2", car2);

        List<EventDescriptor> car3 = new ArrayList<>();
        car3.add(new EventDescriptor("3", new CarCreatedEvent("3", 30, "Volvo V40", true), 0));
        eventStore.put("3", car3);

        List<EventDescriptor> car4 = new ArrayList<>();
        car4.add(new EventDescriptor("4", new CarCreatedEvent("4", 40, "Ford Focus", true), 0));
        eventStore.put("4", car4);
    }

    @Override
    public void saveEvents(String aggregateId, List<Event> events, int expectedVersion) throws Exception {
        List<EventDescriptor> eventDescriptors = eventStore.get(aggregateId);

        if (eventDescriptors == null) {
            eventDescriptors = new ArrayList<>();
            eventStore.put(aggregateId, eventDescriptors);
        } else if (eventDescriptors.get(eventDescriptors.size() - 1).version != expectedVersion
                && expectedVersion != -1) {
            throw new Exception("concurrency error");
        }

        int i = expectedVersion;
        for (Event event : events) {
            i++;
            event.version = i;
            eventDescriptors.add(new EventDescriptor(aggregateId, event, i));

            eventPublisher.sendMessage(event);
        }
    }

    @Override
    public List<Event> getEventsForAggregate(String aggregateId) throws Exception {
        List<EventDescriptor> eventDescriptors = eventStore.get(aggregateId);

        if (eventDescriptors == null) {
            throw new Exception("aggregateId not found");
        }

        return eventDescriptors.stream()
                .map(eventDescriptor -> eventDescriptor.event)
                .collect(Collectors.toList());
    }
}
