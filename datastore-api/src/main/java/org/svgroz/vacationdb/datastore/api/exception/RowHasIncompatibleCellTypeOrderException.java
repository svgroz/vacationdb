package org.svgroz.vacationdb.datastore.api.exception;

import org.svgroz.vacationdb.datastore.api.model.row.Row;
import org.svgroz.vacationdb.datastore.api.model.table.TableMetadata;

import java.util.StringJoiner;

/**
 * @author Simon Grozovsky svgroz@outlook.com
 */
public class RowHasIncompatibleCellTypeOrderException extends DataStoreException {
    private final transient Row row;
    private final transient TableMetadata metadata;

    public RowHasIncompatibleCellTypeOrderException(final Row row, final TableMetadata metadata) {
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
        return new StringJoiner(", ", RowHasIncompatibleCellTypeOrderException.class.getSimpleName() + "[", "]")
                .add("row=" + row)
                .add("metadata=" + metadata)
                .toString();
    }
}
