package test.row;

import org.eclipse.collections.api.factory.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.svgroz.vacationdb.datastore.api.exception.CellsContainsNullException;
import org.svgroz.vacationdb.datastore.api.exception.EmptyCellsException;
import org.svgroz.vacationdb.datastore.api.model.cell.Cells;
import org.svgroz.vacationdb.datastore.api.model.row.Rows;

import java.util.Arrays;

class DefaultRowTest {
    @Test
    void rowArgumentValidationPositive() {
        Assertions.assertDoesNotThrow(() -> Rows.factory.of(Lists.immutable.of(Cells.factory.of(true))));
        Assertions.assertDoesNotThrow(() -> Rows.factory.of(Lists.immutable.of(Cells.factory.of(true), Cells.factory.empty()))
        );
    }

    @Test
    void rowArgumentValidationNegative() {
        Assertions.assertThrows(EmptyCellsException.class, () -> Rows.factory.of(Lists.immutable.empty()));
        Assertions.assertThrows(CellsContainsNullException.class, () -> Rows.factory.of(
                Lists.immutable.ofAll(
                        Arrays.asList(
                                Cells.factory.of(true),
                                null,
                                Cells.factory.empty()
                        )
                )
                )
        );
    }
}
