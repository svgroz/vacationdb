package org.svgroz.vacationdb.datastore.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.svgroz.vacationdb.datastore.exception.ColumnsContainsNullException;
import org.svgroz.vacationdb.datastore.exception.EmptyColumnsException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class TableTest {
    @Test
    void tableArgumentsValidationPositive() {
        final Column validColumn = new Column("name", BooleanCell.class);
        Assertions.assertDoesNotThrow(() -> new Table("foo", List.of(validColumn)));
    }

    @Test
    void tableArgumentsValidationNegative() {
        final Column validColumn = new Column("name", BooleanCell.class);
        Assertions.assertThrows(NullPointerException.class, () -> new Table(null, List.of(validColumn)));
        Assertions.assertThrows(NullPointerException.class, () -> new Table("name", null));
        Assertions.assertThrows(EmptyColumnsException.class, () -> new Table("name", Collections.emptyList()));
        Assertions.assertThrows(ColumnsContainsNullException.class, () -> new Table("foo", Arrays.asList(validColumn, null)));
    }
}
