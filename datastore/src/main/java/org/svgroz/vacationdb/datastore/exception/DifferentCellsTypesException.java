package org.svgroz.vacationdb.datastore.exception;

import org.svgroz.vacationdb.datastore.model.Cell;

public class DifferentCellsTypesException extends DataStoreException {
    private transient final Cell first;
    private transient final Cell second;

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
