package org.svgroz.vacationdb.datastore.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ColumnTest {
    @Test
    void testNullArgsValidationPositive() {
        Assertions.assertDoesNotThrow(() -> new Column("name", BooleanCell.class));
        Assertions.assertDoesNotThrow(() -> new Column("name", LongCell.class));
        Assertions.assertDoesNotThrow(() -> new Column("name", DoubleCell.class));
        Assertions.assertDoesNotThrow(() -> new Column("name", StringCell.class));
    }

    @Test
    void testNullArgsValidationNegative() {
        Assertions.assertThrows(NullPointerException.class, () -> new Column(null, null));
        Assertions.assertThrows(NullPointerException.class, () -> new Column("name", null));
        Assertions.assertThrows(NullPointerException.class, () -> new Column(null, EmptyCell.class));
    }
}
