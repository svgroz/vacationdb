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
final class BooleanCell implements TypedCell<Boolean> {

    private static final DataType SUPPORTED_TYPE = DataType.BOOLEAN;

    static final BooleanCell INSTANCE_TRUE = new BooleanCell(true);

    static final BooleanCell INSTANCE_FALSE = new BooleanCell(false);

    private final boolean value;

    /**
     * @param value cannot be null
     * @throws NullPointerException if value is null
     */
    private BooleanCell(final boolean value) {
        this.value = value;
    }

    @Override
    public Boolean getValue() {
        return this == INSTANCE_TRUE;
    }

    @Override
    public DataType supportedType() {
        return SUPPORTED_TYPE;
    }

    @Override
    public int compareTo(final Cell target) {
        Objects.requireNonNull(target, "target is null");

        if (target == INSTANCE_TRUE || target == INSTANCE_FALSE) {
            return this == target ? 0 : (this == INSTANCE_TRUE ? 1 : -1);
        }

        if (Cell.isEmpty(target)) {
            return 1;
        }
        throw new CellsTypeMismatchException(this, target);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof BooleanCell)) return false;
        final BooleanCell that = (BooleanCell) o;
        return value == that.value;
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
