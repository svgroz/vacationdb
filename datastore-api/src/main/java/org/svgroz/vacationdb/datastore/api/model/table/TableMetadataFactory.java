package org.svgroz.vacationdb.datastore.api.model.table;

import org.eclipse.collections.api.list.ImmutableList;
import org.svgroz.vacationdb.datastore.api.model.column.Column;

/**
 * @author Simon Grozovsky svgroz@outlook.com
 */
public interface TableMetadataFactory {
    TableMetadata of(final String tableName, final ImmutableList<Column> columns);
}
