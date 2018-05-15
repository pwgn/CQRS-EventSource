package dude.chrisp.cqrseventsource.readmodel.carmanager.view;

import dude.chrisp.cqrseventsource.application.carmanager.CarRabbitConfigurer;
import dude.chrisp.cqrseventsource.domain.car.event.CarCheckedinEvent;
import dude.chrisp.cqrseventsource.domain.car.event.CarCheckedoutEvent;
import dude.chrisp.cqrseventsource.domain.car.event.CarCreatedEvent;
import dude.chrisp.cqrseventsource.readmodel.carmanager.dto.CarDto;
import dude.chrisp.cqrseventsource.readmodel.carmanager.spi.CarReadRepository;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Service;

@Service
public class CarsListView {
    private final CarReadRepository carReadRepository;

    public CarsListView(CarReadRepository carReadRepository) {
        this.carReadRepository = carReadRepository;
    }

    @RabbitListener(id = "CarManagerReadModelListener3",
            bindings = @QueueBinding(
                    value = @Queue(value = CarRabbitConfigurer.QUEUE_CAR_MANAGER + ".CarsListView"),
                    exchange = @Exchange(value = CarRabbitConfigurer.FANOUT_EXCHANGE_CAR_MANAGER, type = "topic"),
                    key = "CarCreatedEvent"
            )

    )
    public void receiveMessage(CarCreatedEvent event) {
        System.out.println("CarsListView: Received car created event");
        CarDto newCar = new CarDto(event.version, event.id,
                event.rate, event.carModel, event.available);
        carReadRepository.addCar(newCar);
    }

    @RabbitListener(id = "CarManagerReadModelListener2",
            bindings = @QueueBinding(
                    value = @Queue(value = CarRabbitConfigurer.QUEUE_CAR_MANAGER + ".CarsListView"),
                    exchange = @Exchange(value = CarRabbitConfigurer.FANOUT_EXCHANGE_CAR_MANAGER, type = "topic"),
                    key = "CarCheckedinEvent"

            )

    )
    public void receiveMessage(CarCheckedinEvent event) {
        System.out.println("CarsListView: Received car checkedin event");
        CarDto car = carReadRepository.getCarById(event.id);
        car.version = event.version;
        car.available = false;
        carReadRepository.updateCar(car);
    }

    @RabbitListener(id = "CarManagerReadModelListener1",
            bindings = @QueueBinding(
                    value = @Queue(value = CarRabbitConfigurer.QUEUE_CAR_MANAGER + ".CarsListView"),
                    exchange = @Exchange(value = CarRabbitConfigurer.FANOUT_EXCHANGE_CAR_MANAGER, type = "topic"),
                    key = "CarCheckedoutEvent"
            )

    )
    public void receiveMessage(CarCheckedoutEvent event) {
        System.out.println("CarsListView: Received car checkedout event");
        CarDto car = carReadRepository.getCarById(event.id);
        car.version = event.version;
        car.available = true;
        carReadRepository.updateCar(car);
    }

}