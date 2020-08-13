package org.svgroz.vacationdb.datastore.model;

import org.svgroz.vacationdb.datastore.exception.CellsTypeMismatchException;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * That class is null safety, thread safety, and immutable.
 *
 * @author Simon Grozovsky svgroz@outlook.com
 */
public class BooleanCell implements TypedCell<Boolean> {
    private final Boolean value;

    /**
     * @param value cannot be null
     * @throws NullPointerException if value is null
     */
    public BooleanCell(final Boolean value) {
        this.value = Objects.requireNonNull(value, "value is null");
    }

    @Override
    public Boolean getValue() {
        return value;
    }

    @Override
    public Class<Boolean> supportedType() {
        return Boolean.class;
    }

    @Override
    public int compareTo(final Cell target) {
        Objects.requireNonNull(target, "target is null");
        if (EmptyCell.isEmpty(target)) {
            return 1;
        }

        if (target instanceof BooleanCell) {
            return value.compareTo(((BooleanCell) target).getValue());
        } else {
            throw new CellsTypeMismatchException(this, target);
        }
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof BooleanCell)) return false;
        final BooleanCell that = (BooleanCell) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", BooleanCell.class.getSimpleName() + "[", "]")
                .add("value=" + value)
                .toString();
    }
}