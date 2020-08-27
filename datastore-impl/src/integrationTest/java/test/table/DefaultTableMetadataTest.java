package test.table;

import org.eclipse.collections.api.factory.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.svgroz.vacationdb.datastore.api.exception.ColumnsContainsNullException;
import org.svgroz.vacationdb.datastore.api.exception.ColumnsContainsSameNamesException;
import org.svgroz.vacationdb.datastore.api.exception.ColumnsSupposedToBeOnlyInBeginning;
import org.svgroz.vacationdb.datastore.api.exception.EmptyColumnsException;
import org.svgroz.vacationdb.datastore.api.model.column.Column;
import org.svgroz.vacationdb.datastore.api.model.column.Columns;
import org.svgroz.vacationdb.datastore.api.model.table.TableMetadata;
import org.svgroz.vacationdb.datastore.api.model.table.Tables;

import java.util.ArrayList;

class DefaultTableMetadataTest {

    @Test
    void constructorPositive() {
        TableMetadata defaultTableMetadata = Assertions.assertDoesNotThrow(
                () -> Tables.tableMetadataFactory.of(
                        "TABLEONE",
                        Lists.immutable.of(
                                Columns.key.booleanOf("FOO"),
                                Columns.key.longOf("BAR"),
                                Columns.key.longOf("BIZ")
                        )
                )
        );

        Assertions.assertNotNull(defaultTableMetadata);
        Assertions.assertEquals(3, defaultTableMetadata.columnsCount());
        Column column1 = Assertions.assertDoesNotThrow(() -> defaultTableMetadata.getColumnByIndex(0));
        Assertions.assertNotNull(column1);
        Assertions.assertEquals("FOO", column1.getName());
        Column column2 = Assertions.assertDoesNotThrow(() -> defaultTableMetadata.getColumnByIndex(1));
        Assertions.assertEquals("BAR", column2.getName());
    }

    @Test
    void constructorNegative() {
        Assertions.assertThrows(NullPointerException.class, () -> Tables.tableMetadataFactory.of(null, null));
        Assertions.assertThrows(NullPointerException.class, () -> Tables.tableMetadataFactory.of("TABLENAME", null));
        Assertions.assertThrows(EmptyColumnsException.class, () -> Tables.tableMetadataFactory.of("TABLENAME", Lists.immutable.empty()));
        Assertions.assertThrows(
                ColumnsContainsNullException.class,
                () -> {
                    final ArrayList<Column> columns = new ArrayList<>();
                    columns.add(null);
                    Tables.tableMetadataFactory.of("TABLENAME", Lists.immutable.ofAll(columns));
                }
        );
        Assertions.assertThrows(
                ColumnsContainsSameNamesException.class,
                () -> Tables.tableMetadataFactory.of(
                        "TABLENAME",
                        Lists.immutable.of(
                                Columns.key.booleanOf("FOO"),
                                Columns.key.booleanOf("FOO")
                        )
                )
        );

        Assertions.assertThrows(
                ColumnsSupposedToBeOnlyInBeginning.class,
                () -> Tables.tableMetadataFactory.of(
                        "TABLENAME",
                        Lists.immutable.of(
                                Columns.key.booleanOf("Z"),
                                Columns.regular.booleanOf("C"),
                                Columns.key.booleanOf("U")
                        )
                )
        );
    }
}
