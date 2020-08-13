package org.svgroz.vacationdb.datastore.exception;

import org.svgroz.vacationdb.datastore.model.DataType;
import org.svgroz.vacationdb.datastore.model.cell.Cell;

import java.util.StringJoiner;

/**
 * @author Simon Grozovsky svgroz@outlook.com
 */
public class DataTypeAlreadyRegisteredException extends DataStoreException {
    private final transient DataType dataType;
    private final transient Class<? extends Cell> target;

    public DataTypeAlreadyRegisteredException(final DataType dataType, final Class<? extends Cell> target) {
        this.dataType = dataType;
        this.target = target;
    }

    public DataType getDataType() {
        return dataType;
    }

    public Class<? extends Cell> getTarget() {
        return target;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", DataTypeAlreadyRegisteredException.class.getSimpleName() + "[", "]")
                .add("dataType=" + dataType)
                .add("target=" + target)
                .toString();
    }
}
