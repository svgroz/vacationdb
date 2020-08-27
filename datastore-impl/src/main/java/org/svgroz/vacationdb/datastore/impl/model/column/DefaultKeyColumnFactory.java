package org.svgroz.vacationdb.datastore.impl.model.column;

import org.svgroz.vacationdb.datastore.api.model.column.BooleanKeyColumn;
import org.svgroz.vacationdb.datastore.api.model.column.DoubleKeyColumn;
import org.svgroz.vacationdb.datastore.api.model.column.KeyColumnFactory;
import org.svgroz.vacationdb.datastore.api.model.column.LongKeyColumn;
import org.svgroz.vacationdb.datastore.api.model.column.StringKeyColumn;

import java.util.Objects;

/**
 * @author Simon Grozovsky svgroz@outlook.com
 */
public class DefaultKeyColumnFactory implements KeyColumnFactory {
    @Override
    public BooleanKeyColumn booleanOf(final String name) {
        return new DefaultBooleanKeyColumn(Objects.requireNonNull(name, "name is null"));
    }

    @Override
    public LongKeyColumn longOf(final String name) {
        return new DefaultLongKeyColumn(Objects.requireNonNull(name, "name is null"));
    }

    @Override
    public DoubleKeyColumn doubleOf(final String name) {
        return new DefaultDoubleKeyColumn(Objects.requireNonNull(name, "name is null"));
    }

    @Override
    public StringKeyColumn stringOf(final String name) {
        return new DefaultStringKeyColumn(Objects.requireNonNull(name, "name is null"));
    }
}
