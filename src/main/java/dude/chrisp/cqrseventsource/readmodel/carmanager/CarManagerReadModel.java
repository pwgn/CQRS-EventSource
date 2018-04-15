package dude.chrisp.cqrseventsource.readmodel.carmanager;

import dude.chrisp.cqrseventsource.readmodel.carmanager.dto.CarDto;
import dude.chrisp.cqrseventsource.readmodel.carmanager.queries.CarsListQuery;
import dude.chrisp.cqrseventsource.readmodel.carmanager.spi.CarReadRepository;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class CarManagerReadModel {
    private final CarReadRepository carReadRepository;

    public CarManagerReadModel(CarReadRepository carReadRepository) {
        this.carReadRepository = carReadRepository;
    }

    public Stream<CarDto> GetCars(CarsListQuery query) {
        return this.carReadRepository.getCars()
                .filter(car -> query.available == null || car.available == query.available);
    }

}
