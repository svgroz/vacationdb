package org.svgroz.vacationdb.datastore.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.svgroz.vacationdb.datastore.exception.CellsContainsNullException;
import org.svgroz.vacationdb.datastore.exception.EmptyCellsException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class RowTest {
    @Test
    void rowArgumentValidationPositive() {
        Assertions.assertDoesNotThrow(() -> new Row(
                        List.of(
                                new Cell<>(ColumnType.BOOLEAN, true)
                        )
                )
        );
        Assertions.assertDoesNotThrow(() -> new Row(
                        List.of(
                                new Cell<>(ColumnType.BOOLEAN, true),
                                new Cell<>(ColumnType.BOOLEAN, null)
                        )
                )
        );
    }

    @Test
    void rowArgumentValidationNegative() {
        Assertions.assertThrows(NullPointerException.class, () -> new Row(null));
        Assertions.assertThrows(EmptyCellsException.class, () -> new Row(Collections.emptyList()));
        Assertions.assertThrows(CellsContainsNullException.class, () -> new Row(
                Arrays.asList(
                        new Cell<>(ColumnType.BOOLEAN, true),
                        null,
                        new Cell<>(ColumnType.BOOLEAN, null)
                )
        ));
    }
}