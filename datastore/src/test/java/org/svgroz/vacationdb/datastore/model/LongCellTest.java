package org.svgroz.vacationdb.datastore.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.svgroz.vacationdb.datastore.exception.CellsTypeMismatchException;

/**
 * @author Simon Grozovsky svgroz@outlook.com
 */
class LongCellTest {

    @Test
    void constructorArgsValidation() {
        Assertions.assertThrows(NullPointerException.class, () -> new LongCell(null));
    }

    @Test
    void getValue() {
        Assertions.assertNotNull(new LongCell(0L).getValue());
        Assertions.assertEquals(0L, new LongCell(0L).getValue());
    }

    @Test
    void supportedType() {
        Assertions.assertEquals(Long.class, new LongCell(0L).supportedType());
    }

    @Test
    void compareToPositive() {
        final LongCell zero = new LongCell(0L);
        final LongCell one = new LongCell(1L);
        final LongCell minusOne = new LongCell(-1L);

        Assertions.assertEquals(0, zero.compareTo(new LongCell(0L)));
        Assertions.assertEquals(0, one.compareTo(new LongCell(1L)));
        Assertions.assertEquals(0, minusOne.compareTo(new LongCell(-1L)));

        Assertions.assertTrue(0 < zero.compareTo(EmptyCell.getInstance()));
        Assertions.assertTrue(0 < one.compareTo(EmptyCell.getInstance()));
        Assertions.assertTrue(0 < minusOne.compareTo(EmptyCell.getInstance()));

        Assertions.assertTrue(0 < zero.compareTo(new LongCell(-1L)));
        Assertions.assertTrue(0 > zero.compareTo(new LongCell(1L)));

        Assertions.assertTrue(0 < one.compareTo(new LongCell(0L)));
        Assertions.assertTrue(0 > one.compareTo(new LongCell(2L)));

        Assertions.assertTrue(0 < minusOne.compareTo(new LongCell(-2L)));
        Assertions.assertTrue(0 > minusOne.compareTo(new LongCell(0L)));
    }

    @Test
    void compareToNegative() {
        final LongCell cell = new LongCell(0L);

        Assertions.assertThrows(NullPointerException.class, () -> cell.compareTo(null));

        Assertions.assertThrows(CellsTypeMismatchException.class, () -> cell.compareTo(new BooleanCell(true)));
        Assertions.assertThrows(CellsTypeMismatchException.class, () -> cell.compareTo(new DoubleCell(0.0)));
        Assertions.assertThrows(CellsTypeMismatchException.class, () -> cell.compareTo(new StringCell("")));
    }
}
