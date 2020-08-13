package org.svgroz.vacationdb.datastore.model;

import org.svgroz.vacationdb.datastore.exception.*;

import java.util.*;

/**
 * This class represent metadata information about table - columns, key indexes.
 * Immutable and thread safe.
 *
 * @author Simon Grozovsky svgroz@outlook.com
 */
public final class TableMetadata {
    private final List<Column> columns;
    private final KeyIndexesContainer keyIndexesContainer;

    /**
     * @param columns table columns, cannot be null, should have one key column at least
     * @throws NullPointerException                if columns is null
     * @throws EmptyColumnsException               if columns is empty
     * @throws ColumnsContainsNullException        if columns contains null values
     * @throws ColumnsContainsSameNamesException   if columns contains same names
     * @throws ColumnsDoesNotContainsKeysException if columns does not contains key columns
     */
    public TableMetadata(final List<Column> columns) {
        Objects.requireNonNull(columns, "columns is null");
        if (columns.isEmpty()) {
            throw new EmptyColumnsException();
        }

        final List<Integer> keyIndexes = new ArrayList<>();
        final Set<String> uniqueNames = new HashSet<>();

        for (int i = 0; i < columns.size(); i++) {
            Column column = columns.get(i);
            if (column == null) {
                throw new ColumnsContainsNullException();
            }

            if (!uniqueNames.add(column.getName())) {
                throw new ColumnsContainsSameNamesException(columns);
            }

            if (column.isKey()) {
                keyIndexes.add(i);
            }
        }

        if (keyIndexes.isEmpty()) {
            throw new ColumnsDoesNotContainsKeysException(columns);
        }

        this.columns = List.copyOf(columns);
        this.keyIndexesContainer = new KeyIndexesContainer(List.copyOf(keyIndexes));
    }

    /**
     * @return immutable list of columns {@link Column}
     */
    public List<Column> getColumns() {
        return columns;
    }

    /**
     * @return key indexes container {@link KeyIndexesContainer}
     */
    public KeyIndexesContainer getKeyIndexesContainer() {
        return keyIndexesContainer;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof TableMetadata)) return false;
        final TableMetadata that = (TableMetadata) o;
        return Objects.equals(columns, that.columns) &&
                Objects.equals(keyIndexesContainer, that.keyIndexesContainer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(columns, keyIndexesContainer);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", TableMetadata.class.getSimpleName() + "[", "]")
                .add("columns=" + columns)
                .add("keyIndexesContainer=" + keyIndexesContainer)
                .toString();
    }
}
