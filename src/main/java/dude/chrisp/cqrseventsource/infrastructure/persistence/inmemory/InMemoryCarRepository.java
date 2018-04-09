package dude.chrisp.cqrseventsource.infrastructure.persistence.inmemory;

import dude.chrisp.cqrseventsource.domain.carmanager.model.Car;
import dude.chrisp.cqrseventsource.domain.carmanager.repository.CarRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Repository
public class InMemoryCarRepository implements CarRepository {

	private List<Car> cars;
	
	public InMemoryCarRepository() {
		cars = new ArrayList<Car>();
		cars.add(new Car("1", 10, "Audi A5"));
        cars.add(new Car("2", 20, "BMW 118i"));
        cars.add(new Car("3", 30, "Volvo V40"));
		cars.add(new Car("4", 40, "Ford Focus"));
	}

	@Override
	public Car getCarById(String id) {
	    return cars.stream()
                .filter(car -> car.id.equals(id))
                .findFirst()
                .orElse(null);
    }

	@Override
	public Stream<Car> getCars() {
		return cars.stream();
	}

    @Override
    public Car addCar(Car newCar) {
        newCar.id = "" + newCar.hashCode();
        cars.add(newCar);
        return newCar;
    }

	@Override
	public Car updateCar(Car updateCar) {
	    Car updatedCar = null;
        for (Car car : cars) {
            if (car.id.equals(updateCar.id)) {
                car.rate = updateCar.rate;
                car.carModel = updateCar.carModel;
                car.available = updateCar.available;
                updatedCar = car;
                break;
            }
        }

        return updatedCar;
    }
}
