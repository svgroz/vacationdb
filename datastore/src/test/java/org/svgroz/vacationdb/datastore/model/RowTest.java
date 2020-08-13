package org.svgroz.vacationdb.datastore.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.svgroz.vacationdb.datastore.exception.CellsContainsNullException;
import org.svgroz.vacationdb.datastore.exception.EmptyCellsException;
import org.svgroz.vacationdb.datastore.exception.MaxKeyIdIsBiggerThanCellsCountException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class RowTest {
    @Test
    void rowArgumentValidationPositive() {
        final KeyIndexesContainer keyIndexesContainer = new KeyIndexesContainer(List.of(0));

        Assertions.assertDoesNotThrow(() -> new Row(List.of(Cell.of(true)), keyIndexesContainer));
        Assertions.assertDoesNotThrow(() -> new Row(List.of(Cell.of(true), Cell.empty()), keyIndexesContainer)
        );
    }

    @Test
    void rowArgumentValidationNegative() {
        final KeyIndexesContainer valid = new KeyIndexesContainer(List.of(0));
        final KeyIndexesContainer invalid = new KeyIndexesContainer(List.of(Integer.MAX_VALUE));

        Assertions.assertThrows(NullPointerException.class, () -> new Row(null, valid));
        Assertions.assertThrows(EmptyCellsException.class, () -> new Row(Collections.emptyList(), valid));
        Assertions.assertThrows(CellsContainsNullException.class, () -> new Row(
                        Arrays.asList(
                                Cell.of(true),
                                null,
                                Cell.empty()
                        ),
                        valid
                )
        );
        Assertions.assertThrows(MaxKeyIdIsBiggerThanCellsCountException.class, () -> new Row(List.of(Cell.of(true)), invalid));
    }
}
