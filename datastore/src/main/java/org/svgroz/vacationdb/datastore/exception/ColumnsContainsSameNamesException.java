package org.svgroz.vacationdb.datastore.exception;

import org.svgroz.vacationdb.datastore.model.Column;

import java.util.List;
import java.util.StringJoiner;

/**
 * @author Simon Grozovsky svgroz@outlook.com
 */
public class ColumnsContainsSameNamesException extends DataStoreException {
    private final transient List<Column> columns;

    public ColumnsContainsSameNamesException(final List<Column> columns) {
        this.columns = columns;
    }

    public List<Column> getColumns() {
        return columns;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ColumnsContainsSameNamesException.class.getSimpleName() + "[", "]")
                .add("columns=" + columns)
                .toString();
    }
}
