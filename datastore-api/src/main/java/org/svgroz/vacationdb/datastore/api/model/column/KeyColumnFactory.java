package org.svgroz.vacationdb.datastore.api.model.column;

/**
 * @author Simon Grozovsky svgroz@outlook.com
 */
public interface KeyColumnFactory {
    BooleanKeyColumn booleanOf(String name);

    LongKeyColumn longOf(String name);

    DoubleKeyColumn doubleOf(String name);

    StringKeyColumn stringOf(String name);
}
