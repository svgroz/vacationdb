package org.svgroz.vacationdb.grammar.expression;

import org.svgroz.vacationdb.datastore.Table;

import java.util.Objects;

public class CreateTableExpression implements GeneralExpression {
    private final Table table;

    public CreateTableExpression(Table table) {
        this.table = Objects.requireNonNull(table, "table is null");
    }

    @Override
    public ExpressionType getType() {
        return ExpressionType.CREATE_TABLE;
    }

    public Table getTable() {
        return table;
    }

    @Override
    public String toString() {
        return "CreateTableExpression{" +
                "table=" + table +
                '}';
    }
}
