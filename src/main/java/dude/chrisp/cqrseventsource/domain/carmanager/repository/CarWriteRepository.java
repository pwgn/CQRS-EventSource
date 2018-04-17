package dude.chrisp.cqrseventsource.domain.carmanager.repository;

import dude.chrisp.cqrseventsource.common.domain.AggregateRoot;
import dude.chrisp.cqrseventsource.common.domain.Event;
import dude.chrisp.cqrseventsource.common.domain.Repository;
import dude.chrisp.cqrseventsource.domain.carmanager.model.Car;
import dude.chrisp.cqrseventsource.domain.carmanager.spi.EventStore;

import java.util.List;

@org.springframework.stereotype.Repository
public class CarWriteRepository implements Repository<Car> {

    private EventStore eventStore;

    public CarWriteRepository(EventStore eventStore) {
        this.eventStore = eventStore;
    }

    @Override
    public void save(AggregateRoot aggregateRoot, int expectedVersion) throws Exception {
        eventStore.saveEvents(aggregateRoot.id, aggregateRoot.getUncommitedEvents(), expectedVersion);
    }

    @Override
    public Car GetById(String id) throws Exception {
        Car car = new Car();
        List<Event> events = eventStore.getEventsForAggregate(id);
        car.applyEventHistory(events);
        return car;
    }
}
