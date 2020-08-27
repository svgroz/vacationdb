package test.cell;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.svgroz.vacationdb.datastore.api.exception.CellsTypeMismatchException;
import org.svgroz.vacationdb.datastore.api.model.DataType;
import org.svgroz.vacationdb.datastore.api.model.cell.Cells;
import org.svgroz.vacationdb.datastore.api.model.cell.DoubleCell;

/**
 * @author Simon Grozovsky svgroz@outlook.com
 */
class DefaultDoubleCellTest {

    @Test
    void getValue() {
        Assertions.assertNotNull(Cells.factory.of(0.0));
        Assertions.assertEquals(0.0, Cells.factory.of(0.0).getValue());
    }

    @Test
    void supportedType() {
        Assertions.assertEquals(DataType.DOUBLE, Cells.factory.of(0.0).supportedType());
    }

    @Test
    void compareToPositive() {
        final DoubleCell zero = Cells.factory.of(0.0);
        final DoubleCell one = Cells.factory.of(1.0);
        final DoubleCell minusOne = Cells.factory.of(-1.0);

        Assertions.assertEquals(0, zero.compareTo(Cells.factory.of(0.0)));
        Assertions.assertEquals(0, one.compareTo(Cells.factory.of(1.0)));
        Assertions.assertEquals(0, minusOne.compareTo(Cells.factory.of(-1.0)));

        Assertions.assertTrue(0 < zero.compareTo(Cells.factory.empty()));
        Assertions.assertTrue(0 < one.compareTo(Cells.factory.empty()));
        Assertions.assertTrue(0 < minusOne.compareTo(Cells.factory.empty()));

        Assertions.assertTrue(0 < zero.compareTo(Cells.factory.of(-1.0)));
        Assertions.assertTrue(0 > zero.compareTo(Cells.factory.of(1.0)));

        Assertions.assertTrue(0 < one.compareTo(Cells.factory.of(0.0)));
        Assertions.assertTrue(0 > one.compareTo(Cells.factory.of(2.0)));

        Assertions.assertTrue(0 < minusOne.compareTo(Cells.factory.of(-2.0)));
        Assertions.assertTrue(0 > minusOne.compareTo(Cells.factory.of(0.0)));
    }

    @Test
    void compareToNegative() {
        final DoubleCell cell = Cells.factory.of(0.0);

        Assertions.assertThrows(NullPointerException.class, () -> cell.compareTo(null));

        Assertions.assertThrows(CellsTypeMismatchException.class, () -> cell.compareTo(Cells.factory.of(true)));
        Assertions.assertThrows(CellsTypeMismatchException.class, () -> cell.compareTo(Cells.factory.of(0L)));
        Assertions.assertThrows(CellsTypeMismatchException.class, () -> cell.compareTo(Cells.factory.of("0")));
    }
}
