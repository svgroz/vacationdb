package org.svgroz.vacationdb.datastore.model;

import java.util.Objects;

/**
 * That class represents available types of data
 */
public enum ColumnType {
    BOOLEAN,
    LONG,
    DOUBLE,
    STRING;

    /**
     * @param type  {@link ColumnType} cannot be null
     * @param value any value
     * @return if value is null method returns true
     * @throws NullPointerException if type is null
     */
    public static boolean valueHasValidType(ColumnType type, Object value) throws NullPointerException {
        Objects.requireNonNull(type, "type is null");

        if (value == null) {
            return true;
        }

        switch (type) {
            case BOOLEAN:
                return value instanceof Boolean;
            case LONG:
                return value instanceof Long;
            case DOUBLE:
                return value instanceof Double;
            case STRING:
                return value instanceof String;
            default:
                return false;
        }
    }

    /**
     * @param value any value
     * @return if value is null method returns true
     */
    public boolean valueHasValidType(Object value) {
        return valueHasValidType(this, value);
    }
}
