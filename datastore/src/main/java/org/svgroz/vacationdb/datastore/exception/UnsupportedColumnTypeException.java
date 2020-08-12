package org.svgroz.vacationdb.datastore.exception;

import org.svgroz.vacationdb.datastore.model.Cell;

import java.util.StringJoiner;

public class UnsupportedColumnTypeException extends DataStoreException {
    private transient final Class<? extends Cell> columnType;

    public UnsupportedColumnTypeException(Class<? extends Cell> columnType) {
        this.columnType = columnType;
    }

    public Class<? extends Cell> getColumnType() {
        return columnType;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", UnsupportedColumnTypeException.class.getSimpleName() + "[", "]")
                .add("columnType=" + columnType)
                .toString();
    }
}
