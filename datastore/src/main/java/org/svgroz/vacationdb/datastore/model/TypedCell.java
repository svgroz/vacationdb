package org.svgroz.vacationdb.datastore.model;

/**
 * That class represents cells with typed content
 * All inherited classes should have null safety guaranties
 *
 * @param <T> type of content
 */
public interface TypedCell<T> extends Cell {
    /**
     * The method that returns not null value from cell
     *
     * @return not null value
     */
    T getValue();

    /**
     * @return supported type
     */
    Class<T> supportedType();
}
