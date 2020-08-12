package org.svgroz.vacationdb.datastore.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.svgroz.vacationdb.datastore.exception.ColumnsContainsNullException;
import org.svgroz.vacationdb.datastore.exception.ColumnsDoesNotContainsKeysException;
import org.svgroz.vacationdb.datastore.exception.EmptyColumnsException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class TableTest {
    @Test
    void tableArgumentsValidationPositive() {
        final Column validColumn1 = new Column("name1", BooleanCell.class, true);
        final Column validColumn2 = new Column("name2", BooleanCell.class, false);
        Assertions.assertDoesNotThrow(() -> new Table("foo", List.of(validColumn1, validColumn2)));
    }

    @Test
    void tableArgumentsValidationNegative() {
        final Column validColumn = new Column("name", BooleanCell.class, false);
        Assertions.assertThrows(NullPointerException.class, () -> new Table(null, List.of(validColumn)));
        Assertions.assertThrows(NullPointerException.class, () -> new Table("name", null));
        Assertions.assertThrows(EmptyColumnsException.class, () -> new Table("name", Collections.emptyList()));
        Assertions.assertThrows(ColumnsContainsNullException.class, () -> new Table("foo", Arrays.asList(validColumn, null)));
        Assertions.assertThrows(ColumnsDoesNotContainsKeysException.class, () -> new Table("foo", Arrays.asList(validColumn)));
    }
}
