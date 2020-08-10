package org.svgroz.vacationdb.grammar.antlr;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.svgroz.vacationdb.datastore.Column;
import org.svgroz.vacationdb.datastore.ColumnType;
import org.svgroz.vacationdb.grammar.expression.CreateTableExpression;
import org.svgroz.vacationdb.grammar.expression.ExpressionType;
import org.svgroz.vacationdb.grammar.expression.GeneralExpression;

class AntlrProducerTest {

    @Test
    void produce() {
        AntlrProducer antlrProducer = new AntlrProducer();

        GeneralExpression expression = antlrProducer.produce("CREATE TABLE FOO (INT BAR);");

        Assertions.assertNotNull(expression);
        Assertions.assertEquals(expression.getType(), ExpressionType.CREATE_TABLE);
        Assertions.assertEquals(expression.getClass(), CreateTableExpression.class);

        CreateTableExpression createTableExpression = (CreateTableExpression) expression;

        Assertions.assertNotNull(createTableExpression.getTable());

        Assertions.assertNotNull(createTableExpression.getTable().getName());
        Assertions.assertEquals(createTableExpression.getTable().getName(), "FOO");

        Assertions.assertNotNull(createTableExpression.getTable().getColumns());
        Assertions.assertEquals(createTableExpression.getTable().getColumns().size(), 1);

        Column column = createTableExpression.getTable().getColumns().get(0);
        Assertions.assertNotNull(column);
        Assertions.assertEquals(column.getName(), "BAR");
        Assertions.assertEquals(column.getType(), ColumnType.INTEGER);

    }
}