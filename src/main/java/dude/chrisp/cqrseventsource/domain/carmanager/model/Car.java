package dude.chrisp.cqrseventsource.domain.carmanager.model;

import dude.chrisp.cqrseventsource.domain.carmanager.exception.CarNotAvailableException;

public class Car {
    public String id;
	public int rate;
	public String carModel;
	public boolean available;

	public Car(String id, int rate, String carModel) {
		this.id = id;
		this.rate = rate;
		this.carModel = carModel;
		this.available = true;
	}

    public void checkin() throws CarNotAvailableException {
        if(!this.available) throw new CarNotAvailableException();
        this.available = false;
    }

    public void checkout() {
        this.available = true;
    }
}
