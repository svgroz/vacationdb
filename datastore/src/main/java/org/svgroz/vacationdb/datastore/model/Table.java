package org.svgroz.vacationdb.datastore.model;

import org.svgroz.vacationdb.datastore.exception.ColumnsContainsNullException;
import org.svgroz.vacationdb.datastore.exception.EmptyColumnsException;

import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * That class is null safety, thread safety, and immutable.
 */
public class Table {
    private final String name;
    private final TableMetadata metadata;

    /**
     * @param name    cannot be null
     * @param columns cannot be null, empty, or contains null values
     * @throws NullPointerException         if name or columns is null
     * @throws EmptyColumnsException        if columns is empty
     * @throws ColumnsContainsNullException if columns contains one or more null values
     */
    public Table(final String name, final List<Column> columns) {
        this.name = Objects.requireNonNull(name, "name is null");
        this.metadata = new TableMetadata(Objects.requireNonNull(columns, "columns is null"));
    }

    public String getName() {
        return name;
    }

    public TableMetadata getMetadata() {
        return metadata;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof Table)) return false;
        final Table table = (Table) o;
        return Objects.equals(name, table.name) &&
                Objects.equals(metadata, table.metadata);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, metadata);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Table.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("metadata=" + metadata)
                .toString();
    }
}
