package dude.chrisp.cqrseventsource.common.domain;

public interface Repository<T extends  AggregateRoot> {
    void save(AggregateRoot aggregateRoot, int expectedVersion) throws Exception;
    T GetById(String id) throws Exception;
}
