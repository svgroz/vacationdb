package org.svgroz.vacationdb.datastore.exception;

import org.svgroz.vacationdb.datastore.model.ColumnType;

import java.util.Objects;

public class ColumnTypeValueTypeMismatch extends DataStoreException {
    private transient final ColumnType columnType;
    private transient final Object value;

    public ColumnTypeValueTypeMismatch(final ColumnType columnType, final Object value) {
        this.columnType = columnType;
        this.value = value;
    }

    public ColumnType getColumnType() {
        return columnType;
    }

    public Object getValue() {
        return value;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof ColumnTypeValueTypeMismatch)) return false;
        final ColumnTypeValueTypeMismatch that = (ColumnTypeValueTypeMismatch) o;
        return columnType == that.columnType &&
                Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(columnType, value);
    }

    @Override
    public String toString() {
        return "ColumnTypeValueTypeMismatch{" +
                "columnType=" + columnType +
                ", value=" + value +
                "} " + super.toString();
    }
}
