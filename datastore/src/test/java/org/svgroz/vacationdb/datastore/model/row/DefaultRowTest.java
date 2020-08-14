package org.svgroz.vacationdb.datastore.model.row;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.svgroz.vacationdb.datastore.exception.CellsContainsNullException;
import org.svgroz.vacationdb.datastore.exception.EmptyCellsException;
import org.svgroz.vacationdb.datastore.model.cell.Cell;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class DefaultRowTest {
    @Test
    void rowArgumentValidationPositive() {
        Assertions.assertDoesNotThrow(() -> new DefaultRow(List.of(Cell.of(true))));
        Assertions.assertDoesNotThrow(() -> new DefaultRow(List.of(Cell.of(true), Cell.empty()))
        );
    }

    @Test
    void rowArgumentValidationNegative() {
        Assertions.assertThrows(NullPointerException.class, () -> new DefaultRow(null));
        Assertions.assertThrows(EmptyCellsException.class, () -> new DefaultRow(Collections.emptyList()));
        Assertions.assertThrows(CellsContainsNullException.class, () -> new DefaultRow(
                        Arrays.asList(
                                Cell.of(true),
                                null,
                                Cell.empty()
                        )
                )
        );
    }
}
