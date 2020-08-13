package org.svgroz.vacationdb.datastore.model;

import org.svgroz.vacationdb.datastore.exception.CellsTypeMismatchException;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * That class is null safety, thread safety, and immutable.
 *
 * @author Simon Grozovsky svgroz@outlook.com
 */
public class StringCell implements TypedCell<String> {
    private final String value;

    /**
     * @param value cannot be null
     * @throws NullPointerException if value is null
     */
    public StringCell(final String value) {
        this.value = Objects.requireNonNull(value);
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public Class<String> supportedType() {
        return String.class;
    }

    @Override
    public int compareTo(final Cell target) {
        Objects.requireNonNull(target, "target is null");
        if (EmptyCell.isEmpty(target)) {
            return 1;
        }

        if (target instanceof StringCell) {
            return value.compareTo(((StringCell) target).getValue());
        } else {
            throw new CellsTypeMismatchException(this, target);
        }
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof StringCell)) return false;
        final StringCell that = (StringCell) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", StringCell.class.getSimpleName() + "[", "]")
                .add("value='" + value + "'")
                .toString();
    }
}