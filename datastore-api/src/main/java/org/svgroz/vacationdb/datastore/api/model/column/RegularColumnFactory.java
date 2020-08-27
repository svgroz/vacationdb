package org.svgroz.vacationdb.datastore.api.model.column;

/**
 * @author Simon Grozovsky svgroz@outlook.com
 */
public interface RegularColumnFactory {
    BooleanRegularColumn booleanOf(String name);

    LongRegularColumn longOf(String name);

    DoubleRegularColumn doubleOf(String name);

    StringKeyColumn stringOf(String name);
}
