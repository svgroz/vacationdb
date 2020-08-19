package org.svgroz.vacationdb.datastore.model.table;

import org.svgroz.vacationdb.datastore.model.column.Column;

import java.util.List;

/**
 * This interface contains behaviour of table metadata
 *
 * @author Simon Grozovsky svgroz@outlook.com
 */
public interface TableMetadata {
    /**
     * @return not null value
     */
    String getName();

    /**
     * @return not null, not empty, and validated columns
     */
    List<Column> getColumns();

    /**
     * @param column for index, supposed to be not null
     * @return index of column or -1 if not found
     * @throws NullPointerException if column is null
     */
    Integer indexOf(Column column);

    static TableMetadata of(final String tableName, final List<Column> columns) {
        return new DefaultTableMetadata(tableName, columns);
    }
}
