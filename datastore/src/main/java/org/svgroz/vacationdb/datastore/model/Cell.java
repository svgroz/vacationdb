package org.svgroz.vacationdb.datastore.model;

import org.svgroz.vacationdb.datastore.exception.ColumnTypeValueTypeMismatch;
import org.svgroz.vacationdb.datastore.exception.DifferentCellsTypesException;
import org.svgroz.vacationdb.datastore.exception.UnsupportedColumnType;

import java.util.Objects;

/**
 * That class is null safety, thread safety, and immutable.
 */
public class Cell implements Comparable<Cell> {
    private final ColumnType type;
    private final Object value;

    /**
     * Default constructor
     *
     * @param type  cannot be null
     * @param value can be null
     * @throws NullPointerException        if type is null
     * @throws ColumnTypeValueTypeMismatch if value type has mismatch with column type
     */
    public Cell(final ColumnType type, final Object value) throws ColumnTypeValueTypeMismatch {
        this.type = Objects.requireNonNull(type, "type is null");
        if (value == null) {
            this.value = null;
        } else if (type.valueHasValidType(value)) {
            this.value = value;
        } else {
            throw new ColumnTypeValueTypeMismatch(type, value);
        }
    }

    @Override
    public int compareTo(final Cell target) {
        Objects.requireNonNull(target, "target is null");
        if (this.type != target.type) {
            throw new DifferentCellsTypesException(this, target);
        }

        Object targetValue = target.getValue();

        if (this.value == null && targetValue == null) {
            return 0;
        }

        if (this.value == null) {
            return -1;
        }

        if (targetValue == null) {
            return 1;
        }

        switch (this.type) {
            case BOOLEAN:
                return ((Boolean) this.value).compareTo((Boolean) targetValue);
            case LONG:
                return ((Long) this.value).compareTo((Long) targetValue);
            case DOUBLE:
                return ((Double) this.value).compareTo((Double) targetValue);
            case STRING:
                return ((String)this.value).compareTo((String) targetValue);
            default:
                throw new UnsupportedColumnType(this.type);
        }
    }

    public ColumnType getType() {
        return type;
    }

    public Object getValue() {
        return value;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof Cell)) return false;
        final Cell cell = (Cell) o;
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
