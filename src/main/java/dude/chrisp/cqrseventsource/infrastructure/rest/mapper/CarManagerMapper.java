package dude.chrisp.cqrseventsource.infrastructure.rest.mapper;

import dude.chrisp.cqrseventsource.domain.carmanager.api.entity.AddCar;
import dude.chrisp.cqrseventsource.domain.carmanager.api.entity.Car;
import dude.chrisp.cqrseventsource.infrastructure.rest.dto.AddCarDto;
import dude.chrisp.cqrseventsource.infrastructure.rest.dto.CarDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CarManagerMapper {
    CarManagerMapper INSTANCE = Mappers.getMapper(CarManagerMapper.class);

    CarDto carToCarDto(Car car);
    AddCar addCarDtoToAddCar(AddCarDto addCarDto);
}

