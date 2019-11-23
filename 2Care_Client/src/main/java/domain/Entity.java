package domain;

import java.io.Serializable;

public abstract class Entity<T> implements Serializable {
    private T id;

    public T getId() {
        return id;
    }

    void setId(T id) {
        this.id = id;
    }
}
