package org.svgroz.vacationdb.datastore.exception;

import java.util.StringJoiner;

/**
 * @author Simon Grozovsky svgroz@outlook.com
 */
public abstract class DataStoreException extends RuntimeException {
    @Override
    public String toString() {
        return new StringJoiner(", ", DataStoreException.class.getSimpleName() + "[", "]")
                .toString();
    }
}
