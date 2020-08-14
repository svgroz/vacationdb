package org.svgroz.vacationdb.datastore.model.column;

import org.svgroz.vacationdb.datastore.model.DataType;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * That class is null safety, thread safety, and immutable.
 *
 * @author Simon Grozovsky svgroz@outlook.com
 */
final class DefaultColumn implements Column {
    private final String name;
    private final DataType type;
    private final boolean isKey;

    /**
     * @param name cannot be null
     * @param type cannot be null
     */
    DefaultColumn(final String name, final DataType type, final boolean isKey) {
        this.name = Objects.requireNonNull(name, "name is null");
        this.type = Objects.requireNonNull(type, "type is null");
        this.isKey = isKey;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public DataType getType() {
        return type;
    }

    @Override
    public boolean isKey() {
        return isKey;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof DefaultColumn)) return false;
        final DefaultColumn column = (DefaultColumn) o;
        return isKey == column.isKey &&
                Objects.equals(name, column.name) &&
                type == column.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, type, isKey);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", DefaultColumn.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("type=" + type)
                .add("isKey=" + isKey)
                .toString();
    }
}
