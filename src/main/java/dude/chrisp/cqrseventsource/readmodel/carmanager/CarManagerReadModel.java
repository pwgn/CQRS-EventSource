package dude.chrisp.cqrseventsource.readmodel.carmanager;

import dude.chrisp.cqrseventsource.application.carmanager.CarRabbitConfigurer;
import dude.chrisp.cqrseventsource.domain.carmanager.event.CarCheckedinEvent;
import dude.chrisp.cqrseventsource.domain.carmanager.event.CarCheckedoutEvent;
import dude.chrisp.cqrseventsource.domain.carmanager.event.CarCreatedEvent;
import dude.chrisp.cqrseventsource.domain.carmanager.model.Car;
import dude.chrisp.cqrseventsource.domain.carmanager.spi.CarRepository;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
@RabbitListener(id = "CarManagerReadModelListener",
        bindings = @QueueBinding(
                value = @Queue(value = CarRabbitConfigurer.QUEUE_CAR_MANAGER + ".CarCreatedEvent"),
                exchange = @Exchange(value = CarRabbitConfigurer.FANOUT_EXCHANGE_CAR_MANAGER, type = "fanout")
        )
)
public class CarManagerReadModel {
    private final CarRepository carRepository;

    public CarManagerReadModel(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public Stream<Car> GetAllCars() {
        return this.carRepository.getCars();
    }

    @RabbitHandler
    public void receiveMessage(CarCreatedEvent event) {
        System.out.println("Received car created event");
    }

    @RabbitHandler
    public void receiveMessage(CarCheckedinEvent event) {
        System.out.println("Received car checkedin event");
    }

    @RabbitHandler
    public void receiveMessage(CarCheckedoutEvent event) {
        System.out.println("Received car checkedout event");
    }

    @RabbitHandler(isDefault = true)
    public void receiveMessage(Object event) {
        System.out.println("Received unhandled message");
    }
}
