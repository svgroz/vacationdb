package org.svgroz.vacationdb.datastore.api.model.table;

import org.svgroz.vacationdb.datastore.api.exception.ColumnNotFoundByIndexException;
import org.svgroz.vacationdb.datastore.api.model.column.Column;

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
     * @return count of columns in table
     */
    int columnsCount();

    /**
     * @param index of column
     * @return column if found or empty
     * @throws ColumnNotFoundByIndexException if column has not found
     */
    Column getColumnByIndex(int index);

    /**
     * @param column for index, supposed to be not null
     * @return index of column or -1 if not found
     * @throws NullPointerException if column is null
     */
    Integer indexOf(Column column);
}
