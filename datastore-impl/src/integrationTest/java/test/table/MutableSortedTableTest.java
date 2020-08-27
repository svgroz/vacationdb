package test.table;

import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.ImmutableList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.svgroz.vacationdb.datastore.api.exception.ColumnsContainsNullException;
import org.svgroz.vacationdb.datastore.api.exception.ColumnsDoesNotContainsKeysException;
import org.svgroz.vacationdb.datastore.api.exception.EmptyColumnsException;
import org.svgroz.vacationdb.datastore.api.model.cell.Cells;
import org.svgroz.vacationdb.datastore.api.model.cell.LongCell;
import org.svgroz.vacationdb.datastore.api.model.cell.StringCell;
import org.svgroz.vacationdb.datastore.api.model.column.Column;
import org.svgroz.vacationdb.datastore.api.model.column.Columns;
import org.svgroz.vacationdb.datastore.api.model.row.Row;
import org.svgroz.vacationdb.datastore.api.model.row.Rows;
import org.svgroz.vacationdb.datastore.api.model.table.MutableTable;
import org.svgroz.vacationdb.datastore.api.model.table.Tables;
import org.svgroz.vacationdb.datastore.api.statement.Condition;

import java.util.Arrays;

class MutableSortedTableTest {
    @Test
    void tableArgumentsValidationPositive() {
        final Column validColumn1 = Columns.key.booleanOf("name1");
        final Column validColumn2 = Columns.key.booleanOf("name2");
        Assertions.assertDoesNotThrow(() -> Tables.tableFactory.mutable("foo", Lists.immutable.of(validColumn1, validColumn2)));
    }

    @Test
    void tableArgumentsValidationNegative() {
        final Column validColumn = Columns.regular.booleanOf("name");
        Assertions.assertThrows(NullPointerException.class, () -> Tables.tableFactory.mutable(null, Lists.immutable.of(validColumn)));
        Assertions.assertThrows(NullPointerException.class, () -> Tables.tableFactory.mutable("name", null));
        Assertions.assertThrows(EmptyColumnsException.class, () -> Tables.tableFactory.mutable("name", Lists.immutable.empty()));
        Assertions.assertThrows(ColumnsContainsNullException.class, () -> Tables.tableFactory.mutable("foo", Lists.immutable.ofAll(Arrays.asList(validColumn, null))));
        Assertions.assertThrows(ColumnsDoesNotContainsKeysException.class, () -> Tables.tableFactory.mutable("foo", Lists.immutable.ofAll(Arrays.asList(validColumn))));
    }

    @Test
    void selectPositiveTest() {
        MutableTable table = Tables.tableFactory.mutable(
                "TEST",
                Lists.immutable.of(
                        Columns.key.longOf("FOO"),
                        Columns.key.longOf("BAR"),
                        Columns.key.stringOf("BIZ")
                )
        );

        table.addRow(Rows.factory.of(Cells.factory.of(1L), Cells.factory.of(100L), Cells.factory.of("TADA")));
        table.addRow(Rows.factory.of(Cells.factory.of(2L), Cells.factory.of(100L), Cells.factory.of("TADA")));
        table.addRow(Rows.factory.of(Cells.factory.of(1L), Cells.factory.of(101L), Cells.factory.of("TADA")));
        table.addRow(Rows.factory.of(Cells.factory.of(2L), Cells.factory.of(100L), Cells.factory.of("T")));


        ImmutableList<Row> select = Assertions.assertDoesNotThrow(() -> table.select(
                Lists.immutable.of(
                        Condition.equalsCondition(
                                Columns.key.longOf("FOO"),
                                Cells.factory.of(1L)
                        ),
                        Condition.equalsCondition(
                                Columns.key.longOf("BAR"),
                                Cells.factory.of(100L)
                        )
                )
        ));

        Assertions.assertNotNull(select);
        Assertions.assertEquals(1, select.size());
        Assertions.assertNotNull(select.get(0));
        Assertions.assertNotNull(select.get(0).getCells());
        Assertions.assertEquals(3, select.get(0).getCells().size());
        Assertions.assertNotNull(select.get(0).getCells().get(0));
        Assertions.assertTrue(select.get(0).getCells().get(0) instanceof LongCell);

        Assertions.assertNotNull(select.get(0).getCells().get(1));
        Assertions.assertTrue(select.get(0).getCells().get(1) instanceof LongCell);
        Assertions.assertNotNull(select.get(0).getCells().get(2));
        Assertions.assertTrue(select.get(0).getCells().get(2) instanceof StringCell);
    }
}
