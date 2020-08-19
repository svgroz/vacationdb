package org.svgroz.vacationdb.datastore.exception;

import org.svgroz.vacationdb.datastore.model.column.Column;
import org.svgroz.vacationdb.datastore.statement.Condition;

import java.util.List;
import java.util.StringJoiner;

/**
 * @author Simon Grozovsky svgroz@outlook.com
 */
public class ColumnsInExpressionDoesNotMatchMetadataColumns extends DataStoreException {
    private final transient List<Condition> conditions;
    private final transient List<Column> metadataColumns;

    public ColumnsInExpressionDoesNotMatchMetadataColumns(final List<Condition> conditions, final List<Column> metadataColumns) {
        this.conditions = conditions;
        this.metadataColumns = metadataColumns;
    }

    public List<Condition> getConditions() {
        return conditions;
    }

    public List<Column> getMetadataColumns() {
        return metadataColumns;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ColumnsInExpressionDoesNotMatchMetadataColumns.class.getSimpleName() + "[", "]")
                .add("conditions=" + conditions)
                .add("metadataColumns=" + metadataColumns)
                .toString();
    }
}
