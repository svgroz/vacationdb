package org.svgroz.vacationdb.datastore.exception;

import org.svgroz.vacationdb.datastore.model.DataType;

import java.util.StringJoiner;

/**
 * @author Simon Grozovsky svgroz@outlook.com
 */
public class UnsupportedColumnTypeException extends DataStoreException {
    private transient final DataType dataType;

    public UnsupportedColumnTypeException(final DataType dataType) {
        this.dataType = dataType;
    }

    public DataType getDataType() {
        return dataType;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", UnsupportedColumnTypeException.class.getSimpleName() + "[", "]")
                .add("dataType=" + dataType)
                .toString();
    }
}
