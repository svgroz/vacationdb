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

    /**
     * @param name cannot be null
     * @param type cannot be null
     */
    DefaultColumn(final String name, final DataType type) {
        this.name = Objects.requireNonNull(name, "name is null");
        this.type = Objects.requireNonNull(type, "type is null");
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
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof DefaultColumn)) return false;

        final DefaultColumn that = (DefaultColumn) o;

        return type == that.type && name.equals(that.name);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + type.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", DefaultColumn.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("type=" + type)
                .toString();
    }
}
