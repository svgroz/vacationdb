package org.svgroz.vacationdb.datastore.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ColumnTest {
    @Test
    void testNullArgsValidationPositive() {
        Assertions.assertDoesNotThrow(() -> new Column("name", ColumnType.BOOLEAN));
        Assertions.assertDoesNotThrow(() -> new Column("name", ColumnType.LONG));
        Assertions.assertDoesNotThrow(() -> new Column("name", ColumnType.DOUBLE));
        Assertions.assertDoesNotThrow(() -> new Column("name", ColumnType.STRING));
    }

    @Test
    void testNullArgsValidationNegative() {
        Assertions.assertThrows(NullPointerException.class, () -> new Column(null, null));
        Assertions.assertThrows(NullPointerException.class, () -> new Column("name", null));
        Assertions.assertThrows(NullPointerException.class, () -> new Column(null, ColumnType.BOOLEAN));
    }
}
