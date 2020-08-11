package org.svgroz.vacationdb.datastore.model;

import org.svgroz.vacationdb.datastore.exception.ColumnsContainsNullException;
import org.svgroz.vacationdb.datastore.exception.EmptyColumnsException;

import java.util.List;
import java.util.Objects;

/**
 * That class is null safety, thread safety, and immutable.
 */
public class Table {
    private final String name;
    private final List<Column> columns;

    /**
     * @param name    cannot be null
     * @param columns cannot be null, empty, or contains null values
     * @throws NullPointerException         if name or columns is null
     * @throws EmptyColumnsException        if columns is empty
     * @throws ColumnsContainsNullException if columns contains one or more null values
     */
    public Table(final String name, final List<Column> columns) throws NullPointerException, EmptyColumnsException, ColumnsContainsNullException {
        this.name = Objects.requireNonNull(name, "name is null");

        Objects.requireNonNull(columns, "columns is null");

        if (columns.isEmpty()) {
            throw new EmptyColumnsException();
        }

        for (final Column column : columns) {
            if (column == null) {
                throw new ColumnsContainsNullException();
            }
        }

        this.columns = List.copyOf(columns);
    }

    public String getName() {
        return name;
    }

    public List<Column> getColumns() {
        return columns;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Table)) return false;
        Table table = (Table) o;
        return Objects.equals(name, table.name) &&
                Objects.equals(columns, table.columns);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, columns);
    }

    @Override
    public String toString() {
        return "Table{" +
                "name='" + name + '\'' +
                ", columns=" + columns +
                '}';
    }
}
