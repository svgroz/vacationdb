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
    void test1() {
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
                                new Column("FOO", BooleanCell.class),
                                new Column("FOO", BooleanCell.class)
                        )
                )
        );
    }
}