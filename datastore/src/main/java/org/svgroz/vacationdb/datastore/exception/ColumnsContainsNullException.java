package org.svgroz.vacationdb.datastore.exception;

import java.util.StringJoiner;

/**
 * @author Simon Grozovsky svgroz@outlook.com
 */
public class ColumnsContainsNullException extends DataStoreException {
    @Override
    public String toString() {
        return new StringJoiner(", ", ColumnsContainsNullException.class.getSimpleName() + "[", "]")
                .toString();
    }
}
