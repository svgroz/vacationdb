package org.svgroz.vacationdb.datastore.exception;

import org.svgroz.vacationdb.datastore.model.table.TableMetadata;

import java.util.StringJoiner;

/**
 * @author Simon Grozovsky svgroz@outlook.com
 */
public class ColumnNotFoundByIndexException extends DataStoreException {
    private final transient int index;
    private final transient TableMetadata tableMetadata;

    public ColumnNotFoundByIndexException(final int index, final TableMetadata tableMetadata) {
        this.index = index;
        this.tableMetadata = tableMetadata;
    }

    public int getIndex() {
        return index;
    }

    public TableMetadata getTableMetadata() {
        return tableMetadata;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ColumnNotFoundByIndexException.class.getSimpleName() + "[", "]")
                .add("index=" + index)
                .add("tableMetadata=" + tableMetadata)
                .toString();
    }
}
