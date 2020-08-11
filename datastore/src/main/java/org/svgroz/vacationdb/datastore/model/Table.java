package org.svgroz.vacationdb.datastore.model;

import org.svgroz.vacationdb.datastore.exception.ColumnsContainsNullException;
import org.svgroz.vacationdb.datastore.exception.EmptyColumnsException;

import java.util.List;
import java.util.Objects;

public class Table {
    private final String name;
    private final List<Column> columns;

    public Table(final String name, final List<Column> columns) {
        this.name = Objects.requireNonNull(name, "name is null");

        Objects.requireNonNull(columns, "columns is null");

        if (columns.isEmpty()) {
            throw new EmptyColumnsException();
        }

        if (columns.stream().anyMatch(Objects::isNull)) {
            throw new ColumnsContainsNullException();
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
