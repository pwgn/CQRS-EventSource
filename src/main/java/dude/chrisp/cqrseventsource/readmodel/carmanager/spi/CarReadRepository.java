package dude.chrisp.cqrseventsource.readmodel.carmanager.spi;

import java.util.stream.Stream;

import dude.chrisp.cqrseventsource.readmodel.carmanager.dto.CarDto;


public interface CarReadRepository {
	CarDto getCarById(String id);
	Stream<CarDto> getCars();
    CarDto addCar(CarDto car);
    CarDto updateCar(CarDto updatedCar);
}
