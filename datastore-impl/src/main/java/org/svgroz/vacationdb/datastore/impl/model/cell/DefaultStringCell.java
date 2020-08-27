package org.svgroz.vacationdb.datastore.impl.model.cell;

import org.svgroz.vacationdb.datastore.api.exception.CellsTypeMismatchException;
import org.svgroz.vacationdb.datastore.api.model.DataType;
import org.svgroz.vacationdb.datastore.api.model.cell.Cell;
import org.svgroz.vacationdb.datastore.api.model.cell.EmptyCell;
import org.svgroz.vacationdb.datastore.api.model.cell.StringCell;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * That class is null safety, thread safety, and immutable.
 *
 * @author Simon Grozovsky svgroz@outlook.com
 */
public final class DefaultStringCell implements StringCell {

    private final String value;

    /**
     * @param value cannot be null
     * @throws NullPointerException if value is null
     */
    DefaultStringCell(final String value) {
        this.value = Objects.requireNonNull(value);
    }

    @Override
    public String getValue() {
        return value;
    }


    @Override
    public int compareTo(final Cell target) {
        Objects.requireNonNull(target, "target is null");

        if (target instanceof StringCell sc) {
            return value.compareTo(sc.getValue());
        } else if (target instanceof EmptyCell) {
            return 1;
        }

        throw new CellsTypeMismatchException(this, target);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof DefaultStringCell that)) return false;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", DefaultStringCell.class.getSimpleName() + "[", "]")
                .add("value='" + value + "'")
                .toString();
    }
}
