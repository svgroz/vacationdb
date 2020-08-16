package org.svgroz.vacationdb.datastore.model.table;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.svgroz.vacationdb.datastore.exception.ColumnsContainsNullException;
import org.svgroz.vacationdb.datastore.exception.ColumnsContainsSameNamesException;
import org.svgroz.vacationdb.datastore.exception.EmptyColumnsException;
import org.svgroz.vacationdb.datastore.model.DataType;
import org.svgroz.vacationdb.datastore.model.column.Column;

import java.util.ArrayList;
import java.util.List;

class DefaultTableMetadataTest {

    @Test
    void constructorPositive() {
        DefaultTableMetadata defaultTableMetadata = Assertions.assertDoesNotThrow(
                () -> new DefaultTableMetadata(
                        "TABLEONE",
                        List.of(
                                Column.of("FOO", DataType.BOOLEAN, false),
                                Column.of("BAR", DataType.LONG, true)
                        )
                )
        );

        Assertions.assertNotNull(defaultTableMetadata);
        Assertions.assertNotNull(defaultTableMetadata.getColumns());
        Assertions.assertEquals(2, defaultTableMetadata.getColumns().size());
        Assertions.assertNotNull(defaultTableMetadata.getColumns().get(0));
        Assertions.assertEquals("FOO", defaultTableMetadata.getColumns().get(0).getName());
        Assertions.assertEquals(DataType.BOOLEAN, defaultTableMetadata.getColumns().get(0).getType());
        Assertions.assertNotNull(defaultTableMetadata.getColumns().get(1));
        Assertions.assertEquals("BAR", defaultTableMetadata.getColumns().get(1).getName());
        Assertions.assertEquals(DataType.LONG, defaultTableMetadata.getColumns().get(1).getType());
    }

    @Test
    void constructorNegative() {
        Assertions.assertThrows(NullPointerException.class, () -> new DefaultTableMetadata(null, null));
        Assertions.assertThrows(NullPointerException.class, () -> new DefaultTableMetadata("TABLENAME", null));
        Assertions.assertThrows(EmptyColumnsException.class, () -> new DefaultTableMetadata("TABLENAME", List.of()));
        Assertions.assertThrows(
                ColumnsContainsNullException.class,
                () -> {
                    final ArrayList<Column> columns = new ArrayList<>();
                    columns.add(null);
                    new DefaultTableMetadata("TABLENAME", columns);
                }
        );
        Assertions.assertThrows(
                ColumnsContainsSameNamesException.class,
                () -> new DefaultTableMetadata(
                        "TABLENAME",
                        List.of(
                                Column.of("FOO", DataType.BOOLEAN, false),
                                Column.of("FOO", DataType.BOOLEAN, false)
                        )
                )
        );
    }
}
