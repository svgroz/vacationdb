package org.svgroz.vacationdb.datastore.model;

import org.svgroz.vacationdb.datastore.exception.CellsTypeMismatchException;

import java.util.Objects;

/**
 * That class is null safety, thread safety, and immutable.
 */
public class DoubleCell implements TypedCell<Double> {
    private final Double value;

    /**
     * @param value cannot be null
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
    public int compareTo(final Cell o) {
        if (EmptyCell.isEmpty(o)) {
            return 1;
        }

        if (o instanceof DoubleCell) {
            return value.compareTo(((DoubleCell) o).getValue());
        } else {
            throw new CellsTypeMismatchException(this, o);
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
        return "DoubleCell{" +
                "value=" + value +
                '}';
    }
}
