package org.svgroz.vacationdb.datastore.model.row;

import org.eclipse.collections.api.factory.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.svgroz.vacationdb.datastore.exception.CellsContainsNullException;
import org.svgroz.vacationdb.datastore.exception.EmptyCellsException;
import org.svgroz.vacationdb.datastore.model.cell.Cell;

import java.util.Arrays;

class DefaultRowTest {
    @Test
    void rowArgumentValidationPositive() {
        Assertions.assertDoesNotThrow(() -> new DefaultRow(Lists.immutable.of(Cell.of(true))));
        Assertions.assertDoesNotThrow(() -> new DefaultRow(Lists.immutable.of(Cell.of(true), Cell.empty()))
        );
    }

    @Test
    void rowArgumentValidationNegative() {
        Assertions.assertThrows(NullPointerException.class, () -> new DefaultRow(null));
        Assertions.assertThrows(EmptyCellsException.class, () -> new DefaultRow(Lists.immutable.empty()));
        Assertions.assertThrows(CellsContainsNullException.class, () -> new DefaultRow(
                        Lists.immutable.ofAll(Arrays.asList(
                                Cell.of(true),
                                null,
                                Cell.empty()
                        ))
                )
        );
    }
}
