package org.svgroz.vacationdb.datastore.exception;

import org.eclipse.collections.api.list.ImmutableList;
import org.svgroz.vacationdb.datastore.model.column.Column;

import java.util.StringJoiner;

/**
 * @author Simon Grozovsky svgroz@outlook.com
 */
public class ColumnsSupposedToBeOnlyInBeginning extends DataStoreException {
    private final transient ImmutableList<Column> columns;

    public ColumnsSupposedToBeOnlyInBeginning(final ImmutableList<Column> columns) {
        this.columns = columns;
    }

    public ImmutableList<Column> getColumns() {
        return columns;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ColumnsSupposedToBeOnlyInBeginning.class.getSimpleName() + "[", "]")
                .add("columns=" + columns)
                .toString();
    }
}
