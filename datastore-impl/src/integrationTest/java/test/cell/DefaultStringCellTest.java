package test.cell;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.svgroz.vacationdb.datastore.api.exception.CellsTypeMismatchException;
import org.svgroz.vacationdb.datastore.api.model.DataType;
import org.svgroz.vacationdb.datastore.api.model.cell.Cells;
import org.svgroz.vacationdb.datastore.api.model.cell.StringCell;

/**
 * @author Simon Grozovsky svgroz@outlook.com
 */
class DefaultStringCellTest {

    @Test
    void getValue() {
        Assertions.assertNotNull(Cells.factory.of("0"));
        Assertions.assertEquals("0", Cells.factory.of("0").getValue());
    }

    @Test
    void supportedType() {
        Assertions.assertTrue(Cells.factory.of("0") instanceof StringCell);
    }

    @Test
    void compareToPositive() {
        final StringCell zero = Cells.factory.of("0");
        final StringCell one = Cells.factory.of("1");
        final StringCell minusOne = Cells.factory.of("-1");

        Assertions.assertEquals(0, zero.compareTo(Cells.factory.of("0")));
        Assertions.assertEquals(0, one.compareTo(Cells.factory.of("1")));
        Assertions.assertEquals(0, minusOne.compareTo(Cells.factory.of("-1")));

        Assertions.assertTrue(0 < zero.compareTo(Cells.factory.empty()));
        Assertions.assertTrue(0 < one.compareTo(Cells.factory.empty()));
        Assertions.assertTrue(0 < minusOne.compareTo(Cells.factory.empty()));

        Assertions.assertTrue(0 < zero.compareTo(Cells.factory.of("-1")));
        Assertions.assertTrue(0 > zero.compareTo(Cells.factory.of("1")));

        Assertions.assertTrue(0 < one.compareTo(Cells.factory.of("0")));
        Assertions.assertTrue(0 > one.compareTo(Cells.factory.of("2")));

        Assertions.assertTrue(0 > minusOne.compareTo(Cells.factory.of("2")));
        Assertions.assertTrue(0 > minusOne.compareTo(Cells.factory.of("0")));
    }

    @Test
    void compareToNegative() {
        final StringCell cell = Cells.factory.of("0");

        Assertions.assertThrows(NullPointerException.class, () -> cell.compareTo(null));

        Assertions.assertThrows(CellsTypeMismatchException.class, () -> cell.compareTo(Cells.factory.of(true)));
        Assertions.assertThrows(CellsTypeMismatchException.class, () -> cell.compareTo(Cells.factory.of(0L)));
        Assertions.assertThrows(CellsTypeMismatchException.class, () -> cell.compareTo(Cells.factory.of(0.0)));
    }
}
