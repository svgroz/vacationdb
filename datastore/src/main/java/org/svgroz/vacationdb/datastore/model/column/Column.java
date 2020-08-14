package org.svgroz.vacationdb.datastore.model.column;

import org.svgroz.vacationdb.datastore.model.DataType;

/**
 * @author Simon Grozovsky svgroz@outlook.com
 */
public interface Column {
    /**
     * @return name of columns
     */
    String getName();

    /**
     * @return data type of columns
     */
    DataType getType();

    /**
     * @return true if column is the key
     */
    boolean isKey();

    /**
     * @param name  is the column name
     * @param type  is the column type
     * @param isKey is the column isKey flag
     * @return instance of default column
     */
    static Column of(String name, DataType type, boolean isKey) {
        return new DefaultColumn(name, type, isKey);
    }
}
