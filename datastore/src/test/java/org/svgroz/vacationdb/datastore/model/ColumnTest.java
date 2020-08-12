package org.svgroz.vacationdb.datastore.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ColumnTest {
    @Test
    void testNullArgsValidationPositive() {
        Assertions.assertDoesNotThrow(() -> new Column("name", BooleanCell.class, false));
        Assertions.assertDoesNotThrow(() -> new Column("name", LongCell.class, false));
        Assertions.assertDoesNotThrow(() -> new Column("name", DoubleCell.class, false));
        Assertions.assertDoesNotThrow(() -> new Column("name", StringCell.class, false));
    }

    @Test
    void testNullArgsValidationNegative() {
        Assertions.assertThrows(NullPointerException.class, () -> new Column(null, null, false));
        Assertions.assertThrows(NullPointerException.class, () -> new Column("name", null, false));
        Assertions.assertThrows(NullPointerException.class, () -> new Column(null, EmptyCell.class, false));
    }
}
