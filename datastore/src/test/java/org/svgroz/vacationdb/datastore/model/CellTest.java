package org.svgroz.vacationdb.datastore.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.svgroz.vacationdb.datastore.exception.ColumnTypeValueTypeMismatch;

import static org.svgroz.vacationdb.datastore.model.ColumnType.*;

class CellTest {
    @Test
    void booleanColumnTypeValidationPositive() {
        Assertions.assertDoesNotThrow(() -> new Cell<>(BOOLEAN, Boolean.TRUE));

        Assertions.assertDoesNotThrow(() -> new Cell<>(BOOLEAN, null));
    }

    @Test
    void booleanColumnTypeValidationNegative() {
        Assertions.assertThrows(NullPointerException.class, () -> new Cell<>(null, null));

        Assertions.assertThrows(ColumnTypeValueTypeMismatch.class, () -> new Cell<>(BOOLEAN, Long.valueOf("1")));

        Assertions.assertThrows(ColumnTypeValueTypeMismatch.class, () -> new Cell<>(BOOLEAN, Double.valueOf("1.1")));

        Assertions.assertThrows(ColumnTypeValueTypeMismatch.class, () -> new Cell<>(BOOLEAN, "1"));
    }

    @Test
    void longColumnTypeValidationPositive() {
        Assertions.assertDoesNotThrow(() -> new Cell<>(LONG, 1L));

        Assertions.assertDoesNotThrow(() -> new Cell<>(LONG, null));
    }

    @Test
    void longColumnTypeValidationNegative() {
        Assertions.assertThrows(NullPointerException.class, () -> new Cell<>(null, null));

        Assertions.assertThrows(ColumnTypeValueTypeMismatch.class, () -> new Cell<>(LONG, Boolean.TRUE));

        Assertions.assertThrows(ColumnTypeValueTypeMismatch.class, () -> new Cell<>(LONG, Double.valueOf("1.1")));

        Assertions.assertThrows(ColumnTypeValueTypeMismatch.class, () -> new Cell<>(LONG, "1"));
    }

    @Test
    void doubleColumnTypeValidationPositive() {
        Assertions.assertDoesNotThrow(() -> new Cell<>(DOUBLE, 1.1));

        Assertions.assertDoesNotThrow(() -> new Cell<>(DOUBLE, null));
    }

    @Test
    void doubleColumnTypeValidationNegative() {
        Assertions.assertThrows(NullPointerException.class, () -> new Cell<>(null, null));

        Assertions.assertThrows(ColumnTypeValueTypeMismatch.class, () -> new Cell<>(DOUBLE, Boolean.TRUE));

        Assertions.assertThrows(ColumnTypeValueTypeMismatch.class, () -> new Cell<>(DOUBLE, Long.valueOf("1")));

        Assertions.assertThrows(ColumnTypeValueTypeMismatch.class, () -> new Cell<>(DOUBLE, "1"));
    }

    @Test
    void stringColumnTypeValidationPositive() {
        Assertions.assertDoesNotThrow(() -> new Cell<>(STRING, "1"));

        Assertions.assertDoesNotThrow(() -> new Cell<>(STRING, null));
    }

    @Test
    void stringColumnTypeValidationNegative() {
        Assertions.assertThrows(NullPointerException.class, () -> new Cell<>(null, null));

        Assertions.assertThrows(ColumnTypeValueTypeMismatch.class, () -> new Cell<>(STRING, Boolean.TRUE));

        Assertions.assertThrows(ColumnTypeValueTypeMismatch.class, () -> new Cell<>(STRING, Long.valueOf("1")));

        Assertions.assertThrows(ColumnTypeValueTypeMismatch.class, () -> new Cell<>(STRING, Double.valueOf("1.1")));
    }
}
