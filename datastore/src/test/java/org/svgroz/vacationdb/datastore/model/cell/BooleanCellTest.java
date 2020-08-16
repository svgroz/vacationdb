package org.svgroz.vacationdb.datastore.model.cell;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.svgroz.vacationdb.datastore.exception.CellsTypeMismatchException;
import org.svgroz.vacationdb.datastore.model.DataType;

/**
 * Simon Grozovsky svgroz@outlook.com
 */
class BooleanCellTest {

    @Test
    void supportedType() {
        Assertions.assertEquals(DataType.BOOLEAN, BooleanCell.INSTANCE_TRUE.supportedType());
    }

    @Test
    void compareToPositive() {
        final BooleanCell cellTrue = BooleanCell.INSTANCE_TRUE;
        final BooleanCell cellFalse = BooleanCell.INSTANCE_FALSE;

        Assertions.assertEquals(1, cellTrue.compareTo(cellFalse));
        Assertions.assertEquals(-1, cellFalse.compareTo(cellTrue));
        Assertions.assertEquals(0, cellTrue.compareTo(BooleanCell.INSTANCE_TRUE));
        Assertions.assertEquals(0, cellFalse.compareTo(BooleanCell.INSTANCE_FALSE));
        Assertions.assertEquals(1, cellTrue.compareTo(EmptyCell.getInstance()));
        Assertions.assertEquals(1, cellFalse.compareTo(EmptyCell.getInstance()));
    }

    @Test
    void compareToNegative() {
        final BooleanCell cellTrue = BooleanCell.INSTANCE_TRUE;
        final BooleanCell cellFalse = BooleanCell.INSTANCE_FALSE;
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
