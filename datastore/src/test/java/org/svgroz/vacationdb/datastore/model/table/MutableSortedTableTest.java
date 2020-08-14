package org.svgroz.vacationdb.datastore.model.table;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.svgroz.vacationdb.datastore.exception.ColumnsContainsNullException;
import org.svgroz.vacationdb.datastore.exception.ColumnsDoesNotContainsKeysException;
import org.svgroz.vacationdb.datastore.exception.EmptyColumnsException;
import org.svgroz.vacationdb.datastore.model.DataType;
import org.svgroz.vacationdb.datastore.model.column.Column;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class MutableSortedTableTest {
    @Test
    void tableArgumentsValidationPositive() {
        final Column validColumn1 = Column.of("name1", DataType.BOOLEAN, true);
        final Column validColumn2 = Column.of("name2", DataType.BOOLEAN, false);
        Assertions.assertDoesNotThrow(() -> new MutableSortedTable("foo", List.of(validColumn1, validColumn2)));
    }

    @Test
    void tableArgumentsValidationNegative() {
        final Column validColumn = Column.of("name", DataType.BOOLEAN, false);
        Assertions.assertThrows(NullPointerException.class, () -> new MutableSortedTable(null, List.of(validColumn)));
        Assertions.assertThrows(NullPointerException.class, () -> new MutableSortedTable("name", null));
        Assertions.assertThrows(EmptyColumnsException.class, () -> new MutableSortedTable("name", Collections.emptyList()));
        Assertions.assertThrows(ColumnsContainsNullException.class, () -> new MutableSortedTable("foo", Arrays.asList(validColumn, null)));
        Assertions.assertThrows(ColumnsDoesNotContainsKeysException.class, () -> new MutableSortedTable("foo", Arrays.asList(validColumn)));
    }
}
