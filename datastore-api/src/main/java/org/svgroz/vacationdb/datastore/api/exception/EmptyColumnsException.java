package org.svgroz.vacationdb.datastore.api.exception;

import java.util.StringJoiner;

/**
 * @author Simon Grozovsky svgroz@outlook.com
 */
public class EmptyColumnsException extends DataStoreException {
    @Override
    public String toString() {
        return new StringJoiner(", ", EmptyColumnsException.class.getSimpleName() + "[", "]")
                .toString();
    }
}
