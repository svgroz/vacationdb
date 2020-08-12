package org.svgroz.vacationdb.datastore.exception;

import java.util.StringJoiner;

public class EmptyColumnsException extends DataStoreException {
    @Override
    public String toString() {
        return new StringJoiner(", ", EmptyColumnsException.class.getSimpleName() + "[", "]")
                .toString();
    }
}
