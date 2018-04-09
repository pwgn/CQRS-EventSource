package dude.chrisp.cqrseventsource.infrastructure.rest.mapper;

import dude.chrisp.cqrseventsource.application.carmanager.commands.AddCarCommand;
import dude.chrisp.cqrseventsource.domain.carmanager.model.Car;
import dude.chrisp.cqrseventsource.infrastructure.rest.dto.AddCarDto;
import dude.chrisp.cqrseventsource.infrastructure.rest.dto.CarDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CarManagerMapper {
    CarManagerMapper INSTANCE = Mappers.getMapper(CarManagerMapper.class);

    CarDto carToCarDto(Car car);
    AddCarCommand addCarDtoToAddCarCommand(AddCarDto addCarDto);
}

