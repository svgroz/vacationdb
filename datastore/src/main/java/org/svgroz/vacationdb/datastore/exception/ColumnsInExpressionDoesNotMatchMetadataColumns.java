package org.svgroz.vacationdb.datastore.exception;

import org.eclipse.collections.api.list.ImmutableList;
import org.svgroz.vacationdb.datastore.model.table.TableMetadata;
import org.svgroz.vacationdb.datastore.statement.Condition;

import java.util.StringJoiner;

/**
 * @author Simon Grozovsky svgroz@outlook.com
 */
public class ColumnsInExpressionDoesNotMatchMetadataColumns extends DataStoreException {
    private final transient ImmutableList<Condition> conditions;
    private final transient TableMetadata tableMetadata;

    public ColumnsInExpressionDoesNotMatchMetadataColumns(
            final ImmutableList<Condition> conditions,
            final TableMetadata tableMetadata
            ) {
        this.conditions = conditions;
        this.tableMetadata = tableMetadata;
    }

    public ImmutableList<Condition> getConditions() {
        return conditions;
    }

    public TableMetadata getTableMetadata() {
        return tableMetadata;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ColumnsInExpressionDoesNotMatchMetadataColumns.class.getSimpleName() + "[", "]")
                .add("conditions=" + conditions)
                .add("tableMetadata=" + tableMetadata)
                .toString();
    }
}
