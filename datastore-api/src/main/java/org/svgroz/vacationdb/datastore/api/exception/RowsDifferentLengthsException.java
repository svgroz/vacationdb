package org.svgroz.vacationdb.datastore.api.exception;

import org.svgroz.vacationdb.datastore.api.model.row.Row;

import java.util.StringJoiner;

/**
 * @author Simon Grozovsky svgroz@outlook.com
 */
public class RowsDifferentLengthsException extends DataStoreException {
    private transient final Row first;
    private transient final Row second;

    public RowsDifferentLengthsException(final Row first, final Row second) {
        this.first = first;
        this.second = second;
    }

    public Row getFirst() {
        return first;
    }

    public Row getSecond() {
        return second;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", RowsDifferentLengthsException.class.getSimpleName() + "[", "]")
                .add("first=" + first)
                .add("second=" + second)
                .toString();
    }
}
