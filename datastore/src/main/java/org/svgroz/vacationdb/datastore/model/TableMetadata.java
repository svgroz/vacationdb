package org.svgroz.vacationdb.datastore.model;

import org.svgroz.vacationdb.datastore.exception.*;

import java.util.*;

public final class TableMetadata {
    private final List<Column> columns;
    private final KeyIndexesContainer keyIndexesContainer;

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

    public List<Column> getColumns() {
        return columns;
    }

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
