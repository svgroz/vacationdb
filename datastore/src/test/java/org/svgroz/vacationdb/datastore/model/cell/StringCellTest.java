package org.svgroz.vacationdb.datastore.model.cell;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.svgroz.vacationdb.datastore.exception.CellsTypeMismatchException;
import org.svgroz.vacationdb.datastore.model.DataType;

/**
 * @author Simon Grozovsky svgroz@outlook.com
 */
class StringCellTest {

    @Test
    void constructorArgsValidation() {
        Assertions.assertThrows(NullPointerException.class, () -> new StringCell(null));
    }

    @Test
    void getValue() {
        Assertions.assertNotNull(new StringCell("0").getValue());
        Assertions.assertEquals("0", new StringCell("0").getValue());
    }

    @Test
    void supportedType() {
        Assertions.assertEquals(DataType.STRING, new StringCell("0").supportedType());
    }

    @Test
    void compareToPositive() {
        final StringCell zero = new StringCell("0");
        final StringCell one = new StringCell("1");
        final StringCell minusOne = new StringCell("-1");

        Assertions.assertEquals(0, zero.compareTo(new StringCell("0")));
        Assertions.assertEquals(0, one.compareTo(new StringCell("1")));
        Assertions.assertEquals(0, minusOne.compareTo(new StringCell("-1")));

        Assertions.assertTrue(0 < zero.compareTo(EmptyCell.getInstance()));
        Assertions.assertTrue(0 < one.compareTo(EmptyCell.getInstance()));
        Assertions.assertTrue(0 < minusOne.compareTo(EmptyCell.getInstance()));

        Assertions.assertTrue(0 < zero.compareTo(new StringCell("-1")));
        Assertions.assertTrue(0 > zero.compareTo(new StringCell("1")));

        Assertions.assertTrue(0 < one.compareTo(new StringCell("0")));
        Assertions.assertTrue(0 > one.compareTo(new StringCell("2")));

        Assertions.assertTrue(0 > minusOne.compareTo(new StringCell("2")));
        Assertions.assertTrue(0 > minusOne.compareTo(new StringCell("0")));
    }

    @Test
    void compareToNegative() {
        final StringCell cell = new StringCell("0");

        Assertions.assertThrows(NullPointerException.class, () -> cell.compareTo(null));

        Assertions.assertThrows(CellsTypeMismatchException.class, () -> cell.compareTo(Cell.of(true)));
        Assertions.assertThrows(CellsTypeMismatchException.class, () -> cell.compareTo(Cell.of(0L)));
        Assertions.assertThrows(CellsTypeMismatchException.class, () -> cell.compareTo(Cell.of(0.0)));
    }
}
