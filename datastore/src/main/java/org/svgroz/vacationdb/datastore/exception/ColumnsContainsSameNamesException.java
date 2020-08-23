package org.svgroz.vacationdb.datastore.exception;

import org.eclipse.collections.api.list.ImmutableList;
import org.svgroz.vacationdb.datastore.model.column.Column;

import java.util.StringJoiner;

/**
 * @author Simon Grozovsky svgroz@outlook.com
 */
public class ColumnsContainsSameNamesException extends DataStoreException {
    private final transient ImmutableList<Column> columns;

    public ColumnsContainsSameNamesException(final ImmutableList<Column> columns) {
        this.columns = columns;
    }

    public ImmutableList<Column> getColumns() {
        return columns;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ColumnsContainsSameNamesException.class.getSimpleName() + "[", "]")
                .add("columns=" + columns)
                .toString();
    }
}
