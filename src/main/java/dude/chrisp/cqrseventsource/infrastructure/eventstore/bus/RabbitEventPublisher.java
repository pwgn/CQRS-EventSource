package dude.chrisp.cqrseventsource.infrastructure.eventstore.bus;

import dude.chrisp.cqrseventsource.application.carmanager.CarRabbitConfigurer;
import dude.chrisp.cqrseventsource.common.event.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitEventPublisher {

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public RabbitEventPublisher(final RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(Message message) {
        rabbitTemplate.convertAndSend(CarRabbitConfigurer.TOPIC_EXCHANGE_NAME, CarRabbitConfigurer.ROUTING_KEY, message);
    }
}
