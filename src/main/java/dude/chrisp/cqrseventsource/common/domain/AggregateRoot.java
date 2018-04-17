package dude.chrisp.cqrseventsource.common.domain;

import java.util.ArrayList;
import java.util.List;

public abstract class AggregateRoot {
    private List<Event> uncommitedEvents;

    public int version;
    public String id;

    public AggregateRoot(String id) {
        this.id = id;
        uncommitedEvents = new ArrayList<>();
    }

    public abstract void apply(Event event);

    public List<Event> getUncommitedEvents() {
        return uncommitedEvents;
    }

    public void applyEventHistory(List<Event> history) {
        history.forEach(event -> this.applyChange(event, false));
    }

    public void applyChange(Event event) {
        applyChange(event, true);
    }

    private void applyChange(Event event, boolean isNew) {
        if (isNew) uncommitedEvents.add(event);
        apply(event);
    }
}

