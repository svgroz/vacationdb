package org.svgroz.vacationdb.datastore.model;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * That class is null safety, thread safety, and immutable.
 */
public class Column {
    private final String name;
    private final Class<? extends Cell> type;
    private final boolean isKey;

    /**
     * @param name cannot be null
     * @param type cannot be null
     */
    public Column(final String name, final Class<? extends Cell> type, final boolean isKey) {
        this.name = Objects.requireNonNull(name, "name is null");
        this.type = Objects.requireNonNull(type, "type is null");
        this.isKey = isKey;
    }

    public String getName() {
        return name;
    }

    public Class<? extends Cell> getType() {
        return type;
    }

    public boolean isKey() {
        return isKey;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof Column)) return false;
        final Column column = (Column) o;
        return isKey == column.isKey &&
                Objects.equals(name, column.name) &&
                Objects.equals(type, column.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, type, isKey);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Column.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("type=" + type)
                .add("isKey=" + isKey)
                .toString();
    }
}
