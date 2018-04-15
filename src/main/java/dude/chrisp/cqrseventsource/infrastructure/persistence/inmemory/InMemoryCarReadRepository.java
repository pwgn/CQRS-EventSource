package dude.chrisp.cqrseventsource.infrastructure.persistence.inmemory;

import dude.chrisp.cqrseventsource.readmodel.carmanager.dto.CarDto;
import dude.chrisp.cqrseventsource.readmodel.carmanager.spi.CarReadRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Repository
public class InMemoryCarReadRepository implements CarReadRepository {

	private List<CarDto> cars;
	
	public InMemoryCarReadRepository() {
		cars = new ArrayList<CarDto>();
		cars.add(new CarDto(0, "1", 10, "Audi A5", true));
        cars.add(new CarDto(0, "2", 20, "BMW 118i", true));
        cars.add(new CarDto(0, "3", 30, "Volvo V40", true));
		cars.add(new CarDto(0, "4", 40, "Ford Focus",true));
	}

	@Override
	public CarDto getCarById(String id) {
	    return cars.stream()
                .filter(car -> car.id.equals(id))
                .findFirst()
                .orElse(null);
    }

	@Override
	public Stream<CarDto> getCars() {
		return cars.stream();
	}

    @Override
    public CarDto addCar(CarDto newCar) {
        cars.add(newCar);
        return newCar;
    }

	@Override
	public CarDto updateCar(CarDto updateCar) {
	    CarDto updatedCar = null;
        for (CarDto car : cars) {
            if (car.id.equals(updateCar.id)) {
                car.version = updateCar.version;
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
