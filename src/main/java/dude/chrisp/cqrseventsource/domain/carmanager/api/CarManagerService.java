package dude.chrisp.cqrseventsource.domain.carmanager.api;

import java.util.List;

import dude.chrisp.cqrseventsource.domain.carmanager.api.entity.AddCar;
import dude.chrisp.cqrseventsource.domain.carmanager.api.entity.Car;
import dude.chrisp.cqrseventsource.domain.carmanager.api.exception.CarNotAvailableException;

public interface CarManagerService {

	List<Car> getAvailableCars();
	List<Car> getUnavailableCars();
	Car addCar(AddCar car);
	Car checkoutCar(String carId) throws CarNotAvailableException; 
	Car checkinCar(String carId);
}
