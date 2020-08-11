package org.svgroz.vacationdb.datastore.exception;

import org.svgroz.vacationdb.datastore.model.ColumnType;

public class UnsupportedColumnType extends DataStoreException {
    private transient final ColumnType columnType;

    public UnsupportedColumnType(ColumnType columnType) {
        this.columnType = columnType;
    }

    public ColumnType getColumnType() {
        return columnType;
    }

    @Override
    public String toString() {
        return "UnsupportedColumnType{" +
                "columnType=" + columnType +
                '}';
    }
}
