package org.svgroz.vacationdb.datastore.model;

public enum ColumnType {
    BOOLEAN,
    LONG,
    DOUBLE,
    STRING;

    public static boolean valueHasValidType(ColumnType type, Object value) {
        if (type == value) {
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

    public boolean valueHasValidType(Object value) {
        return valueHasValidType(this, value);
    }
}
