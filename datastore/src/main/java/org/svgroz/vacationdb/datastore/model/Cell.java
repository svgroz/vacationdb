package org.svgroz.vacationdb.datastore.model;

import org.svgroz.vacationdb.datastore.exception.ColumnTypeValueTypeMismatch;

import java.util.Objects;

/**
 * That class is null safety, thread safety, and immutable.
 * @param <T> optional parameter for typed columns
 */
public class Cell<T> {
    private final ColumnType type;
    private final T value;

    /**
     * Default constructor
     * @param type cannot be null
     * @param value can be null
     * @throws NullPointerException if type is null
     * @throws ColumnTypeValueTypeMismatch if value type has mismatch with column type
     */
    public Cell(final ColumnType type, final T value) throws ColumnTypeValueTypeMismatch {
        this.type = Objects.requireNonNull(type, "type is null");
        if (value == null) {
            this.value = null;
        } else if (type.valueHasValidType(value)) {
            this.value = value;
        } else {
            throw new ColumnTypeValueTypeMismatch(type, value);
        }
    }

    public ColumnType getType() {
        return type;
    }

    public T getValue() {
        return value;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof Cell)) return false;
        final Cell<?> cell = (Cell<?>) o;
        return type == cell.type &&
                Objects.equals(value, cell.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, value);
    }

    @Override
    public String toString() {
        return "Cell{" +
                "type=" + type +
                ", value=" + value +
                '}';
    }
}
