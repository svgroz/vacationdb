package org.svgroz.vacationdb.grammar.antlr;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.svgroz.vacationdb.datastore.model.DataType;
import org.svgroz.vacationdb.datastore.model.column.Column;
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

        Assertions.assertNotNull(createTableExpression.getTableMetadata());

        Assertions.assertNotNull(createTableExpression.getTableMetadata().getName());
        Assertions.assertEquals("FOO", createTableExpression.getTableMetadata().getName());

        Assertions.assertNotNull(createTableExpression.getTableMetadata());
        Assertions.assertEquals(1, createTableExpression.getTableMetadata().columnsCount());


        Column column = Assertions.assertDoesNotThrow(() -> createTableExpression.getTableMetadata().getColumnByIndex(0));
        Assertions.assertNotNull(column);
        Assertions.assertEquals("BAR", column.getName());
        Assertions.assertEquals(DataType.LONG, column.getType());
    }

    @Test
    void produceOnSimpleCreateTableQueryWithComplexColumns() {
        AntlrProducer antlrProducer = new AntlrProducer();

        final String tableName = "FOO";
        final String firstColumnName = "BARONE";
        final DataType firstColumnType = DataType.LONG;
        final String secondColumnName = "BARTWO";
        final DataType secondColumnType = DataType.STRING;


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

        Assertions.assertNotNull(createTableExpression.getTableMetadata());

        Assertions.assertNotNull(createTableExpression.getTableMetadata().getName());
        Assertions.assertEquals("FOO", createTableExpression.getTableMetadata().getName());

        Assertions.assertEquals(2, createTableExpression.getTableMetadata().columnsCount());

        Column firstColumn = Assertions.assertDoesNotThrow(() -> createTableExpression.getTableMetadata().getColumnByIndex(0));
        Assertions.assertNotNull(firstColumn);
        Assertions.assertEquals(firstColumnName, firstColumn.getName());
        Assertions.assertEquals(firstColumnType, firstColumn.getType());

        Column secondColumn = Assertions.assertDoesNotThrow(() -> createTableExpression.getTableMetadata().getColumnByIndex(1));
        Assertions.assertNotNull(secondColumn);
        Assertions.assertEquals(secondColumnName, secondColumn.getName());
        Assertions.assertEquals(secondColumnType, secondColumn.getType());
    }

    @Test
    void produceOnCreateTableQueryWithAllColumnTypes() {
        AntlrProducer antlrProducer = new AntlrProducer();

        final String tableName = "FOO";

        final String firstColumnName = "BARONE";
        final DataType firstColumnType = DataType.BOOLEAN;

        final String secondColumnName = "BARTWO";
        final DataType secondColumnType = DataType.LONG;

        final String thirdColumnName = "BARTHREE";
        final DataType thirdColumnType = DataType.DOUBLE;

        final String foursColumnName = "BARFOUR";
        final DataType foursColumnType = DataType.STRING;


        GeneralExpression expression = antlrProducer.produce(
                "CREATE TABLE " + tableName + " (" +
                        firstColumnType.name() + " " + firstColumnName + " ," +
                        secondColumnType.name() + " " + secondColumnName + " ," +
                        thirdColumnType.name() + " " + thirdColumnName + " ," +
                        foursColumnType.name() + " " + foursColumnName + " " +
                        ");"
        );

        Assertions.assertNotNull(expression);
        Assertions.assertEquals(ExpressionType.CREATE_TABLE, expression.getType());
        Assertions.assertEquals(CreateTableExpression.class, expression.getClass());

        CreateTableExpression createTableExpression = (CreateTableExpression) expression;

        Assertions.assertNotNull(createTableExpression.getTableMetadata());

        Assertions.assertNotNull(createTableExpression.getTableMetadata().getName());
        Assertions.assertEquals("FOO", createTableExpression.getTableMetadata().getName());

        Assertions.assertEquals(4, createTableExpression.getTableMetadata().columnsCount());

        Column firstColumn = Assertions.assertDoesNotThrow(() -> createTableExpression.getTableMetadata().getColumnByIndex(0));
        Assertions.assertNotNull(firstColumn);
        Assertions.assertEquals(firstColumnName, firstColumn.getName());
        Assertions.assertEquals(firstColumnType, firstColumn.getType());

        Column secondColumn = Assertions.assertDoesNotThrow(() -> createTableExpression.getTableMetadata().getColumnByIndex(1));
        Assertions.assertNotNull(secondColumn);
        Assertions.assertEquals(secondColumnName, secondColumn.getName());
        Assertions.assertEquals(secondColumnType, secondColumn.getType());

        Column thirdColumn = Assertions.assertDoesNotThrow(() -> createTableExpression.getTableMetadata().getColumnByIndex(2));
        Assertions.assertNotNull(thirdColumn);
        Assertions.assertEquals(thirdColumnName, thirdColumn.getName());
        Assertions.assertEquals(thirdColumnType, thirdColumn.getType());

        Column foursColumn = Assertions.assertDoesNotThrow(() -> createTableExpression.getTableMetadata().getColumnByIndex(3));
        Assertions.assertNotNull(foursColumn);
        Assertions.assertEquals(foursColumnName, foursColumn.getName());
        Assertions.assertEquals(foursColumnType, foursColumn.getType());
    }
}
