package org.svgroz.vacationdb.datastore.model;

import java.util.Objects;

/**
 * That class represents available types of data
 */
public enum ColumnType {
    BOOLEAN(Boolean.class),
    LONG(Long.class),
    DOUBLE(Double.class),
    STRING(String.class);

    private final Class<?> clazz;

    ColumnType(Class<?> clazz) {
        this.clazz = clazz;
    }

    /**
     * @param type  {@link ColumnType} cannot be null
     * @param value any value
     * @return if value is null method returns true
     * @throws NullPointerException if type is null
     */
    public static boolean valueHasValidType(ColumnType type, Object value) throws NullPointerException {
        Objects.requireNonNull(type, "type is null");
        return type.clazz.isInstance(value);
    }

    /**
     * @param value any value
     * @return if value is null method returns true
     */
    public boolean valueHasValidType(Object value) {
        return valueHasValidType(this, value);
    }
}
