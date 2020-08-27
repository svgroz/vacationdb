package org.svgroz.vacationdb.datastore.impl.model.table;

import org.eclipse.collections.api.list.ImmutableList;
import org.svgroz.vacationdb.datastore.api.model.column.Column;
import org.svgroz.vacationdb.datastore.api.model.table.MutableTable;
import org.svgroz.vacationdb.datastore.api.model.table.TableFactory;

/**
 * @author Simon Grozovsky svgroz@outlook.com
 */
public class DefaultTableFactory implements TableFactory {
    @Override
    public MutableTable mutable(final String name, final ImmutableList<Column> columns) {
        return new MutableSortedTable(name, columns);
    }
}
