package org.svgroz.vacationdb.datastore.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.svgroz.vacationdb.datastore.exception.ColumnsContainsNullException;
import org.svgroz.vacationdb.datastore.exception.ColumnsContainsSameNamesException;
import org.svgroz.vacationdb.datastore.exception.EmptyColumnsException;

import java.util.ArrayList;
import java.util.List;

class TableMetadataTest {

    @Test
    void constructorPositive() {
        TableMetadata TableMetadata = Assertions.assertDoesNotThrow(
                () -> new TableMetadata(
                        "TABLEONE",
                        List.of(
                                new Column("FOO", BooleanCell.class, false),
                                new Column("BAR", LongCell.class, true)
                        )
                )
        );

        Assertions.assertNotNull(TableMetadata);
        Assertions.assertNotNull(TableMetadata.getColumns());
        Assertions.assertEquals(2, TableMetadata.getColumns().size());
        Assertions.assertNotNull(TableMetadata.getColumns().get(0));
        Assertions.assertEquals("FOO", TableMetadata.getColumns().get(0).getName());
        Assertions.assertEquals(BooleanCell.class, TableMetadata.getColumns().get(0).getType());
        Assertions.assertNotNull(TableMetadata.getColumns().get(1));
        Assertions.assertEquals("BAR", TableMetadata.getColumns().get(1).getName());
        Assertions.assertEquals(LongCell.class, TableMetadata.getColumns().get(1).getType());
    }

    @Test
    void constructorNegative() {
        Assertions.assertThrows(NullPointerException.class, () -> new TableMetadata(null, null));
        Assertions.assertThrows(NullPointerException.class, () -> new TableMetadata("TABLENAME", null));
        Assertions.assertThrows(EmptyColumnsException.class, () -> new TableMetadata("TABLENAME", List.of()));
        Assertions.assertThrows(
                ColumnsContainsNullException.class,
                () -> {
                    final ArrayList<Column> columns = new ArrayList<>();
                    columns.add(null);
                    new TableMetadata("TABLENAME", columns);
                }
        );
        Assertions.assertThrows(
                ColumnsContainsSameNamesException.class,
                () -> new TableMetadata(
                        "TABLENAME",
                        List.of(
                                new Column("FOO", BooleanCell.class, false),
                                new Column("FOO", BooleanCell.class, false)
                        )
                )
        );
    }
}
