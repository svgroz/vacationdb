package org.svgroz.vacationdb.grammar.antlr;

import org.svgroz.vacationdb.datastore.Column;
import org.svgroz.vacationdb.datastore.ColumnType;
import org.svgroz.vacationdb.datastore.Table;
import org.svgroz.vacationdb.parser.VQLBaseListener;
import org.svgroz.vacationdb.parser.VQLParser;
import org.svgroz.vacationdb.grammar.exception.UnsupportedColumnType;
import org.svgroz.vacationdb.grammar.expression.CreateTableExpression;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CreateTableListener extends VQLBaseListener {
    private String tableName;
    private List<Column> columns = new ArrayList<>();

    @Override
    public void exitCreate_table_name(VQLParser.Create_table_nameContext ctx) {
        this.tableName = ctx.ID().getText();
    }

    @Override
    public void exitColumn(VQLParser.ColumnContext ctx) {
        final String columnName = Objects.requireNonNull(ctx.ID().getText(), "raw column name is null");
        final String rawColumnType = Objects.requireNonNull(ctx.COLUMN_TYPE().getText(), "raw column type is null");
        final ColumnType columnType;
        switch (rawColumnType) {
            case "INT":
                columnType = ColumnType.INTEGER;
                break;
            case "STRING":
                columnType = ColumnType.STRING;
                break;
            default:
                throw new UnsupportedColumnType(rawColumnType);
        }

        Column column = new Column(
                columnName,
                columnType
        );

        columns.add(column);
    }

    public CreateTableExpression getCreateTableExpression() {
        return new CreateTableExpression(
                new Table(
                        tableName,
                        columns
                )
        );
    }
}
