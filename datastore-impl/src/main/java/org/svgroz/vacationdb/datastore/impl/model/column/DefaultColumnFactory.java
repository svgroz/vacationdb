package org.svgroz.vacationdb.datastore.impl.model.column;

import org.svgroz.vacationdb.datastore.api.model.DataType;
import org.svgroz.vacationdb.datastore.api.model.column.Column;
import org.svgroz.vacationdb.datastore.api.model.column.ColumnFactory;
import org.svgroz.vacationdb.datastore.api.model.column.KeyColumn;

/**
 * @author Simon Grozovsky svgroz@outlook.com
 */
public class DefaultColumnFactory implements ColumnFactory {
    @Override
    public Column of(final String name, final DataType type) {
        return new DefaultColumn(name, type);
    }

    @Override
    public KeyColumn keyOf(final String name, final DataType type) {
        return new DefaultKeyColumn(name, type);
    }
}
