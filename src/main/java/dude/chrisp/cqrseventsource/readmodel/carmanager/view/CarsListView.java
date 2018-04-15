package dude.chrisp.cqrseventsource.readmodel.carmanager.view;

import dude.chrisp.cqrseventsource.application.carmanager.CarRabbitConfigurer;
import dude.chrisp.cqrseventsource.domain.carmanager.event.CarCheckedinEvent;
import dude.chrisp.cqrseventsource.domain.carmanager.event.CarCheckedoutEvent;
import dude.chrisp.cqrseventsource.domain.carmanager.event.CarCreatedEvent;
import dude.chrisp.cqrseventsource.readmodel.carmanager.dto.CarDto;
import dude.chrisp.cqrseventsource.readmodel.carmanager.spi.CarReadRepository;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
@RabbitListener(id = "CarManagerReadModelListener",
        bindings = @QueueBinding(
                value = @Queue(value = CarRabbitConfigurer.QUEUE_CAR_MANAGER + ".CarsListView"),
                exchange = @Exchange(value = CarRabbitConfigurer.FANOUT_EXCHANGE_CAR_MANAGER, type = "fanout")
        )
)
public class CarsListView {
    private final CarReadRepository carReadRepository;

    public CarsListView(CarReadRepository carReadRepository) {
        this.carReadRepository = carReadRepository;
    }

    @RabbitHandler
    public void receiveMessage(CarCreatedEvent event) {
        System.out.println("CarsListView: Received car created event");
        CarDto newCar = new CarDto(event.version, event.id,
                event.rate, event.carModel, event.available);
        carReadRepository.addCar(newCar);
    }

    @RabbitHandler
    public void receiveMessage(CarCheckedinEvent event) {
        System.out.println("CarsListView: Received car checkedin event");
        CarDto car = carReadRepository.getCarById(event.id);
        car.version = event.version;
        car.available = false;
        carReadRepository.updateCar(car);
    }

    @RabbitHandler
    public void receiveMessage(CarCheckedoutEvent event) {
        System.out.println("CarsListView: Received car checkedout event");
        CarDto car = carReadRepository.getCarById(event.id);
        car.version = event.version;
        car.available = true;
        carReadRepository.updateCar(car);
    }

    @RabbitHandler(isDefault = true)
    public void receiveMessage(Object event) {
        System.out.println("CarsListView: Received unhandled message");
    }
}