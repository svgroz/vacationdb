package org.svgroz.vacationdb.datastore.comparator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.svgroz.vacationdb.datastore.exception.DifferentCellsTypesException;
import org.svgroz.vacationdb.datastore.exception.RowsDifferentLengthsException;
import org.svgroz.vacationdb.datastore.model.Cell;
import org.svgroz.vacationdb.datastore.model.ColumnType;
import org.svgroz.vacationdb.datastore.model.Row;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.svgroz.vacationdb.datastore.model.ColumnType.*;

class RowComparatorTest {

    @Test
    void comparePositive() {
        RowComparator rowComparator = new RowComparator();

        Assertions.assertEquals(
                0,
                rowComparator.compare(
                        new Row(
                                List.of(
                                        new Cell(BOOLEAN, true),
                                        new Cell(LONG, 1L),
                                        new Cell(DOUBLE, 1.0),
                                        new Cell(STRING, "1")
                                )
                        ),
                        new Row(
                                List.of(
                                        new Cell(BOOLEAN, true),
                                        new Cell(LONG, 1L),
                                        new Cell(DOUBLE, 1.0),
                                        new Cell(STRING, "1")
                                )
                        )
                )
        );

        Assertions.assertEquals(
                1,
                rowComparator.compare(
                        new Row(
                                List.of(
                                        new Cell(BOOLEAN, true),
                                        new Cell(LONG, 1L),
                                        new Cell(DOUBLE, 1.0),
                                        new Cell(STRING, "1")
                                )
                        ),
                        new Row(
                                List.of(
                                        new Cell(BOOLEAN, false),
                                        new Cell(LONG, 1L),
                                        new Cell(DOUBLE, 1.0),
                                        new Cell(STRING, "1")
                                )
                        )
                )
        );

        Assertions.assertEquals(
                1,
                rowComparator.compare(
                        new Row(
                                List.of(
                                        new Cell(BOOLEAN, true),
                                        new Cell(LONG, 1L),
                                        new Cell(DOUBLE, 1.0),
                                        new Cell(STRING, "1")
                                )
                        ),
                        new Row(
                                List.of(
                                        new Cell(BOOLEAN, true),
                                        new Cell(LONG, 0L),
                                        new Cell(DOUBLE, 1.0),
                                        new Cell(STRING, "1")
                                )
                        )
                )
        );

        Assertions.assertEquals(
                1,
                rowComparator.compare(
                        new Row(
                                List.of(
                                        new Cell(BOOLEAN, true),
                                        new Cell(LONG, 1L),
                                        new Cell(DOUBLE, 1.0),
                                        new Cell(STRING, "1")
                                )
                        ),
                        new Row(
                                List.of(
                                        new Cell(BOOLEAN, true),
                                        new Cell(LONG, 1L),
                                        new Cell(DOUBLE, 0.0),
                                        new Cell(STRING, "1")
                                )
                        )
                )
        );

        Assertions.assertEquals(
                1,
                rowComparator.compare(
                        new Row(
                                List.of(
                                        new Cell(BOOLEAN, true),
                                        new Cell(LONG, 1L),
                                        new Cell(DOUBLE, 1.0),
                                        new Cell(STRING, "1")
                                )
                        ),
                        new Row(
                                List.of(
                                        new Cell(BOOLEAN, true),
                                        new Cell(LONG, 1L),
                                        new Cell(DOUBLE, 1.0),
                                        new Cell(STRING, "0")
                                )
                        )
                )
        );

        Assertions.assertEquals(
                1,
                rowComparator.compare(
                        new Row(
                                List.of(
                                        new Cell(BOOLEAN, true),
                                        new Cell(LONG, 1L),
                                        new Cell(DOUBLE, 1.0),
                                        new Cell(STRING, "1")
                                )
                        ),
                        new Row(
                                List.of(
                                        new Cell(BOOLEAN, true),
                                        new Cell(LONG, 1L),
                                        new Cell(DOUBLE, 1.0),
                                        new Cell(STRING, null)
                                )
                        )
                )
        );

    }

    @Test
    void comparatorNegative() {
        RowComparator rowComparator = new RowComparator();

        Assertions.assertThrows(
                RowsDifferentLengthsException.class,
                () -> rowComparator.compare(
                        new Row(
                                List.of(
                                        new Cell(BOOLEAN, true),
                                        new Cell(LONG, 1L),
                                        new Cell(DOUBLE, 1.0),
                                        new Cell(STRING, "1")
                                )
                        ),
                        new Row(
                                List.of(
                                        new Cell(BOOLEAN, true),
                                        new Cell(LONG, 1L),
                                        new Cell(DOUBLE, 1.0)
                                )
                        )
                )
        );

        Assertions.assertThrows(
                DifferentCellsTypesException.class,
                () -> rowComparator.compare(
                        new Row(
                                List.of(
                                        new Cell(BOOLEAN, true),
                                        new Cell(LONG, 1L),
                                        new Cell(DOUBLE, 1.0),
                                        new Cell(STRING, "1")
                                )
                        ),
                        new Row(
                                List.of(
                                        new Cell(LONG, 1L),
                                        new Cell(BOOLEAN, true),
                                        new Cell(DOUBLE, 1.0),
                                        new Cell(STRING, "1")
                                )
                        )
                )
        );
    }
}