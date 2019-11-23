package domain;

public abstract class Entity<T> {
    private T id;

    public T getId() {
        return id;
    }

    void setId(T id) {
        this.id = id;
    }
}
