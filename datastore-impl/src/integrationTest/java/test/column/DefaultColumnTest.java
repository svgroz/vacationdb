package test.column;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.svgroz.vacationdb.datastore.api.model.DataType;
import org.svgroz.vacationdb.datastore.api.model.column.Columns;

/**
 * @author Simon Grozovsky svgroz@outlook.com
 */
class DefaultColumnTest {
    @Test
    void constructorArgumentsPositive() {
        Assertions.assertDoesNotThrow(() -> Columns.factory.of("foo", DataType.BOOLEAN));
    }

    @Test
    void constructorArgumentsNegative() {
        Assertions.assertThrows(NullPointerException.class, () -> Columns.factory.of(null, null));
        Assertions.assertThrows(NullPointerException.class, () -> Columns.factory.of("foo", null));
        Assertions.assertThrows(NullPointerException.class, () -> Columns.factory.of(null, DataType.BOOLEAN));
    }

    @Test
    void getName() {
        Assertions.assertEquals("foo", Columns.factory.of("foo", DataType.BOOLEAN).getName());
    }

    @Test
    void getType() {
        Assertions.assertEquals(DataType.BOOLEAN, Columns.factory.of("foo", DataType.BOOLEAN).getType());
    }
}