package test.cell;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.svgroz.vacationdb.datastore.api.model.column.Columns;

class DefaultKeyColumnFactoryTest {
    @Test
    void testNullArgsValidationPositive() {
        Assertions.assertDoesNotThrow(() -> Columns.key.booleanOf("name"));
        Assertions.assertDoesNotThrow(() -> Columns.key.longOf("name"));
        Assertions.assertDoesNotThrow(() -> Columns.key.doubleOf("name"));
        Assertions.assertDoesNotThrow(() -> Columns.key.stringOf("name"));
    }

    @Test
    void testNullArgsValidationNegative() {
        Assertions.assertThrows(NullPointerException.class, () -> Columns.key.booleanOf(null));
        Assertions.assertThrows(NullPointerException.class, () -> Columns.key.longOf(null));
        Assertions.assertThrows(NullPointerException.class, () -> Columns.key.doubleOf(null));
        Assertions.assertThrows(NullPointerException.class, () -> Columns.key.stringOf(null));
    }
}
