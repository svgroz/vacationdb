package org.svgroz.vacationdb.datastore.model.table;

import org.eclipse.collections.api.factory.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.svgroz.vacationdb.datastore.exception.ColumnsContainsNullException;
import org.svgroz.vacationdb.datastore.exception.ColumnsContainsSameNamesException;
import org.svgroz.vacationdb.datastore.exception.ColumnsSupposedToBeOnlyInBeginning;
import org.svgroz.vacationdb.datastore.exception.EmptyColumnsException;
import org.svgroz.vacationdb.datastore.model.DataType;
import org.svgroz.vacationdb.datastore.model.column.Column;

import java.util.ArrayList;

class DefaultTableMetadataTest {

    @Test
    void constructorPositive() {
        DefaultTableMetadata defaultTableMetadata = Assertions.assertDoesNotThrow(
                () -> new DefaultTableMetadata(
                        "TABLEONE",
                        Lists.immutable.of(
                                Column.of("FOO", DataType.BOOLEAN, true),
                                Column.of("BAR", DataType.LONG, false),
                                Column.of("BIZ", DataType.LONG, false)
                        )
                )
        );

        Assertions.assertNotNull(defaultTableMetadata);
        Assertions.assertEquals(3, defaultTableMetadata.columnsCount());
        Column column1 = Assertions.assertDoesNotThrow(() -> defaultTableMetadata.getColumnByIndex(0));
        Assertions.assertNotNull(column1);
        Assertions.assertEquals("FOO", column1.getName());
        Assertions.assertEquals(DataType.BOOLEAN, column1.getType());
        Column column2 = Assertions.assertDoesNotThrow(() -> defaultTableMetadata.getColumnByIndex(1));
        Assertions.assertEquals("BAR", column2.getName());
        Assertions.assertEquals(DataType.LONG, column2.getType());
    }

    @Test
    void constructorNegative() {
        Assertions.assertThrows(NullPointerException.class, () -> new DefaultTableMetadata(null, null));
        Assertions.assertThrows(NullPointerException.class, () -> new DefaultTableMetadata("TABLENAME", null));
        Assertions.assertThrows(EmptyColumnsException.class, () -> new DefaultTableMetadata("TABLENAME", Lists.immutable.empty()));
        Assertions.assertThrows(
                ColumnsContainsNullException.class,
                () -> {
                    final ArrayList<Column> columns = new ArrayList<>();
                    columns.add(null);
                    new DefaultTableMetadata("TABLENAME", Lists.immutable.ofAll(columns));
                }
        );
        Assertions.assertThrows(
                ColumnsContainsSameNamesException.class,
                () -> new DefaultTableMetadata(
                        "TABLENAME",
                        Lists.immutable.of(
                                Column.of("FOO", DataType.BOOLEAN, false),
                                Column.of("FOO", DataType.BOOLEAN, false)
                        )
                )
        );

        Assertions.assertThrows(
                ColumnsSupposedToBeOnlyInBeginning.class,
                () -> new DefaultTableMetadata(
                        "TABLENAME",
                        Lists.immutable.of(
                                Column.of("Z", DataType.BOOLEAN, true),
                                Column.of("C", DataType.BOOLEAN, false),
                                Column.of("U", DataType.BOOLEAN, true)
                        )
                )
        );
    }
}
