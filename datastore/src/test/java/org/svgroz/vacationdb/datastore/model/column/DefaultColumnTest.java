package org.svgroz.vacationdb.datastore.model.column;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.svgroz.vacationdb.datastore.model.DataType;

/**
 * @author Simon Grozovsky svgroz@outlook.com
 */
class DefaultColumnTest {
    @Test
    void constructorArgumentsPositive() {
        Assertions.assertDoesNotThrow(() -> new DefaultColumn("foo", DataType.BOOLEAN));
    }

    @Test
    void constructorArgumentsNegative() {
        Assertions.assertThrows(NullPointerException.class, () -> new DefaultColumn(null, null));
        Assertions.assertThrows(NullPointerException.class, () -> new DefaultColumn("foo", null));
        Assertions.assertThrows(NullPointerException.class, () -> new DefaultColumn(null, DataType.BOOLEAN));
    }

    @Test
    void getName() {
        Assertions.assertEquals("foo", new DefaultColumn("foo", DataType.BOOLEAN).getName());
    }

    @Test
    void getType() {
        Assertions.assertEquals(DataType.BOOLEAN, new DefaultColumn("foo", DataType.BOOLEAN).getType());
    }
}