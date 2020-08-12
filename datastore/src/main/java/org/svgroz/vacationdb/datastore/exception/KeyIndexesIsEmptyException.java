package org.svgroz.vacationdb.datastore.exception;

import java.util.StringJoiner;

public class KeyIndexesIsEmptyException extends DataStoreException {
    @Override
    public String toString() {
        return new StringJoiner(", ", KeyIndexesIsEmptyException.class.getSimpleName() + "[", "]")
                .toString();
    }
}
