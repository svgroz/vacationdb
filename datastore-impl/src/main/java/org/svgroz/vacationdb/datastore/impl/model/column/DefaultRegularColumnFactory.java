package org.svgroz.vacationdb.datastore.impl.model.column;

import org.svgroz.vacationdb.datastore.api.model.column.BooleanRegularColumn;
import org.svgroz.vacationdb.datastore.api.model.column.DoubleRegularColumn;
import org.svgroz.vacationdb.datastore.api.model.column.LongRegularColumn;
import org.svgroz.vacationdb.datastore.api.model.column.RegularColumnFactory;
import org.svgroz.vacationdb.datastore.api.model.column.StringKeyColumn;

import java.util.Objects;

/**
 * @author Simon Grozovsky svgroz@outlook.com
 */
public class DefaultRegularColumnFactory implements RegularColumnFactory {

    @Override
    public BooleanRegularColumn booleanOf(final String name) {
        return new DefaultBooleanRegularColumn(Objects.requireNonNull(name, "name is null"));
    }

    @Override
    public LongRegularColumn longOf(final String name) {
        return new DefaultLongRegularColumn(Objects.requireNonNull(name, "name is null"));
    }

    @Override
    public DoubleRegularColumn doubleOf(final String name) {
        return new DefaultDoubleRegularColumn(Objects.requireNonNull(name, "name is null"));
    }

    @Override
    public StringKeyColumn stringOf(final String name) {
        return new DefaultStringKeyColumn(Objects.requireNonNull(name, "name is null"));
    }
}
