package dude.chrisp.cqrseventsource.application.carmanager;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.context.annotation.Bean;

@EnableRabbit
public class CarRabbitConfigurer {

    public static final String TOPIC_EXCHANGE_NAME = "carmanager-exchange";
    public static final String QUEUE_NAME = "carmanager-queue";
    public static final String ROUTING_KEY = "messages.car";

    @Bean
    public TopicExchange CarExchange() {
        return new TopicExchange(TOPIC_EXCHANGE_NAME);
    }

    @Bean
    public Queue carQueue() {
        return new Queue(QUEUE_NAME);
    }

    @Bean
    public Binding declareBindingCarManager(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
    }
}
