package fr.arpinum.graine.modele;

@SuppressWarnings("UnusedDeclaration")
public abstract class BaseAggregate<TId> extends BaseEntity<TId> implements Aggregate<TId> {

    protected BaseAggregate() {
    }

    protected BaseAggregate(TId tId) {
        super(tId);
    }
}
