package org.svgroz.vacationdb.datastore.exception;

import java.util.StringJoiner;

public class EmptyCellsException extends DataStoreException {
    @Override
    public String toString() {
        return new StringJoiner(", ", EmptyCellsException.class.getSimpleName() + "[", "]")
                .toString();
    }
}
