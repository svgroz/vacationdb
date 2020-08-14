package org.svgroz.vacationdb.grammar.expression;

import org.svgroz.vacationdb.datastore.model.table.TableMetadata;

import java.util.Objects;
import java.util.StringJoiner;

public class CreateTableExpression implements GeneralExpression {
    private final TableMetadata table;

    public CreateTableExpression(final TableMetadata table) {
        this.table = Objects.requireNonNull(table, "table is null");
    }

    @Override
    public ExpressionType getType() {
        return ExpressionType.CREATE_TABLE;
    }

    public TableMetadata getTableMetadata() {
        return table;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", CreateTableExpression.class.getSimpleName() + "[", "]")
                .add("table=" + table)
                .toString();
    }
}
