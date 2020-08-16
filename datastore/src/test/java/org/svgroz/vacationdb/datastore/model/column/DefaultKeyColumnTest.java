package org.svgroz.vacationdb.datastore.model.column;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.svgroz.vacationdb.datastore.model.DataType;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Simon Grozovsky svgroz@outlook.com
 */
class DefaultKeyColumnTest {
    @Test
    void constructorArgumentsPositive() {
        Assertions.assertDoesNotThrow(() -> new DefaultKeyColumn("foo", DataType.BOOLEAN));
    }

    @Test
    void constructorArgumentsNegative() {
        Assertions.assertThrows(NullPointerException.class, () -> new DefaultKeyColumn(null, null));
        Assertions.assertThrows(NullPointerException.class, () -> new DefaultKeyColumn("foo", null));
        Assertions.assertThrows(NullPointerException.class, () -> new DefaultKeyColumn(null, DataType.BOOLEAN));
    }

    @Test
    void getName() {
        Assertions.assertEquals("foo", new DefaultKeyColumn("foo", DataType.BOOLEAN).getName());
    }

    @Test
    void getType() {
        Assertions.assertEquals(DataType.BOOLEAN, new DefaultKeyColumn("foo", DataType.BOOLEAN).getType());
    }
}