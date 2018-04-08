package dude.chrisp.cqrseventsource.domain.carmanager.spi;

import java.util.List;
import java.util.stream.Stream;

import dude.chrisp.cqrseventsource.domain.carmanager.api.entity.AddCar;
import dude.chrisp.cqrseventsource.domain.carmanager.api.entity.Car;

public interface CarRepository {
	Car getCarById(String id);
	Stream<Car> findAvailableCars();
	Stream<Car> findUnavailableCars();
	Car addCar(AddCar car);
    Car updateCar(Car updatedCar);
}
