package org.svgroz.vacationdb.datastore.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.svgroz.vacationdb.datastore.exception.ColumnsContainsNullException;
import org.svgroz.vacationdb.datastore.exception.ColumnsContainsSameNamesException;
import org.svgroz.vacationdb.datastore.exception.ColumnsDoesNotContainsKeysException;
import org.svgroz.vacationdb.datastore.exception.EmptyColumnsException;

import java.util.ArrayList;
import java.util.List;

class TableMetadataTest {

    @Test
    void constructorPositive() {
        TableMetadata tableMetadata = Assertions.assertDoesNotThrow(
                () -> new TableMetadata(
                        List.of(
                                new Column("FOO", BooleanCell.class, false),
                                new Column("BAR", LongCell.class, true)
                        )
                )
        );

        Assertions.assertNotNull(tableMetadata);
        Assertions.assertNotNull(tableMetadata.getColumns());
        Assertions.assertEquals(2, tableMetadata.getColumns().size());
        Assertions.assertNotNull(tableMetadata.getColumns().get(0));
        Assertions.assertEquals("FOO", tableMetadata.getColumns().get(0).getName());
        Assertions.assertEquals(BooleanCell.class, tableMetadata.getColumns().get(0).getType());
        Assertions.assertNotNull(tableMetadata.getColumns().get(1));
        Assertions.assertEquals("BAR", tableMetadata.getColumns().get(1).getName());
        Assertions.assertEquals(LongCell.class, tableMetadata.getColumns().get(1).getType());
        Assertions.assertNotNull(tableMetadata.getKeyIndexesContainer());
        Assertions.assertNotNull(tableMetadata.getKeyIndexesContainer().getIndexes());
        Assertions.assertEquals(1, tableMetadata.getKeyIndexesContainer().getIndexes().size());
        Assertions.assertEquals(1, tableMetadata.getKeyIndexesContainer().getIndexes().get(0));
    }

    @Test
    void constructorNegative() {
        Assertions.assertThrows(NullPointerException.class, () -> new TableMetadata(null));
        Assertions.assertThrows(EmptyColumnsException.class, () -> new TableMetadata(List.of()));
        Assertions.assertThrows(
                ColumnsContainsNullException.class,
                () -> {
                    final ArrayList<Column> columns = new ArrayList<>();
                    columns.add(null);
                    new TableMetadata(columns);
                }
        );
        Assertions.assertThrows(
                ColumnsContainsSameNamesException.class,
                () -> new TableMetadata(
                        List.of(
                                new Column("FOO", BooleanCell.class, false),
                                new Column("FOO", BooleanCell.class, false)
                        )
                )
        );
        Assertions.assertThrows(
                ColumnsDoesNotContainsKeysException.class,
                () -> new TableMetadata(
                        List.of(
                                new Column("FOO", BooleanCell.class, false),
                                new Column("BAR", BooleanCell.class, false)
                        )
                )
        );
    }
}