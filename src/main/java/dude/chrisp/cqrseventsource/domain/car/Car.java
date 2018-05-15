package dude.chrisp.cqrseventsource.domain.car;

import dude.chrisp.cqrseventsource.common.domain.AggregateRoot;
import dude.chrisp.cqrseventsource.common.domain.Event;
import dude.chrisp.cqrseventsource.domain.car.event.CarCheckedinEvent;
import dude.chrisp.cqrseventsource.domain.car.event.CarCreatedEvent;
import dude.chrisp.cqrseventsource.domain.car.event.CarCheckedoutEvent;
import dude.chrisp.cqrseventsource.domain.car.exception.CarNotAvailableException;

public class Car extends AggregateRoot {
	public int rate;
	public String carModel;
	public boolean available;

    public Car() { super(null); }

    public Car(String id, int rate, String carModel) {
        super(id);
		applyChange(new CarCreatedEvent(id, rate, carModel, available));
	}

    public void checkin() throws CarNotAvailableException {
        if(!this.available) throw new CarNotAvailableException();
        this.available = false;
        applyChange(new CarCheckedinEvent(id));
    }

    public void checkout() {
        this.available = true;
        applyChange(new CarCheckedoutEvent(id));
    }

    @Override
    public void apply(Event event) {
        if (event instanceof CarCreatedEvent) apply((CarCreatedEvent) event);
        if (event instanceof CarCheckedinEvent) apply((CarCheckedinEvent) event);
        if (event instanceof CarCheckedoutEvent) apply((CarCheckedoutEvent) event);
        version = event.version;
    }

    private void apply(CarCreatedEvent event) {
        id = event.id;
        rate = event.rate;
        carModel = event.carModel;
        available = event.available;
    }

    private void apply(CarCheckedinEvent event) {
        available = false;
    }

    private void apply(CarCheckedoutEvent event) {
        available = true;
    }
}
