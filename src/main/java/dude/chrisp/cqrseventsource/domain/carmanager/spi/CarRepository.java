package dude.chrisp.cqrseventsource.domain.carmanager.spi;

import java.util.stream.Stream;

import dude.chrisp.cqrseventsource.domain.carmanager.model.Car;


public interface CarRepository {
	Car getCarById(String id);
	Stream<Car> getCars();
    Car addCar(Car car);
    Car updateCar(Car updatedCar);
}
