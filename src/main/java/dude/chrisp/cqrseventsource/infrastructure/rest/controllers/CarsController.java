package dude.chrisp.cqrseventsource.infrastructure.rest.controllers;

import dude.chrisp.cqrseventsource.application.carmanager.CarManagerCommandHandlers;
import dude.chrisp.cqrseventsource.application.carmanager.commands.CheckinCarCommand;
import dude.chrisp.cqrseventsource.application.carmanager.commands.CheckoutCarCommand;
import dude.chrisp.cqrseventsource.domain.carmanager.exception.CarNotAvailableException;
import dude.chrisp.cqrseventsource.domain.carmanager.model.Car;
import dude.chrisp.cqrseventsource.infrastructure.rest.mapper.CarManagerMapper;
import dude.chrisp.cqrseventsource.infrastructure.rest.dto.AddCarDto;
import dude.chrisp.cqrseventsource.readmodel.carmanager.CarManagerReadModel;
import dude.chrisp.cqrseventsource.readmodel.carmanager.dto.CarDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/cars")
public class CarsController {

	private final CarManagerCommandHandlers carManagerCommandHandlers;
	private final CarManagerReadModel carManagerReadModel;
	private final CarManagerMapper carManagerMapper;

	public CarsController(CarManagerCommandHandlers carManagerCommandHandlers,
                          CarManagerReadModel carManagerReadModel,
                          CarManagerMapper carManagerMapper) {
		this.carManagerCommandHandlers = carManagerCommandHandlers;
		this.carManagerReadModel = carManagerReadModel;
		this.carManagerMapper = carManagerMapper;
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<CarDto> getCars() {
	    List<CarDto> cars = carManagerReadModel.GetAllCars()
                .parallel()
                .map(car -> carManagerMapper.carToCarDto(car))
                .collect(Collectors.toList());
		return cars;
	}

    @RequestMapping(method = RequestMethod.POST)
	public CarDto postCar(@RequestBody AddCarDto addCarDto) {
		Car car = carManagerCommandHandlers.handle(
		        carManagerMapper.addCarDtoToAddCarCommand(addCarDto));
		return carManagerMapper.carToCarDto(car);
    }

    @RequestMapping(value="/{carId}/checkin", method = RequestMethod.PUT)
    public void checkinCar(@PathVariable String carId) {
	    carManagerCommandHandlers.handle(new CheckinCarCommand(carId));
    }

    @RequestMapping(value="/{carId}/checkout", method = RequestMethod.PUT)
    public void checkoutCar(@PathVariable String carId) {
        carManagerCommandHandlers.handle(new CheckoutCarCommand(carId));
    }

    @ResponseStatus(value = HttpStatus.CONFLICT, reason = "car not available")
    @ExceptionHandler(CarNotAvailableException.class)
    void handleCarTonAvailableException() {

    }

}
