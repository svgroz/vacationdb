package org.svgroz.vacationdb.datastore.model.column;

import org.svgroz.vacationdb.datastore.model.DataType;

/**
 * @author Simon Grozovsky svgroz@outlook.com
 */
public interface KeyColumn extends Column {

    /**
     * @param name  is the column name
     * @param type  is the column type
     * @return instance of default key column
     */
    static KeyColumn of(final String name, final DataType type) {
        return new DefaultKeyColumn(name, type);
    }

    @Override
    default boolean isKey() {
        return true;
    }
}
