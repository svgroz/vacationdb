package org.svgroz.vacationdb.grammar.antlr;

import org.svgroz.vacationdb.datastore.model.*;
import org.svgroz.vacationdb.grammar.exception.UnsupportedColumnType;
import org.svgroz.vacationdb.grammar.expression.CreateTableExpression;
import org.svgroz.vacationdb.parser.VQLBaseListener;
import org.svgroz.vacationdb.parser.VQLParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CreateTableListener extends VQLBaseListener {
    private String tableName;
    private final List<Column> columns = new ArrayList<>();

    @Override
    public void exitCreate_table_name(VQLParser.Create_table_nameContext ctx) {
        this.tableName = ctx.ID().getText();
    }

    @Override
    public void exitColumn(VQLParser.ColumnContext ctx) {
        final String columnName = Objects.requireNonNull(ctx.ID().getText(), "raw column name is null");
        final String rawColumnType = Objects.requireNonNull(ctx.COLUMN_TYPE().getText(), "raw column type is null");
        final Class<? extends Cell> columnType;
        switch (rawColumnType) {
            case "BOOLEAN":
                columnType = BooleanCell.class;
                break;
            case "LONG":
                columnType = LongCell.class;
                break;
            case "DOUBLE":
                columnType = DoubleCell.class;
                break;
            case "STRING":
                columnType = StringCell.class;
                break;
            default:
                throw new UnsupportedColumnType(rawColumnType);
        }

        Column column = new Column(
                columnName,
                columnType,
                false
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
