package org.svgroz.vacationdb.datastore.api.model.table;

import org.eclipse.collections.api.list.ImmutableList;
import org.svgroz.vacationdb.datastore.api.model.column.Column;

/**
 * @author Simon Grozovsky svgroz@outlook.com
 */
public interface TableFactory {
    MutableTable mutable(String name, ImmutableList<Column> columns);
}
