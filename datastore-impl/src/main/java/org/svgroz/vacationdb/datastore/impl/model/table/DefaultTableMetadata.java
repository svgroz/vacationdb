package org.svgroz.vacationdb.datastore.impl.model.table;

import org.eclipse.collections.api.bimap.BiMap;
import org.eclipse.collections.api.factory.BiMaps;
import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.set.SetIterable;
import org.svgroz.vacationdb.datastore.api.exception.ColumnNotFoundByIndexException;
import org.svgroz.vacationdb.datastore.api.exception.ColumnsContainsNullException;
import org.svgroz.vacationdb.datastore.api.exception.ColumnsContainsSameNamesException;
import org.svgroz.vacationdb.datastore.api.exception.ColumnsDoesNotContainsKeysException;
import org.svgroz.vacationdb.datastore.api.exception.ColumnsSupposedToBeOnlyInBeginning;
import org.svgroz.vacationdb.datastore.api.exception.EmptyColumnsException;
import org.svgroz.vacationdb.datastore.api.model.column.Column;
import org.svgroz.vacationdb.datastore.api.model.table.TableMetadata;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * @author Simon Grozovsky svgroz@outlook.com
 */
final class DefaultTableMetadata implements TableMetadata {
    private final String name;
    private final BiMap<Column, Integer> columns;

    /**
     * @param columns table columns, cannot be null, should have one key column at least
     * @throws NullPointerException                if columns is null
     * @throws EmptyColumnsException               if columns is empty
     * @throws ColumnsContainsNullException        if columns contains null values
     * @throws ColumnsContainsSameNamesException   if columns contains same names
     * @throws ColumnsDoesNotContainsKeysException if columns does not contains key columns
     */
    DefaultTableMetadata(final String name, final ImmutableList<Column> columns) {
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

        this.columns = BiMaps.immutable.withAll(columnIndexes);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Integer indexOf(final Column column) {
        Objects.requireNonNull(column, "column is null");
        return columns.getOrDefault(column, -1);
    }

    @Override
    public int columnsCount() {
        return columns.size();
    }

    @Override
    public Column getColumnByIndex(final int index) {
        final SetIterable<Column> columns = this.columns.flip().get(index);
        if (columns == null) {
            throw new ColumnNotFoundByIndexException(index, this);
        }

        return columns.getOnly();
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
