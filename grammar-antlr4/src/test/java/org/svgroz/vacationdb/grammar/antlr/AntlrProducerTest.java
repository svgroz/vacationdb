package org.svgroz.vacationdb.grammar.antlr;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.svgroz.vacationdb.datastore.model.*;
import org.svgroz.vacationdb.grammar.expression.CreateTableExpression;
import org.svgroz.vacationdb.grammar.expression.ExpressionType;
import org.svgroz.vacationdb.grammar.expression.GeneralExpression;

class AntlrProducerTest {

    @Test
    void produceOnSimpleCreateTableQuery() {
        AntlrProducer antlrProducer = new AntlrProducer();

        GeneralExpression expression = antlrProducer.produce("CREATE TABLE FOO (LONG BAR);");

        Assertions.assertNotNull(expression);
        Assertions.assertEquals(ExpressionType.CREATE_TABLE, expression.getType());
        Assertions.assertEquals(CreateTableExpression.class, expression.getClass());

        CreateTableExpression createTableExpression = (CreateTableExpression) expression;

        Assertions.assertNotNull(createTableExpression.getTable());

        Assertions.assertNotNull(createTableExpression.getTable().getName());
        Assertions.assertEquals("FOO", createTableExpression.getTable().getName());

        Assertions.assertNotNull(createTableExpression.getTable().getColumns());
        Assertions.assertEquals(1, createTableExpression.getTable().getColumns().size());

        Column column = createTableExpression.getTable().getColumns().get(0);
        Assertions.assertNotNull(column);
        Assertions.assertEquals("BAR", column.getName());
        Assertions.assertEquals(LongCell.class, column.getType());
    }

    @Test
    void produceOnSimpleCreateTableQueryWithComplexColumns() {
        AntlrProducer antlrProducer = new AntlrProducer();

        final String tableName = "FOO";
        final String firstColumnName = "BARONE";
        final Class<? extends Cell> firstColumnType = LongCell.class;
        final String secondColumnName = "BARTWO";
        final Class<? extends Cell> secondColumnType = StringCell.class;


        GeneralExpression expression = antlrProducer.produce(
                "CREATE TABLE " + tableName + " (" +
                        " LONG " + firstColumnName + " ," +
                        " STRING " + secondColumnName + " " +
                        ");"
        );

        Assertions.assertNotNull(expression);
        Assertions.assertEquals(ExpressionType.CREATE_TABLE, expression.getType());
        Assertions.assertEquals(CreateTableExpression.class, expression.getClass());

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
        final Class<? extends Cell> firstColumnType = BooleanCell.class;

        final String secondColumnName = "BARTWO";
        final Class<? extends Cell> secondColumnType = LongCell.class;

        final String thirdColumnName = "BARTHREE";
        final Class<? extends Cell> thirdColumnType = DoubleCell.class;

        final String foursColumnName = "BARFOUR";
        final Class<? extends Cell> foursColumnType = StringCell.class;


        GeneralExpression expression = antlrProducer.produce(
                "CREATE TABLE " + tableName + " (" +
                        "BOOLEAN" + " " + firstColumnName + " ," +
                        "LONG" + " " + secondColumnName + " ," +
                        "DOUBLE" + " " + thirdColumnName + " ," +
                        "STRING" + " " + foursColumnName + " " +
                        ");"
        );

        Assertions.assertNotNull(expression);
        Assertions.assertEquals(ExpressionType.CREATE_TABLE, expression.getType());
        Assertions.assertEquals(CreateTableExpression.class, expression.getClass());

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