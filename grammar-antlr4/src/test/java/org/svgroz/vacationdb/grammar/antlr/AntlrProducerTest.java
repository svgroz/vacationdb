package org.svgroz.vacationdb.grammar.antlr;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.svgroz.vacationdb.datastore.model.Column;
import org.svgroz.vacationdb.datastore.model.ColumnType;
import org.svgroz.vacationdb.grammar.expression.CreateTableExpression;
import org.svgroz.vacationdb.grammar.expression.ExpressionType;
import org.svgroz.vacationdb.grammar.expression.GeneralExpression;

class AntlrProducerTest {

    @Test
    void produceOnSimpleCreateTableQuery() {
        AntlrProducer antlrProducer = new AntlrProducer();

        GeneralExpression expression = antlrProducer.produce("CREATE TABLE FOO (LONG BAR);");

        Assertions.assertNotNull(expression);
        Assertions.assertEquals(expression.getType(), ExpressionType.CREATE_TABLE);
        Assertions.assertEquals(expression.getClass(), CreateTableExpression.class);

        CreateTableExpression createTableExpression = (CreateTableExpression) expression;

        Assertions.assertNotNull(createTableExpression.getTable());

        Assertions.assertNotNull(createTableExpression.getTable().getName());
        Assertions.assertEquals("FOO", createTableExpression.getTable().getName());

        Assertions.assertNotNull(createTableExpression.getTable().getColumns());
        Assertions.assertEquals(1, createTableExpression.getTable().getColumns().size());

        Column column = createTableExpression.getTable().getColumns().get(0);
        Assertions.assertNotNull(column);
        Assertions.assertEquals("BAR", column.getName());
        Assertions.assertEquals(ColumnType.LONG, column.getType());
    }

    @Test
    void produceOnSimpleCreateTableQueryWithComplexColumns() {
        AntlrProducer antlrProducer = new AntlrProducer();

        final String tableName = "FOO";
        final String firstColumnName = "BARONE";
        final ColumnType firstColumnType = ColumnType.LONG;
        final String secondColumnName = "BARTWO";
        final ColumnType secondColumnType = ColumnType.STRING;


        GeneralExpression expression = antlrProducer.produce(
                "CREATE TABLE " + tableName + " (" +
                        firstColumnType.name() + " " + firstColumnName + " ," +
                        secondColumnType.name() + " " + secondColumnName + " " +
                        ");"
        );

        Assertions.assertNotNull(expression);
        Assertions.assertEquals(expression.getType(), ExpressionType.CREATE_TABLE);
        Assertions.assertEquals(expression.getClass(), CreateTableExpression.class);

        CreateTableExpression createTableExpression = (CreateTableExpression) expression;

        Assertions.assertNotNull(createTableExpression.getTable());

        Assertions.assertNotNull(createTableExpression.getTable().getName());
        Assertions.assertEquals("FOO", createTableExpression.getTable().getName());

        Assertions.assertNotNull(createTableExpression.getTable().getColumns());
        Assertions.assertEquals(2, createTableExpression.getTable().getColumns().size());

        Column firstColumn = createTableExpression.getTable().getColumns().get(0);
        Assertions.assertNotNull(firstColumn);
        Assertions.assertEquals(firstColumnName, firstColumn.getName());
        Assertions.assertEquals(firstColumnType, firstColumn.getType());

        Column secondColumn = createTableExpression.getTable().getColumns().get(1);
        Assertions.assertNotNull(secondColumn);
        Assertions.assertEquals(secondColumnName, secondColumn.getName());
        Assertions.assertEquals(secondColumnType, secondColumn.getType());
    }

    @Test
    void produceOnCreateTableQueryWithAllColumnTypes() {
        AntlrProducer antlrProducer = new AntlrProducer();

        final String tableName = "FOO";

        final String firstColumnName = "BARONE";
        final ColumnType firstColumnType = ColumnType.BOOLEAN;

        final String secondColumnName = "BARTWO";
        final ColumnType secondColumnType = ColumnType.LONG;

        final String thirdColumnName = "BARTHREE";
        final ColumnType thirdColumnType = ColumnType.DOUBLE;

        final String foursColumnName = "BARFOUR";
        final ColumnType foursColumnType = ColumnType.STRING;


        GeneralExpression expression = antlrProducer.produce(
                "CREATE TABLE " + tableName + " (" +
                        firstColumnType.name() + " " + firstColumnName + " ," +
                        secondColumnType.name() + " " + secondColumnName + " ," +
                        thirdColumnType.name() + " " + thirdColumnName + " ," +
                        foursColumnType.name() + " " + foursColumnName + " " +
                        ");"
        );

        Assertions.assertNotNull(expression);
        Assertions.assertEquals(expression.getType(), ExpressionType.CREATE_TABLE);
        Assertions.assertEquals(expression.getClass(), CreateTableExpression.class);

        CreateTableExpression createTableExpression = (CreateTableExpression) expression;

        Assertions.assertNotNull(createTableExpression.getTable());

        Assertions.assertNotNull(createTableExpression.getTable().getName());
        Assertions.assertEquals("FOO", createTableExpression.getTable().getName());

        Assertions.assertNotNull(createTableExpression.getTable().getColumns());
        Assertions.assertEquals(4, createTableExpression.getTable().getColumns().size());

        Column firstColumn = createTableExpression.getTable().getColumns().get(0);
        Assertions.assertNotNull(firstColumn);
        Assertions.assertEquals(firstColumnName, firstColumn.getName());
        Assertions.assertEquals(firstColumnType, firstColumn.getType());

        Column secondColumn = createTableExpression.getTable().getColumns().get(1);
        Assertions.assertNotNull(secondColumn);
        Assertions.assertEquals(secondColumnName, secondColumn.getName());
        Assertions.assertEquals(secondColumnType, secondColumn.getType());

        Column thirdColumn = createTableExpression.getTable().getColumns().get(2);
        Assertions.assertNotNull(thirdColumn);
        Assertions.assertEquals(thirdColumnName, thirdColumn.getName());
        Assertions.assertEquals(thirdColumnType, thirdColumn.getType());

        Column foursColumn = createTableExpression.getTable().getColumns().get(3);
        Assertions.assertNotNull(foursColumn);
        Assertions.assertEquals(foursColumnName, foursColumn.getName());
        Assertions.assertEquals(foursColumnType, foursColumn.getType());
    }
}