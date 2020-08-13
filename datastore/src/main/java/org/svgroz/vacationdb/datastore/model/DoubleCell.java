package org.svgroz.vacationdb.datastore.model;

import org.svgroz.vacationdb.datastore.exception.CellsTypeMismatchException;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * That class is null safety, thread safety, and immutable.
 *
 * @author Simon Grozovsky svgroz@outlook.com
 */
public class DoubleCell implements TypedCell<Double> {
    private final Double value;

    /**
     * @param value supposed to be not null
     * @throws NullPointerException if value is null
     */
    public DoubleCell(final Double value) {
        this.value = Objects.requireNonNull(value, "value is null");
    }

    @Override
    public Double getValue() {
        return value;
    }

    @Override
    public Class<Double> supportedType() {
        return Double.class;
    }

    @Override
    public int compareTo(final Cell target) {
        Objects.requireNonNull(target, "target is null");
        if (EmptyCell.isEmpty(target)) {
            return 1;
        }

        if (target instanceof DoubleCell) {
            return value.compareTo(((DoubleCell) target).getValue());
        } else {
            throw new CellsTypeMismatchException(this, target);
        }
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof DoubleCell)) return false;
        final DoubleCell that = (DoubleCell) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", DoubleCell.class.getSimpleName() + "[", "]")
                .add("value=" + value)
                .toString();
    }
}
