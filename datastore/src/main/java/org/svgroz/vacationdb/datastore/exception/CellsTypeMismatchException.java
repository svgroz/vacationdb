package org.svgroz.vacationdb.datastore.exception;

import org.svgroz.vacationdb.datastore.model.CellType;

public class CellsTypeMismatchException extends DataStoreException {
    private transient final CellType first;
    private transient final CellType second;

    public CellsTypeMismatchException(final CellType first, final CellType second) {
        this.first = first;
        this.second = second;
    }

    public CellType getFirst() {
        return first;
    }

    public CellType getSecond() {
        return second;
    }

    @Override
    public String toString() {
        return "CellsTypeMismatchException{" +
                "first=" + first +
                ", second=" + second +
                "} " + super.toString();
    }
}
