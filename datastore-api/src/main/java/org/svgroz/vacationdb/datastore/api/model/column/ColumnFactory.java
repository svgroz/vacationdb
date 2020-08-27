package org.svgroz.vacationdb.datastore.api.model.column;

import org.svgroz.vacationdb.datastore.api.model.DataType;

/**
 * @author Simon Grozovsky svgroz@outlook.com
 */
public interface ColumnFactory {
    /**
     * @param name  is the column name
     * @param type  is the column type
     * @return instance of column
     */
    Column of(String name, DataType type);

    /**
     * @param name  is the column name
     * @param type  is the column type
     * @return instance of column
     */
    KeyColumn keyOf(String name, DataType type);
}
