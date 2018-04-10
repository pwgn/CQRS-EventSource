package dude.chrisp.cqrseventsource.readmodel.carmanager;

import dude.chrisp.cqrseventsource.domain.carmanager.model.Car;
import dude.chrisp.cqrseventsource.domain.carmanager.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class CarManagerReadModel {
    private final CarRepository carRepository;

    public CarManagerReadModel(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public Stream<Car> GetAllCars() {
        return this.carRepository.getCars();
    }
}
