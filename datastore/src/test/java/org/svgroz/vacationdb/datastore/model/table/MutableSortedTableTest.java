package org.svgroz.vacationdb.datastore.model.table;

import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.ImmutableList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.svgroz.vacationdb.datastore.exception.ColumnsContainsNullException;
import org.svgroz.vacationdb.datastore.exception.ColumnsDoesNotContainsKeysException;
import org.svgroz.vacationdb.datastore.exception.EmptyColumnsException;
import org.svgroz.vacationdb.datastore.model.DataType;
import org.svgroz.vacationdb.datastore.model.cell.Cell;
import org.svgroz.vacationdb.datastore.model.column.Column;
import org.svgroz.vacationdb.datastore.model.column.KeyColumn;
import org.svgroz.vacationdb.datastore.model.row.Row;
import org.svgroz.vacationdb.datastore.statement.Condition;

import java.util.Arrays;

class MutableSortedTableTest {
    @Test
    void tableArgumentsValidationPositive() {
        final Column validColumn1 = Column.of("name1", DataType.BOOLEAN, true);
        final Column validColumn2 = Column.of("name2", DataType.BOOLEAN, false);
        Assertions.assertDoesNotThrow(() -> new MutableSortedTable("foo", Lists.immutable.of(validColumn1, validColumn2)));
    }

    @Test
    void tableArgumentsValidationNegative() {
        final Column validColumn = Column.of("name", DataType.BOOLEAN, false);
        Assertions.assertThrows(NullPointerException.class, () -> new MutableSortedTable(null, Lists.immutable.of(validColumn)));
        Assertions.assertThrows(NullPointerException.class, () -> new MutableSortedTable("name", null));
        Assertions.assertThrows(EmptyColumnsException.class, () -> new MutableSortedTable("name", Lists.immutable.empty()));
        Assertions.assertThrows(ColumnsContainsNullException.class, () -> new MutableSortedTable("foo", Lists.immutable.ofAll(Arrays.asList(validColumn, null))));
        Assertions.assertThrows(ColumnsDoesNotContainsKeysException.class, () -> new MutableSortedTable("foo", Lists.immutable.ofAll(Arrays.asList(validColumn))));
    }

    @Test
    void selectPositiveTest() {
        MutableSortedTable table = new MutableSortedTable(
                "TEST",
                Lists.immutable.of(
                        Column.of("FOO", DataType.LONG, true),
                        Column.of("BAR", DataType.LONG, true),
                        Column.of("BIZ", DataType.STRING, false)
                )
        );

        table.addRow(Row.of(Cell.of(1L), Cell.of(100L), Cell.of("TADA")));
        table.addRow(Row.of(Cell.of(2L), Cell.of(100L), Cell.of("TADA")));
        table.addRow(Row.of(Cell.of(1L), Cell.of(101L), Cell.of("TADA")));
        table.addRow(Row.of(Cell.of(2L), Cell.of(100L), Cell.of("T")));


        ImmutableList<Row> select = Assertions.assertDoesNotThrow(() -> table.select(
                Lists.immutable.of(
                        Condition.equalsCondition(
                                KeyColumn.of("FOO", DataType.LONG),
                                Cell.of(1L)
                        ),
                        Condition.equalsCondition(
                                KeyColumn.of("BAR", DataType.LONG),
                                Cell.of(100L)
                        )
                )
        ));

        Assertions.assertNotNull(select);
        Assertions.assertEquals(1, select.size());
        Assertions.assertNotNull(select.get(0));
        Assertions.assertNotNull(select.get(0).getCells());
        Assertions.assertEquals(3, select.get(0).getCells().size());
        Assertions.assertNotNull(select.get(0).getCells().get(0));
        Assertions.assertEquals(DataType.LONG, select.get(0).getCells().get(0).supportedType());

        Assertions.assertNotNull(select.get(0).getCells().get(1));
        Assertions.assertEquals(DataType.LONG, select.get(0).getCells().get(1).supportedType());
        Assertions.assertNotNull(select.get(0).getCells().get(2));
        Assertions.assertEquals(DataType.STRING, select.get(0).getCells().get(2).supportedType());

        System.out.println(select);
    }
}
