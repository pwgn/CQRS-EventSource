package dude.chrisp.cqrseventsource.domain.carmanager.api.entity;

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
}
