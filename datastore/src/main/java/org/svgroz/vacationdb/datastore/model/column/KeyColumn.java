package org.svgroz.vacationdb.datastore.model.column;

/**
 * @author Simon Grozovsky svgroz@outlook.com
 */
public interface KeyColumn extends Column {
    @Override
    default boolean isKey() {
        return true;
    }
}
