package dude.chrisp.cqrseventsource.application.carmanager;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.context.annotation.Bean;

@EnableRabbit
public class CarRabbitConfigurer {

    public static final String FANOUT_EXCHANGE_CAR_MANAGER = "exchange.fanout.carmanager";
    public static final String QUEUE_CAR_MANAGER = "queue.carmanager";

    @Bean
    public FanoutExchange CarExchange() {
        return new FanoutExchange(FANOUT_EXCHANGE_CAR_MANAGER);
    }
}
