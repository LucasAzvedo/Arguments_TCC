package br.com.arguments.apm;

import java.io.Serializable;

public abstract class AbstractEntity<K extends Serializable> implements Serializable {

    private static final long serialVersionUID = 1L;

    public abstract K getId();

    @Override
    public int hashCode() {
        return 31 * (1 + ((getId() == null) ? 0 : getId().hashCode()));
    }

    @Override
    public boolean equals(Object other) {
        return (other != null && hasSameClass(other)) && isThisEntityEqualTo((AbstractEntity<?>) other);
    }

    private boolean isThisEntityEqualTo(AbstractEntity<?> other) {
        return hasBothNullId(other) || hasSameId(other);
    }

    public boolean hasSameClass(Object other) {
        return getClass() == other.getClass() && other instanceof AbstractEntity;
    }

    private boolean hasSameId(AbstractEntity<?> other) {
        return getId() != null && getId().equals(other.getId());
    }

    private boolean hasBothNullId(AbstractEntity<?> other) {
        return getId() == null && other.getId() == null;
    }

    @Override
    public String toString() {
        return super.toString() + "#[id=" + getId() + "]";
    }

}
