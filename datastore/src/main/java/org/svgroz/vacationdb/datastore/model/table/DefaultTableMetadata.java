package org.svgroz.vacationdb.datastore.model.table;

import org.svgroz.vacationdb.datastore.exception.ColumnsContainsNullException;
import org.svgroz.vacationdb.datastore.exception.ColumnsContainsSameNamesException;
import org.svgroz.vacationdb.datastore.exception.ColumnsDoesNotContainsKeysException;
import org.svgroz.vacationdb.datastore.exception.ColumnsSupposedToBeOnlyInBeginning;
import org.svgroz.vacationdb.datastore.exception.EmptyColumnsException;
import org.svgroz.vacationdb.datastore.model.column.Column;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * @author Simon Grozovsky svgroz@outlook.com
 */
final class DefaultTableMetadata implements TableMetadata {
    private final String name;
    private final List<Column> columns;
    private final Map<Column, Integer> columnIndexes;

    /**
     * @param columns table columns, cannot be null, should have one key column at least
     * @throws NullPointerException                if columns is null
     * @throws EmptyColumnsException               if columns is empty
     * @throws ColumnsContainsNullException        if columns contains null values
     * @throws ColumnsContainsSameNamesException   if columns contains same names
     * @throws ColumnsDoesNotContainsKeysException if columns does not contains key columns
     */
    DefaultTableMetadata(final String name, final List<Column> columns) {
        this.name = Objects.requireNonNull(name, "name is null");
        Objects.requireNonNull(columns, "columns is null");
        if (columns.isEmpty()) {
            throw new EmptyColumnsException();
        }

        final Map<Column, Integer> columnIndexes = new HashMap<>();

        int keysCount = 0;
        for (int i = 0; i < columns.size(); i++) {
            Column column = columns.get(i);
            if (column == null) {
                throw new ColumnsContainsNullException();
            }

            if (columnIndexes.put(column, i) != null) {
                throw new ColumnsContainsSameNamesException(columns);
            }

            if (column.isKey()) {
                if (keysCount - i != 0) {
                    throw new ColumnsSupposedToBeOnlyInBeginning(columns);
                }
                keysCount = keysCount + 1;
            }
        }

        if (keysCount == 0) {
            throw new ColumnsDoesNotContainsKeysException(columns);
        }

        this.columns = List.copyOf(columns);
        this.columnIndexes = Map.copyOf(columnIndexes);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<Column> getColumns() {
        return columns;
    }

    @Override
    public Integer indexOf(final Column column) {
        Objects.requireNonNull(column, "column is null");
        return columnIndexes.getOrDefault(column, -1);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof DefaultTableMetadata)) return false;
        final DefaultTableMetadata that = (DefaultTableMetadata) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(columns, that.columns);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, columns);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", DefaultTableMetadata.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("columns=" + columns)
                .toString();
    }
}
