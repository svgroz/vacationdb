package org.svgroz.vacationdb.datastore.exception;

import org.svgroz.vacationdb.datastore.model.row.Row;
import org.svgroz.vacationdb.datastore.model.table.ITableMetadata;

import java.util.StringJoiner;

/**
 * @author Simon Grozovsky svgroz@outlook.com
 */
public class RowDoesNotMatchTableWidth extends DataStoreException {
    private final transient Row row;
    private final transient ITableMetadata metadata;

    public RowDoesNotMatchTableWidth(final Row row, final ITableMetadata metadata) {
        this.row = row;
        this.metadata = metadata;
    }

    public Row getRow() {
        return row;
    }

    public ITableMetadata getMetadata() {
        return metadata;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", RowDoesNotMatchTableWidth.class.getSimpleName() + "[", "]")
                .add("row=" + row)
                .add("metadata=" + metadata)
                .toString();
    }
}
