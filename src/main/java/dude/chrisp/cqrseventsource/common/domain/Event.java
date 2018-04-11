package dude.chrisp.cqrseventsource.common.domain;

import java.time.OffsetDateTime;

public interface Event {
    int eventVersion();
    OffsetDateTime occurredOn();
}
