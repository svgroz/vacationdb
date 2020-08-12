package org.svgroz.vacationdb.datastore.exception;

import java.util.StringJoiner;

public class ColumnsContainsNullException extends DataStoreException {
    @Override
    public String toString() {
        return new StringJoiner(", ", ColumnsContainsNullException.class.getSimpleName() + "[", "]")
                .toString();
    }
}
