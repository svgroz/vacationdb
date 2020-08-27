package test.cell;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.svgroz.vacationdb.datastore.api.model.DataType;
import org.svgroz.vacationdb.datastore.api.model.column.Columns;

class DefaultColumnTest {
    @Test
    void testNullArgsValidationPositive() {
        Assertions.assertDoesNotThrow(() -> Columns.factory.of("name", DataType.BOOLEAN));
        Assertions.assertDoesNotThrow(() -> Columns.factory.of("name", DataType.LONG));
        Assertions.assertDoesNotThrow(() -> Columns.factory.of("name", DataType.DOUBLE));
        Assertions.assertDoesNotThrow(() -> Columns.factory.of("name", DataType.STRING));
    }

    @Test
    void testNullArgsValidationNegative() {
        Assertions.assertThrows(NullPointerException.class, () -> Columns.factory.of(null, null));
        Assertions.assertThrows(NullPointerException.class, () -> Columns.factory.of("name", null));
        Assertions.assertThrows(NullPointerException.class, () -> Columns.factory.of(null, DataType.EMPTY));
    }
}
