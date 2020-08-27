package org.svgroz.vacationdb.datastore.impl.model.cell;

import org.svgroz.vacationdb.datastore.api.exception.CellsTypeMismatchException;
import org.svgroz.vacationdb.datastore.api.model.DataType;
import org.svgroz.vacationdb.datastore.api.model.cell.Cell;
import org.svgroz.vacationdb.datastore.api.model.cell.DoubleCell;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * That class is null safety, thread safety, and immutable.
 *
 * @author Simon Grozovsky svgroz@outlook.com
 */
final class DefaultDoubleCell implements DoubleCell {

    private static final DataType SUPPORTED_TYPE = DataType.DOUBLE;

    private final double value;

    /**
     * @param value supposed to be not null
     * @throws NullPointerException if value is null
     */
    DefaultDoubleCell(final Double value) {
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
            return Double.compare(value, ((DefaultDoubleCell) target).value);
        }

        if (DefaultEmptyCell.isEmpty(target)) {
            return 1;
        }

        throw new CellsTypeMismatchException(this, target);

    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof DefaultDoubleCell)) return false;
        final DefaultDoubleCell that = (DefaultDoubleCell) o;
        return Double.compare(that.value, value) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", DefaultDoubleCell.class.getSimpleName() + "[", "]")
                .add("value=" + value)
                .toString();
    }
}
