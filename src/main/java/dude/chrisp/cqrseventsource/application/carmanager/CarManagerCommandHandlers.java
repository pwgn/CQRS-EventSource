package dude.chrisp.cqrseventsource.application.carmanager;

import dude.chrisp.cqrseventsource.application.carmanager.commands.AddCarCommand;
import dude.chrisp.cqrseventsource.application.carmanager.commands.CheckinCarCommand;
import dude.chrisp.cqrseventsource.application.carmanager.commands.CheckoutCarCommand;
import dude.chrisp.cqrseventsource.domain.car.Car;
import dude.chrisp.cqrseventsource.domain.car.repository.CarWriteRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CarManagerCommandHandlers {

    private final CarWriteRepository carWriteRepository;

    public CarManagerCommandHandlers(CarWriteRepository carWriteRepository) {
        this.carWriteRepository = carWriteRepository;
    }

    public Car handle(AddCarCommand addCarCommand) throws Exception {
        String id = UUID.randomUUID().toString().subSequence(0,2).toString();
        Car newCar = new Car(id, addCarCommand.rate, addCarCommand.carModel);
        carWriteRepository.save(newCar, -1);
        return newCar;
    }

    public void handle(CheckinCarCommand checkinCarCommand) throws Exception {
        Car car = carWriteRepository.GetById(checkinCarCommand.carId);
        car.checkin();
        carWriteRepository.save(car, checkinCarCommand.originalVersion);
    }

    public void handle(CheckoutCarCommand checkoutCarCommand) throws Exception {
        Car car = carWriteRepository.GetById(checkoutCarCommand.carId);
        car.checkout();
        carWriteRepository.save(car, checkoutCarCommand.originalVersion);
    }
}
