package dude.chrisp.cqrseventsource.readmodel.carmanager.queries;

public class CarsListQuery {
    public final Boolean available;

    public CarsListQuery(Boolean available) {
        this.available = available;
    }
}
