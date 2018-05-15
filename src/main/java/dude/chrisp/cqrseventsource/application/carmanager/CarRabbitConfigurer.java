package dude.chrisp.cqrseventsource.application.carmanager;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.context.annotation.Bean;

@EnableRabbit
public class CarRabbitConfigurer {

    public static final String FANOUT_EXCHANGE_CAR_MANAGER = "exchange.fanout.car";
    public static final String QUEUE_CAR_MANAGER = "queue.car";

    @Bean
    public TopicExchange CarExchange() {
        return new TopicExchange(FANOUT_EXCHANGE_CAR_MANAGER);
    }
}
