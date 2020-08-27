package org.svgroz.vacationdb.datastore.api.exception;

import java.util.StringJoiner;

/**
 * @author Simon Grozovsky svgroz@outlook.com
 */
public class KeyIndexesIsEmptyException extends DataStoreException {
    @Override
    public String toString() {
        return new StringJoiner(", ", KeyIndexesIsEmptyException.class.getSimpleName() + "[", "]")
                .toString();
    }
}
