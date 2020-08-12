package org.svgroz.vacationdb.datastore.model;

import java.util.Objects;

/**
 * That class is null safety, thread safety, and immutable.
 */
public class Column {
    private final String name;
    private final Class<? extends Cell> type;

    /**
     * @param name cannot be null
     * @param type cannot be null
     */
    public Column(final String name, final Class<? extends Cell> type) {
        this.name = Objects.requireNonNull(name, "name is null");
        this.type = Objects.requireNonNull(type, "type is null");
    }

    public String getName() {
        return name;
    }

    public Class<? extends Cell> getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Column)) return false;
        Column column = (Column) o;
        return Objects.equals(name, column.name) &&
                type == column.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, type);
    }

    @Override
    public String toString() {
        return "Column{" +
                "name='" + name + '\'' +
                ", type=" + type +
                '}';
    }
}
