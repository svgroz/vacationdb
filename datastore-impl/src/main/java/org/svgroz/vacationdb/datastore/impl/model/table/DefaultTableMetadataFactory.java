package org.svgroz.vacationdb.datastore.impl.model.table;

import org.eclipse.collections.api.list.ImmutableList;
import org.svgroz.vacationdb.datastore.api.model.column.Column;
import org.svgroz.vacationdb.datastore.api.model.table.TableMetadata;
import org.svgroz.vacationdb.datastore.api.model.table.TableMetadataFactory;

/**
 * @author Simon Grozovsky svgroz@outlook.com
 */
public class DefaultTableMetadataFactory implements TableMetadataFactory {
    @Override
    public TableMetadata of(final String tableName, final ImmutableList<Column> columns) {
        return new DefaultTableMetadata(tableName, columns);
    }
}
