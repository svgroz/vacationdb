package org.svgroz.vacationdb.datastore.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.svgroz.vacationdb.datastore.exception.CellsTypeMismatchException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Simon Grozovsky svgroz@outlook.com
 */
class BooleanCellTest {

    @Test
    void constructorArgsValidation() {
        Assertions.assertThrows(NullPointerException.class, () -> new BooleanCell(null));
    }

    @Test
    void supportedType() {
        Assertions.assertEquals(Boolean.class, new BooleanCell(true).supportedType());
    }

    @Test
    void compareToPositive() {
        final BooleanCell cellTrue = new BooleanCell(true);
        final BooleanCell cellFalse = new BooleanCell(false);

        Assertions.assertEquals(1, cellTrue.compareTo(cellFalse));
        Assertions.assertEquals(-1, cellFalse.compareTo(cellTrue));
        Assertions.assertEquals(0, cellTrue.compareTo(new BooleanCell(true)));
        Assertions.assertEquals(0, cellFalse.compareTo(new BooleanCell(false)));
        Assertions.assertEquals(1, cellTrue.compareTo(EmptyCell.getInstance()));
        Assertions.assertEquals(1, cellFalse.compareTo(EmptyCell.getInstance()));
    }

    @Test
    void compareToNegative() {
        final BooleanCell cellTrue = new BooleanCell(true);
        final BooleanCell cellFalse = new BooleanCell(false);
        final LongCell longCell = new LongCell(0L);
        final DoubleCell doubleCell = new DoubleCell(0.0);
        final StringCell stringCell = new StringCell("0");

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
