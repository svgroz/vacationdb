package org.svgroz.vacationdb.datastore.impl.model.cell;

import org.svgroz.vacationdb.datastore.api.exception.CellsTypeMismatchException;
import org.svgroz.vacationdb.datastore.api.model.DataType;
import org.svgroz.vacationdb.datastore.api.model.cell.Cell;
import org.svgroz.vacationdb.datastore.api.model.cell.LongCell;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * That class is null safety, thread safety, and immutable.
 *
 * @author Simon Grozovsky svgroz@outlook.com
 */
final class DefaultLongCell implements LongCell {

    private static final DataType SUPPORTED_TYPE = DataType.LONG;

    private final long value;

    /**
     * @param value cannot be null
     * @throws NullPointerException if value is null
     */
    DefaultLongCell(final Long value) {
        this.value = Objects.requireNonNull(value);
    }

    @Override
    public Long getValue() {
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
            final DefaultLongCell that = (DefaultLongCell) target;
            return Long.compare(value, that.value);
        }

        if (DefaultEmptyCell.isEmpty(target)) {
            return 1;
        }

        throw new CellsTypeMismatchException(this, target);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof DefaultLongCell)) return false;
        final DefaultLongCell defaultLongCell = (DefaultLongCell) o;
        return value == defaultLongCell.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", DefaultLongCell.class.getSimpleName() + "[", "]")
                .add("value=" + value)
                .toString();
    }
}
