package org.svgroz.vacationdb.datastore.model;

import org.svgroz.vacationdb.datastore.exception.CellsTypeMismatchException;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * That class is null safety, thread safety, and immutable.
 *
 * @author Simon Grozovsky svgroz@outlook.com
 */
public class LongCell implements TypedCell<Long> {
    private final Long value;

    /**
     * @param value cannot be null
     * @throws NullPointerException if value is null
     */
    public LongCell(final Long value) {
        this.value = Objects.requireNonNull(value);
    }

    @Override
    public Long getValue() {
        return value;
    }

    @Override
    public Class<Long> supportedType() {
        return Long.class;
    }

    @Override
    public int compareTo(final Cell target) {
        Objects.requireNonNull(target, "target is null");
        if (EmptyCell.isEmpty(target)) {
            return 1;
        }

        if (target instanceof LongCell) {
            return value.compareTo(((LongCell) target).getValue());
        } else {
            throw new CellsTypeMismatchException(this, target);
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
        return new StringJoiner(", ", LongCell.class.getSimpleName() + "[", "]")
                .add("value=" + value)
                .toString();
    }
}
