package org.svgroz.vacationdb.datastore.model.cell;

import org.svgroz.vacationdb.datastore.exception.CellsTypeMismatchException;
import org.svgroz.vacationdb.datastore.model.DataType;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * That class is null safety, thread safety, and immutable.
 *
 * @author Simon Grozovsky svgroz@outlook.com
 */
final class DoubleCell implements TypedCell<Double> {

    private static final DataType SUPPORTED_TYPE = DataType.DOUBLE;

    private final double value;

    /**
     * @param value supposed to be not null
     * @throws NullPointerException if value is null
     */
    DoubleCell(final Double value) {
        this.value = Objects.requireNonNull(value, "value is null");
    }

    @Override
    public Double getValue() {
        return value;
    }

    @Override
    public DataType supportedType() {
        return SUPPORTED_TYPE;
    }

    @Override
    public int compareTo(final Cell target) {
        Objects.requireNonNull(target, "target is null");

        if (supportedType() == target.supportedType()) {
            return Double.compare(value, ((DoubleCell) target).value);
        }

        if (EmptyCell.isEmpty(target)) {
            return 1;
        }

        throw new CellsTypeMismatchException(this, target);

    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof DoubleCell)) return false;
        final DoubleCell that = (DoubleCell) o;
        return Double.compare(that.value, value) == 0;
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
