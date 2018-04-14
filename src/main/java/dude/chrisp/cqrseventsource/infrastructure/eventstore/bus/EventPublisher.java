package dude.chrisp.cqrseventsource.infrastructure.eventstore.bus;

import dude.chrisp.cqrseventsource.common.event.Message;

public interface EventPublisher {
    void sendMessage(Message message);
}
