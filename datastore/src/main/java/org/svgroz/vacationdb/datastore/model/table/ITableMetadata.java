package org.svgroz.vacationdb.datastore.model.table;

import org.svgroz.vacationdb.datastore.model.column.Column;

import java.util.List;

/**
 * This interface contains behaviour of table metadata
 *
 * @author Simon Grozovsky svgroz@outlook.com
 */
public interface ITableMetadata {
    /**
     * @return not null value
     */
    String getName();

    /**
     * @return not null, not empty, and validated columns
     */
    List<Column> getColumns();

    static ITableMetadata of(final String tableName, final List<Column> columns) {
        return new TableMetadata(tableName, columns);
    }
}
