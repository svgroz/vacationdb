package org.svgroz.vacationdb.datastore.model.table;

import org.svgroz.vacationdb.datastore.exception.ColumnsContainsNullException;
import org.svgroz.vacationdb.datastore.exception.ColumnsContainsSameNamesException;
import org.svgroz.vacationdb.datastore.exception.ColumnsDoesNotContainsKeysException;
import org.svgroz.vacationdb.datastore.exception.EmptyColumnsException;
import org.svgroz.vacationdb.datastore.model.column.Column;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.StringJoiner;

/**
 * @author Simon Grozovsky svgroz@outlook.com
 */
final class DefaultTableMetadata implements TableMetadata {
    private final String name;
    private final List<Column> columns;

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

        final Set<String> uniqueNames = new HashSet<>();

        int keysCount = 0;
        for (int i = 0; i < columns.size(); i++) {
            Column column = columns.get(i);
            if (column == null) {
                throw new ColumnsContainsNullException();
            }

            if (!uniqueNames.add(column.getName())) {
                throw new ColumnsContainsSameNamesException(columns);
            }
            if (column.isKey()) {
                keysCount = keysCount + 1;
            }
        }

        if (keysCount == 0) {
            throw new ColumnsDoesNotContainsKeysException(columns);
        }

        this.columns = List.copyOf(columns);
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
