package org.svgroz.vacationdb.datastore.model;

import org.svgroz.vacationdb.datastore.exception.CellsTypeMismatchException;

import java.util.Objects;

public class LongCell implements TypedCellType<Long> {
    private final Long value;

    public LongCell(final Long value) {
        this.value = Objects.requireNonNull(value);
    }

    @Override
    public Long getValue() {
        return value;
    }

    @Override
    public int compareTo(final CellType o) {
        if (EmptyCell.isEmpty(o)) {
            return 1;
        }

        if (o instanceof LongCell) {
            return value.compareTo(((LongCell) o).getValue());
        } else {
            throw new CellsTypeMismatchException(this, o);
        }
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
        return "LongCell{" +
                "value=" + value +
                '}';
    }
}
