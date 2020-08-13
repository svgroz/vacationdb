package org.svgroz.vacationdb.datastore.exception;

import org.svgroz.vacationdb.datastore.model.row.Row;
import org.svgroz.vacationdb.datastore.model.table.TableMetadata;

import java.util.StringJoiner;

/**
 * @author Simon Grozovsky svgroz@outlook.com
 */
public class RowDoesNotMatchTableWidth extends DataStoreException {
    private final transient Row row;
    private final transient TableMetadata metadata;

    public RowDoesNotMatchTableWidth(final Row row, final TableMetadata metadata) {
        this.row = row;
        this.metadata = metadata;
    }

    public Row getRow() {
        return row;
    }

    public TableMetadata getMetadata() {
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
