package org.svgroz.vacationdb.datastore.model.column;

import org.svgroz.vacationdb.datastore.model.DataType;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * @author Simon Grozovsky svgroz@outlook.com
 */
final class DefaultKeyColumn implements KeyColumn {
    private final String name;
    private final DataType type;

    DefaultKeyColumn(final String name, final DataType type) {
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
        if (!(o instanceof DefaultKeyColumn)) return false;
        final DefaultKeyColumn that = (DefaultKeyColumn) o;
        return Objects.equals(name, that.name) &&
                type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, type);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", DefaultKeyColumn.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("type=" + type)
                .toString();
    }
}
