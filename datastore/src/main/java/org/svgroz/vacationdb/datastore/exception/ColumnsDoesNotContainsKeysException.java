package org.svgroz.vacationdb.datastore.exception;

import org.svgroz.vacationdb.datastore.model.Column;

import java.util.List;
import java.util.StringJoiner;

/**
 * @author Simon Grozovsky svgroz@outlook.com
 */
public class ColumnsDoesNotContainsKeysException extends DataStoreException {
    private final transient List<Column> columns;

    public ColumnsDoesNotContainsKeysException(final List<Column> columns) {
        this.columns = columns;
    }

    public List<Column> getColumns() {
        return columns;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ColumnsDoesNotContainsKeysException.class.getSimpleName() + "[", "]")
                .add("columns=" + columns)
                .toString();
    }
}
