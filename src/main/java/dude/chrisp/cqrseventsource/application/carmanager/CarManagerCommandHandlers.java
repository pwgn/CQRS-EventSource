package dude.chrisp.cqrseventsource.application.carmanager;

import dude.chrisp.cqrseventsource.application.carmanager.commands.AddCarCommand;
import dude.chrisp.cqrseventsource.application.carmanager.commands.CheckinCarCommand;
import dude.chrisp.cqrseventsource.application.carmanager.commands.CheckoutCarCommand;
import dude.chrisp.cqrseventsource.domain.carmanager.model.Car;
import dude.chrisp.cqrseventsource.domain.carmanager.repository.CarWriteRepository;
import dude.chrisp.cqrseventsource.domain.carmanager.spi.CarRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CarManagerCommandHandlers {

    private final CarWriteRepository carWriteRepository;
    private final CarRepository carRepository;

    public CarManagerCommandHandlers(CarWriteRepository carWriteRepository, CarRepository carRepository) {
        this.carWriteRepository = carWriteRepository;
        this.carRepository = carRepository;
    }

    public Car handle(AddCarCommand addCarCommand) throws Exception {
        String id = UUID.randomUUID().toString().subSequence(0,2).toString();
        Car newCar = new Car(id, addCarCommand.rate, addCarCommand.carModel);

        // Write
        carWriteRepository.save(newCar, -1);

        // read
        carRepository.addCar(newCar);

        return newCar;
    }

    public void handle(CheckinCarCommand checkinCarCommand) throws Exception {
        // write
        Car car = carWriteRepository.GetById(checkinCarCommand.carId);
        car.checkin();
        carWriteRepository.save(car, checkinCarCommand.originalVersion);

        // read
        carRepository.updateCar(car);

    }

    public void handle(CheckoutCarCommand checkoutCarCommand) throws Exception {
        // write
        Car car = carWriteRepository.GetById(checkoutCarCommand.carId);
        car.checkout();
        carWriteRepository.save(car, checkoutCarCommand.originalVersion);

        // read
        carRepository.updateCar(car);

    }
}
