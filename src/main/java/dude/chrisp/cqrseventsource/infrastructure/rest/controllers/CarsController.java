package dude.chrisp.cqrseventsource.infrastructure.rest.controllers;

import dude.chrisp.cqrseventsource.domain.carmanager.api.CarManagerService;
import dude.chrisp.cqrseventsource.domain.carmanager.api.entity.Car;
import dude.chrisp.cqrseventsource.domain.carmanager.api.exception.CarNotAvailableException;
import dude.chrisp.cqrseventsource.infrastructure.rest.mapper.CarManagerMapper;
import dude.chrisp.cqrseventsource.infrastructure.rest.dto.AddCarDto;
import dude.chrisp.cqrseventsource.infrastructure.rest.dto.CarDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cars")
public class CarsController {

	private final CarManagerService carManagerService;
	private final CarManagerMapper carManagerMapper;

	public CarsController(CarManagerService carManagerService, CarManagerMapper carManagerMapper) {
		this.carManagerService = carManagerService;
		this.carManagerMapper = carManagerMapper;
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<CarDto> getCars() {
	    List<CarDto> cars = carManagerService.getAvailableCars()
                .parallelStream().map(car -> carManagerMapper.carToCarDto(car))
                .collect(Collectors.toList());
		return cars;
	}

    @RequestMapping(method = RequestMethod.POST)
	public CarDto postCar(@RequestBody AddCarDto addCarDto) {
		Car car = carManagerService.addCar(carManagerMapper.addCarDtoToAddCar(addCarDto));
		return carManagerMapper.carToCarDto(car);
    }

    @RequestMapping(value="/{carId}/checkin", method = RequestMethod.PUT)
    public CarDto checkinCar(@PathVariable String carId) {
	    Car car = carManagerService.checkinCar(carId);
	    return carManagerMapper.carToCarDto(car);
    }

    @RequestMapping(value="/{carId}/checkout", method = RequestMethod.PUT)
    public CarDto checkoutCar(@PathVariable String carId) {
        Car car = carManagerService.checkoutCar(carId);
        return carManagerMapper.carToCarDto(car);
    }

    @ResponseStatus(value = HttpStatus.CONFLICT, reason = "car not available")
    @ExceptionHandler(CarNotAvailableException.class)
    void handleCarTonAvailableException() {

    }

}
