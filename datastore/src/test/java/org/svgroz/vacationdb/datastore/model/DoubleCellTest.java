package org.svgroz.vacationdb.datastore.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.svgroz.vacationdb.datastore.exception.CellsTypeMismatchException;

/**
 * @author Simon Grozovsky svgroz@outlook.com
 */
class DoubleCellTest {

    @Test
    void constructorArgsValidation() {
        Assertions.assertThrows(NullPointerException.class, () -> new DoubleCell(null));
    }

    @Test
    void getValue() {
        Assertions.assertNotNull(new DoubleCell(0.0).getValue());
        Assertions.assertEquals(0.0, new DoubleCell(0.0).getValue());
    }

    @Test
    void supportedType() {
        Assertions.assertEquals(Double.class, new DoubleCell(0.0).supportedType());
    }

    @Test
    void compareToPositive() {
        final DoubleCell zero = new DoubleCell(0.0);
        final DoubleCell one = new DoubleCell(1.0);
        final DoubleCell minusOne = new DoubleCell(-1.0);

        Assertions.assertEquals(0, zero.compareTo(new DoubleCell(0.0)));
        Assertions.assertEquals(0, one.compareTo(new DoubleCell(1.0)));
        Assertions.assertEquals(0, minusOne.compareTo(new DoubleCell(-1.0)));

        Assertions.assertTrue(0 < zero.compareTo(EmptyCell.getInstance()));
        Assertions.assertTrue(0 < one.compareTo(EmptyCell.getInstance()));
        Assertions.assertTrue(0 < minusOne.compareTo(EmptyCell.getInstance()));

        Assertions.assertTrue(0 < zero.compareTo(new DoubleCell(-1.0)));
        Assertions.assertTrue(0 > zero.compareTo(new DoubleCell(1.0)));

        Assertions.assertTrue(0 < one.compareTo(new DoubleCell(0.0)));
        Assertions.assertTrue(0 > one.compareTo(new DoubleCell(2.0)));

        Assertions.assertTrue(0 < minusOne.compareTo(new DoubleCell(-2.0)));
        Assertions.assertTrue(0 > minusOne.compareTo(new DoubleCell(0.0)));
    }

    @Test
    void compareToNegative() {
        final DoubleCell cell = new DoubleCell(0.0);

        Assertions.assertThrows(NullPointerException.class, () -> cell.compareTo(null));

        Assertions.assertThrows(CellsTypeMismatchException.class, () -> cell.compareTo(new BooleanCell(true)));
        Assertions.assertThrows(CellsTypeMismatchException.class, () -> cell.compareTo(new LongCell(0L)));
        Assertions.assertThrows(CellsTypeMismatchException.class, () -> cell.compareTo(new StringCell("")));
    }
}
