package dude.chrisp.cqrseventsource.common.domain;

import java.util.ArrayList;
import java.util.List;

public abstract class AggregateRoot {
    private List<Event> changes;

    public int version;
    public String id;

    public AggregateRoot(String id) {
        this.id = id;
        changes = new ArrayList<>();
    }

    public abstract void apply(Event event);

    public List<Event> getUncommitedChanges() {
        return changes;
    }

    public void loadFromHistory(List<Event> history) {
        history.forEach(event -> this.applyChange(event, false));
    }

    public void applyChange(Event event) {
        applyChange(event, true);
    }

    private void applyChange(Event event, boolean isNew) {
        apply(event);
        if (isNew) changes.add(event);
    }
}

