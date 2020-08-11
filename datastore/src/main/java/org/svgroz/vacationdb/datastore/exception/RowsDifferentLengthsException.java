package org.svgroz.vacationdb.datastore.exception;

import org.svgroz.vacationdb.datastore.model.Row;

public class RowsDifferentLengthsException extends DataStoreException {
    private final Row first;
    private final Row second;

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
        return "RowsDifferentLengths{" +
                "first=" + first +
                ", second=" + second +
                "} " + super.toString();
    }
}
