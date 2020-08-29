package org.svgroz.vacationdb.datastore.impl.model.cell;


import org.svgroz.vacationdb.datastore.api.exception.CellsTypeMismatchException;
import org.svgroz.vacationdb.datastore.api.model.cell.BooleanCell;
import org.svgroz.vacationdb.datastore.api.model.cell.Cell;
import org.svgroz.vacationdb.datastore.api.model.cell.EmptyCell;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * That class is null safety, thread safety, and immutable.
 *
 * @author Simon Grozovsky svgroz@outlook.com
 */
public final class DefaultBooleanCell implements BooleanCell {

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
    public boolean getValue() {
        return this == INSTANCE_TRUE;
    }

    @Override
    public int compareTo(final Cell target) {
        Objects.requireNonNull(target, "target is null");

        if (target instanceof BooleanCell) {
            return Boolean.compare(value, ((BooleanCell)target).getValue());
        } else if (target instanceof EmptyCell) {
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
