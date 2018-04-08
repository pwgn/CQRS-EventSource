package dude.chrisp.cqrseventsource.domain.carmanager.core;

import dude.chrisp.cqrseventsource.domain.carmanager.api.CarManagerService;
import dude.chrisp.cqrseventsource.domain.carmanager.api.entity.AddCar;
import dude.chrisp.cqrseventsource.domain.carmanager.api.entity.Car;
import dude.chrisp.cqrseventsource.domain.carmanager.api.exception.CarNotAvailableException;
import dude.chrisp.cqrseventsource.domain.carmanager.spi.CarRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SimpleCarManagerService implements CarManagerService {

	private final CarRepository carRepository;
	
	public SimpleCarManagerService(CarRepository carRepository) {
		this.carRepository = carRepository;
	}
	
	@Override
	public List<Car> getAvailableCars() {
		return carRepository.findAvailableCars().collect(Collectors.toList());
	}

	@Override
	public List<Car> getUnavailableCars() {
		return carRepository.findUnavailableCars().collect(Collectors.toList());
	}

	@Override
	public Car addCar(AddCar car) {
		return carRepository.addCar(car);
	}

	@Override
	public Car checkoutCar(String carId) throws CarNotAvailableException {
		Car car = carRepository.getCarById(carId);
		
		if (car == null || !car.available) throw new CarNotAvailableException();

		return updateCarAvailability(car, false);
	}
 
	@Override
	public Car checkinCar(String carId) {
		Car car = carRepository.getCarById(carId);
		return updateCarAvailability(car, true);
	}

	private Car updateCarAvailability(Car car, boolean isAvailable) {
		car.available = isAvailable;
		return carRepository.updateCar(car);
	}

}
