package org.svgroz.vacationdb.datastore.impl.model.cell;

import org.svgroz.vacationdb.datastore.api.exception.CellsTypeMismatchException;
import org.svgroz.vacationdb.datastore.api.model.DataType;
import org.svgroz.vacationdb.datastore.api.model.cell.Cell;
import org.svgroz.vacationdb.datastore.api.model.cell.DoubleCell;
import org.svgroz.vacationdb.datastore.api.model.cell.EmptyCell;

import java.security.spec.ECField;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * That class is null safety, thread safety, and immutable.
 *
 * @author Simon Grozovsky svgroz@outlook.com
 */
public final class DefaultDoubleCell implements DoubleCell {

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
    public int compareTo(final Cell target) {
        Objects.requireNonNull(target, "target is null");

        if (target instanceof DoubleCell) {
            return Double.compare(value, ((DoubleCell) target).getValue());
        } else if (target instanceof EmptyCell) {
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
