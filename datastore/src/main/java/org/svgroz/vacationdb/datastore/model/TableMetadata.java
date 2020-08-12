package org.svgroz.vacationdb.datastore.model;

import org.svgroz.vacationdb.datastore.exception.ColumnsContainsNullException;
import org.svgroz.vacationdb.datastore.exception.ColumnsContainsSameNamesException;
import org.svgroz.vacationdb.datastore.exception.EmptyColumnsException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class TableMetadata {
    private final List<Column> columns;
    private final Map<String, Integer> columnsIndex;

    public TableMetadata(final List<Column> columns) {
        Objects.requireNonNull(columns);
        if (columns.isEmpty()) {
            throw new EmptyColumnsException();
        }

        final Map<String, Integer> columnsIndex = new HashMap<>(columns.size());
        for (int i = 0; i < columns.size(); i++) {
            Column column = columns.get(i);
            if  (column == null) {
                throw new ColumnsContainsNullException();
            }
            if (columnsIndex.containsKey(column.getName())) {
                throw new ColumnsContainsSameNamesException(columns);
            }

            columnsIndex.put(column.getName(), i);
        }

        this.columns = List.copyOf(columns);
        this.columnsIndex = Map.copyOf(columnsIndex);
    }

    public List<Column> getColumns() {
        return columns;
    }

    public Map<String, Integer> getColumnsIndex() {
        return columnsIndex;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof TableMetadata)) return false;
        final TableMetadata that = (TableMetadata) o;
        return Objects.equals(columns, that.columns) &&
                Objects.equals(columnsIndex, that.columnsIndex);
    }

    @Override
    public int hashCode() {
        return Objects.hash(columns, columnsIndex);
    }

    @Override
    public String toString() {
        return "TableMetadata{" +
                "columns=" + columns +
                ", columnsIndex=" + columnsIndex +
                '}';
    }
}
