package org.svgroz.vacationdb.datastore.exception;

import org.svgroz.vacationdb.datastore.model.Row;

import java.util.StringJoiner;

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
