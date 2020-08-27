package test.column;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.svgroz.vacationdb.datastore.api.model.DataType;
import org.svgroz.vacationdb.datastore.api.model.column.Columns;

/**
 * @author Simon Grozovsky svgroz@outlook.com
 */
class DefaultKeyColumnTest {
    @Test
    void constructorArgumentsPositive() {
        Assertions.assertDoesNotThrow(() -> Columns.factory.keyOf("foo", DataType.BOOLEAN));
    }

    @Test
    void constructorArgumentsNegative() {
        Assertions.assertThrows(NullPointerException.class, () -> Columns.factory.keyOf(null, null));
        Assertions.assertThrows(NullPointerException.class, () -> Columns.factory.keyOf("foo", null));
        Assertions.assertThrows(NullPointerException.class, () -> Columns.factory.keyOf(null, DataType.BOOLEAN));
    }

    @Test
    void getName() {
        Assertions.assertEquals("foo", Columns.factory.keyOf("foo", DataType.BOOLEAN).getName());
    }

    @Test
    void getType() {
        Assertions.assertEquals(DataType.BOOLEAN, Columns.factory.keyOf("foo", DataType.BOOLEAN).getType());
    }
}
