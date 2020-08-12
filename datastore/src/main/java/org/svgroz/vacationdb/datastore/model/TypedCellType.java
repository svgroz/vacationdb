package org.svgroz.vacationdb.datastore.model;

public interface TypedCellType<T> extends CellType {
    T getValue();
}
