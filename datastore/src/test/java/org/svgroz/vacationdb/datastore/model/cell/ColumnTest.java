package org.svgroz.vacationdb.datastore.model.cell;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.svgroz.vacationdb.datastore.model.DataType;
import org.svgroz.vacationdb.datastore.model.column.Column;

class ColumnTest {
    @Test
    void testNullArgsValidationPositive() {
        Assertions.assertDoesNotThrow(() -> new Column("name", DataType.BOOLEAN, false));
        Assertions.assertDoesNotThrow(() -> new Column("name", DataType.LONG, false));
        Assertions.assertDoesNotThrow(() -> new Column("name", DataType.DOUBLE, false));
        Assertions.assertDoesNotThrow(() -> new Column("name", DataType.STRING, false));
    }

    @Test
    void testNullArgsValidationNegative() {
        Assertions.assertThrows(NullPointerException.class, () -> new Column(null, null, false));
        Assertions.assertThrows(NullPointerException.class, () -> new Column("name", null, false));
        Assertions.assertThrows(NullPointerException.class, () -> new Column(null, DataType.EMPTY, false));
    }
}
