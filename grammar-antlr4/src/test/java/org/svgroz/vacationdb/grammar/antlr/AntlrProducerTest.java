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
    void produce() {
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
        Assertions.assertEquals("BAR", column.getName() );
        Assertions.assertEquals(ColumnType.LONG, column.getType());

    }
}