package test.cell;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.svgroz.vacationdb.datastore.api.exception.CellsTypeMismatchException;
import org.svgroz.vacationdb.datastore.api.model.DataType;
import org.svgroz.vacationdb.datastore.api.model.cell.Cells;
import org.svgroz.vacationdb.datastore.api.model.cell.LongCell;

/**
 * @author Simon Grozovsky svgroz@outlook.com
 */
class DefaultLongCellTest {

    @Test
    void getValue() {
        Assertions.assertNotNull(Cells.factory.of(0L).getValue());
        Assertions.assertEquals(0L, Cells.factory.of(0L).getValue());
    }

    @Test
    void supportedType() {
        Assertions.assertEquals(DataType.LONG, Cells.factory.of(0L).supportedType());
    }

    @Test
    void compareToPositive() {
        final LongCell zero = Cells.factory.of(0L);
        final LongCell one = Cells.factory.of(1L);
        final LongCell minusOne = Cells.factory.of(-1L);

        Assertions.assertEquals(0, zero.compareTo(Cells.factory.of(0L)));
        Assertions.assertEquals(0, one.compareTo(Cells.factory.of(1L)));
        Assertions.assertEquals(0, minusOne.compareTo(Cells.factory.of(-1L)));

        Assertions.assertTrue(0 < zero.compareTo(Cells.factory.empty()));
        Assertions.assertTrue(0 < one.compareTo(Cells.factory.empty()));
        Assertions.assertTrue(0 < minusOne.compareTo(Cells.factory.empty()));

        Assertions.assertTrue(0 < zero.compareTo(Cells.factory.of(-1L)));
        Assertions.assertTrue(0 > zero.compareTo(Cells.factory.of(1L)));

        Assertions.assertTrue(0 < one.compareTo(Cells.factory.of(0L)));
        Assertions.assertTrue(0 > one.compareTo(Cells.factory.of(2L)));

        Assertions.assertTrue(0 < minusOne.compareTo(Cells.factory.of(-2L)));
        Assertions.assertTrue(0 > minusOne.compareTo(Cells.factory.of(0L)));
    }

    @Test
    void compareToNegative() {
        final LongCell cell = Cells.factory.of(0L);

        Assertions.assertThrows(NullPointerException.class, () -> cell.compareTo(null));

        Assertions.assertThrows(CellsTypeMismatchException.class, () -> cell.compareTo(Cells.factory.of(true)));
        Assertions.assertThrows(CellsTypeMismatchException.class, () -> cell.compareTo(Cells.factory.of(0.0)));
        Assertions.assertThrows(CellsTypeMismatchException.class, () -> cell.compareTo(Cells.factory.of("0")));
    }
}
