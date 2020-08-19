package org.svgroz.vacationdb.datastore.exception;

import org.svgroz.vacationdb.datastore.model.column.Column;

import java.util.List;
import java.util.StringJoiner;

/**
 * @author Simon Grozovsky svgroz@outlook.com
 */
public class ColumnsSupposedToBeOnlyInBeginning extends DataStoreException {
    private final transient List<Column> columns;

    public ColumnsSupposedToBeOnlyInBeginning(final List<Column> columns) {
        this.columns = columns;
    }

    public List<Column> getColumns() {
        return columns;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ColumnsSupposedToBeOnlyInBeginning.class.getSimpleName() + "[", "]")
                .add("columns=" + columns)
                .toString();
    }
}