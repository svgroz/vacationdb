package org.svgroz.vacationdb.datastore.model.cell;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.svgroz.vacationdb.datastore.model.DataType;
import org.svgroz.vacationdb.datastore.model.column.Column;

class DefaultColumnTest {
    @Test
    void testNullArgsValidationPositive() {
        Assertions.assertDoesNotThrow(() -> Column.of("name", DataType.BOOLEAN, false));
        Assertions.assertDoesNotThrow(() -> Column.of("name", DataType.LONG, false));
        Assertions.assertDoesNotThrow(() -> Column.of("name", DataType.DOUBLE, false));
        Assertions.assertDoesNotThrow(() -> Column.of("name", DataType.STRING, false));
    }

    @Test
    void testNullArgsValidationNegative() {
        Assertions.assertThrows(NullPointerException.class, () -> Column.of(null, null, false));
        Assertions.assertThrows(NullPointerException.class, () -> Column.of("name", null, false));
        Assertions.assertThrows(NullPointerException.class, () -> Column.of(null, DataType.EMPTY, false));
    }
}
