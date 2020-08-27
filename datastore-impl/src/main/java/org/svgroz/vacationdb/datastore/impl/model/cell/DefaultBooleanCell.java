package org.svgroz.vacationdb.datastore.impl.model.cell;


import org.svgroz.vacationdb.datastore.api.exception.CellsTypeMismatchException;
import org.svgroz.vacationdb.datastore.api.model.DataType;
import org.svgroz.vacationdb.datastore.api.model.cell.BooleanCell;
import org.svgroz.vacationdb.datastore.api.model.cell.Cell;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * That class is null safety, thread safety, and immutable.
 *
 * @author Simon Grozovsky svgroz@outlook.com
 */
final class DefaultBooleanCell implements BooleanCell {

    private static final DataType SUPPORTED_TYPE = DataType.BOOLEAN;

    static final DefaultBooleanCell INSTANCE_TRUE = new DefaultBooleanCell(true);

    static final DefaultBooleanCell INSTANCE_FALSE = new DefaultBooleanCell(false);

    private final boolean value;

    /**
     * @param value cannot be null
     * @throws NullPointerException if value is null
     */
    private DefaultBooleanCell(final boolean value) {
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

        if (DefaultEmptyCell.isEmpty(target)) {
            return 1;
        }
        throw new CellsTypeMismatchException(this, target);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof DefaultBooleanCell)) return false;
        final DefaultBooleanCell that = (DefaultBooleanCell) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", DefaultBooleanCell.class.getSimpleName() + "[", "]")
                .add("value=" + value)
                .toString();
    }
}
