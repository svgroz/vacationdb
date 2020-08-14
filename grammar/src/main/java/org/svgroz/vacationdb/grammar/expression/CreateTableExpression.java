package org.svgroz.vacationdb.grammar.expression;

import org.svgroz.vacationdb.datastore.model.table.ITableMetadata;

import java.util.Objects;
import java.util.StringJoiner;

public class CreateTableExpression implements GeneralExpression {
    private final ITableMetadata table;

    public CreateTableExpression(final ITableMetadata table) {
        this.table = Objects.requireNonNull(table, "table is null");
    }

    @Override
    public ExpressionType getType() {
        return ExpressionType.CREATE_TABLE;
    }

    public ITableMetadata getTableMetadata() {
        return table;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", CreateTableExpression.class.getSimpleName() + "[", "]")
                .add("table=" + table)
                .toString();
    }
}
