package org.svgroz.vacationdb.datastore.exception;

import org.svgroz.vacationdb.datastore.model.Cell;

import java.util.StringJoiner;

public class CellsTypeMismatchException extends DataStoreException {
    private transient final Cell first;
    private transient final Cell second;

    public CellsTypeMismatchException(final Cell first, final Cell second) {
        this.first = first;
        this.second = second;
    }

    public Cell getFirst() {
        return first;
    }

    public Cell getSecond() {
        return second;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", CellsTypeMismatchException.class.getSimpleName() + "[", "]")
                .add("first=" + first)
                .add("second=" + second)
                .toString();
    }
}
