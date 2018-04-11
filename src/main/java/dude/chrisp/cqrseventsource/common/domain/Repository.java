package dude.chrisp.cqrseventsource.common.domain;

public interface Repository<T extends  AggregateRoot> {
    void save(AggregateRoot aggregateRoot, int expectedVersion);
    T GetById(String id);
}
