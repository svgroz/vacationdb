package org.svgroz.vacationdb.datastore.exception;

import org.svgroz.vacationdb.datastore.model.Cell;
import org.svgroz.vacationdb.datastore.model.Column;

public class DifferentCellsTypesException extends DataStoreException {
    private final Cell<?> first;
    private final Cell<?> second;

    public DifferentCellsTypesException(final Cell first, final Cell second) {
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
        return "DifferentColumnTypesException{" +
                "first=" + first +
                ", second=" + second +
                "} " + super.toString();
    }
}
