package dude.chrisp.cqrseventsource.infrastructure.eventstore.bus;

import dude.chrisp.cqrseventsource.application.carmanager.CarRabbitConfigurer;
import dude.chrisp.cqrseventsource.common.event.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitEventPublisher implements EventPublisher {

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public RabbitEventPublisher(final RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void sendMessage(Message message) {
        System.out.println("Message sent: " + message.messageKey);
        rabbitTemplate.convertAndSend(CarRabbitConfigurer.FANOUT_EXCHANGE_CAR_MANAGER, message.messageKey, message);
    }
}
