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
final class StringCell implements TypedCell<String> {

    private static final DataType SUPPORTED_TYPE = DataType.STRING;

    private final String value;

    /**
     * @param value cannot be null
     * @throws NullPointerException if value is null
     */
    StringCell(final String value) {
        this.value = Objects.requireNonNull(value);
    }

    @Override
    public String getValue() {
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
            return value.compareTo(((StringCell) target).getValue());
        }
        if (Cell.isEmpty(target)) {
            return 1;
        }
        throw new CellsTypeMismatchException(this, target);
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
