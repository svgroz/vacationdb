package org.svgroz.vacationdb.datastore.api.model.column;

import org.svgroz.vacationdb.datastore.api.model.DataType;

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
    default boolean isKey() {
        return false;
    }
}
