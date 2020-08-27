package test.cell;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.svgroz.vacationdb.datastore.api.exception.CellsTypeMismatchException;
import org.svgroz.vacationdb.datastore.api.model.cell.BooleanCell;
import org.svgroz.vacationdb.datastore.api.model.cell.Cells;
import org.svgroz.vacationdb.datastore.api.model.cell.DoubleCell;
import org.svgroz.vacationdb.datastore.api.model.cell.LongCell;
import org.svgroz.vacationdb.datastore.api.model.cell.StringCell;

/**
 * Simon Grozovsky svgroz@outlook.com
 */
class DefaultBooleanCellTest {

    @Test
    void compareToPositive() {
        final BooleanCell cellTrue = Cells.factory.of(true);
        final BooleanCell cellFalse = Cells.factory.of(false);

        Assertions.assertEquals(1, cellTrue.compareTo(cellFalse));
        Assertions.assertEquals(-1, cellFalse.compareTo(cellTrue));
        Assertions.assertEquals(0, cellTrue.compareTo(Cells.factory.of(true)));
        Assertions.assertEquals(0, cellFalse.compareTo(Cells.factory.of(false)));
        Assertions.assertEquals(1, cellTrue.compareTo(Cells.factory.empty()));
        Assertions.assertEquals(1, cellFalse.compareTo(Cells.factory.empty()));
    }

    @Test
    void compareToNegative() {
        final BooleanCell cellTrue = Cells.factory.of(true);
        final BooleanCell cellFalse = Cells.factory.of(false);
        final LongCell longCell = Cells.factory.of(0L);
        final DoubleCell doubleCell = Cells.factory.of(0.0);
        final StringCell stringCell = Cells.factory.of("0");

        Assertions.assertThrows(NullPointerException.class, () -> cellTrue.compareTo(null));
        Assertions.assertThrows(NullPointerException.class, () -> cellFalse.compareTo(null));

        Assertions.assertThrows(CellsTypeMismatchException.class, () -> cellTrue.compareTo(longCell));
        Assertions.assertThrows(CellsTypeMismatchException.class, () -> cellTrue.compareTo(doubleCell));
        Assertions.assertThrows(CellsTypeMismatchException.class, () -> cellTrue.compareTo(stringCell));

        Assertions.assertThrows(CellsTypeMismatchException.class, () -> cellFalse.compareTo(longCell));
        Assertions.assertThrows(CellsTypeMismatchException.class, () -> cellFalse.compareTo(doubleCell));
        Assertions.assertThrows(CellsTypeMismatchException.class, () -> cellFalse.compareTo(stringCell));
    }
}
