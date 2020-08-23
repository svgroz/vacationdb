package org.svgroz.vacationdb.datastore.model.table;

import org.eclipse.collections.api.list.ImmutableList;
import org.svgroz.vacationdb.datastore.model.column.Column;

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
     * @throws org.svgroz.vacationdb.datastore.exception.ColumnNotFoundByIndexException if column has not found
     */
    Column getColumnByIndex(int index);

    /**
     * @param column for index, supposed to be not null
     * @return index of column or -1 if not found
     * @throws NullPointerException if column is null
     */
    Integer indexOf(Column column);

    static TableMetadata of(final String tableName, final ImmutableList<Column> columns) {
        return new DefaultTableMetadata(tableName, columns);
    }
}
