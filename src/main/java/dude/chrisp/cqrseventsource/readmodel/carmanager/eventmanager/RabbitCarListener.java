package dude.chrisp.cqrseventsource.readmodel.carmanager.eventmanager;

import dude.chrisp.cqrseventsource.application.carmanager.CarRabbitConfigurer;
import dude.chrisp.cqrseventsource.common.event.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitCarListener {

    @RabbitListener(queues = CarRabbitConfigurer.QUEUE_NAME)
    public void receiveMessage(Message message) {
        System.out.println("Received some message");
    }
}
