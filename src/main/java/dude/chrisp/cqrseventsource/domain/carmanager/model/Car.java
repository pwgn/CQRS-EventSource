package dude.chrisp.cqrseventsource.domain.carmanager.model;

import dude.chrisp.cqrseventsource.common.domain.AggregateRoot;
import dude.chrisp.cqrseventsource.common.domain.Event;
import dude.chrisp.cqrseventsource.domain.carmanager.event.CarCheckedinEvent;
import dude.chrisp.cqrseventsource.domain.carmanager.event.CarCreatedEvent;
import dude.chrisp.cqrseventsource.domain.carmanager.event.CarCheckedoutEvent;
import dude.chrisp.cqrseventsource.domain.carmanager.exception.CarNotAvailableException;

public class Car extends AggregateRoot {
	public int rate;
	public String carModel;
	public boolean available;

    public Car() { super(null); }

    public Car(String id, int rate, String carModel) {
        super(id);
		this.rate = rate;
		this.carModel = carModel;
		this.available = true;

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
