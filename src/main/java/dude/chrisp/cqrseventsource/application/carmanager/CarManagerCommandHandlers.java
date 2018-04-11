package dude.chrisp.cqrseventsource.application.carmanager;

import dude.chrisp.cqrseventsource.application.carmanager.commands.AddCarCommand;
import dude.chrisp.cqrseventsource.application.carmanager.commands.CheckinCarCommand;
import dude.chrisp.cqrseventsource.application.carmanager.commands.CheckoutCarCommand;
import dude.chrisp.cqrseventsource.domain.carmanager.model.Car;
import dude.chrisp.cqrseventsource.domain.carmanager.spi.CarRepository;
import org.springframework.stereotype.Service;

@Service
public class CarManagerCommandHandlers {

    private final CarRepository carRepository;

    public CarManagerCommandHandlers(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public Car handle(AddCarCommand addCarCommand) {
        Car newCar = new Car(null, addCarCommand.rate, addCarCommand.carModel);
        Car addedCar = carRepository.addCar(newCar);
        return addedCar;
    }

    public void handle(CheckinCarCommand checkinCarCommand) {
        Car car = carRepository.getCarById(checkinCarCommand.carId);
        car.checkin();
        carRepository.updateCar(car);
    }

    public void handle(CheckoutCarCommand checkoutCarCommand) {
        Car car = carRepository.getCarById(checkoutCarCommand.carId);
        car.checkout();
        carRepository.updateCar(car);
    }
}
