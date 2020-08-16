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
final class LongCell implements TypedCell<Long> {

    private static final DataType SUPPORTED_TYPE = DataType.LONG;

    private final Long value;

    /**
     * @param value cannot be null
     * @throws NullPointerException if value is null
     */
    LongCell(final Long value) {
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
            return value.compareTo(((LongCell) target).getValue());
        }
        if (Cell.isEmpty(target)) {
            return 1;
        }
        throw new CellsTypeMismatchException(this, target);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof LongCell)) return false;
        final LongCell longCell = (LongCell) o;
        return Objects.equals(value, longCell.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", LongCell.class.getSimpleName() + "[", "]")
                .add("value=" + value)
                .toString();
    }
}
