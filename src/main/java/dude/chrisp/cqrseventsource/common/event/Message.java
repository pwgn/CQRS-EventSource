package dude.chrisp.cqrseventsource.common.event;

import java.io.Serializable;

public class Message implements Serializable {
    public final String messageKey;

    public Message(String messageKey) {
        this.messageKey = messageKey;
    }
}
