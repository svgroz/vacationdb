package org.svgroz.vacationdb.datastore.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.svgroz.vacationdb.datastore.exception.CellsContainsNullException;
import org.svgroz.vacationdb.datastore.exception.CellsTypeMismatchException;
import org.svgroz.vacationdb.datastore.exception.EmptyCellsException;
import org.svgroz.vacationdb.datastore.exception.RowsDifferentLengthsException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class RowTest {
    @Test
    void rowArgumentValidationPositive() {
        Assertions.assertDoesNotThrow(() -> new Row(
                        List.of(
                                new BooleanCell(true)
                        )
                )
        );
        Assertions.assertDoesNotThrow(() -> new Row(
                        List.of(
                                new BooleanCell(true),
                                EmptyCell.getInstance()
                        )
                )
        );
    }

    @Test
    void rowArgumentValidationNegative() {
        Assertions.assertThrows(NullPointerException.class, () -> new Row(null));
        Assertions.assertThrows(EmptyCellsException.class, () -> new Row(Collections.emptyList()));
        Assertions.assertThrows(CellsContainsNullException.class, () -> new Row(
                        Arrays.asList(
                                new BooleanCell(true),
                                null,
                                EmptyCell.getInstance()
                        )
                )
        );
    }

    @Test
    void comparePositive() {
        Assertions.assertEquals(
                0,

                new Row(
                        List.of(
                                new BooleanCell(true),
                                new LongCell(1L),
                                new DoubleCell(1.0),
                                new StringCell("1")
                        )
                ).compareTo(
                        new Row(
                                List.of(
                                        new BooleanCell(true),
                                        new LongCell(1L),
                                        new DoubleCell(1.0),
                                        new StringCell("1")
                                )
                        )
                )
        );

        Assertions.assertEquals(
                1,
                new Row(
                        List.of(
                                new BooleanCell(true),
                                new LongCell(1L),
                                new DoubleCell(1.0),
                                new StringCell("1")
                        )
                ).compareTo(
                        new Row(
                                List.of(
                                        new BooleanCell(false),
                                        new LongCell(1L),
                                        new DoubleCell(1.0),
                                        new StringCell("1")
                                )
                        )
                )
        );

        Assertions.assertEquals(
                1,
                new Row(
                        List.of(
                                new BooleanCell(true),
                                new LongCell(1L),
                                new DoubleCell(1.0),
                                new StringCell("1")
                        )
                ).compareTo(
                        new Row(
                                List.of(
                                        new BooleanCell(true),
                                        new LongCell(0L),
                                        new DoubleCell(1.0),
                                        new StringCell("1")
                                )
                        )
                )
        );

        Assertions.assertEquals(
                1,
                new Row(
                        List.of(
                                new BooleanCell(true),
                                new LongCell(1L),
                                new DoubleCell(1.0),
                                new StringCell("1")
                        )
                ).compareTo(
                        new Row(
                                List.of(
                                        new BooleanCell(true),
                                        new LongCell(1L),
                                        new DoubleCell(0.0),
                                        new StringCell("1")
                                )
                        )
                )
        );

        Assertions.assertEquals(
                1,
                new Row(
                        List.of(
                                new BooleanCell(true),
                                new LongCell(1L),
                                new DoubleCell(1.0),
                                new StringCell("1")
                        )
                ).compareTo(
                        new Row(
                                List.of(
                                        new BooleanCell(true),
                                        new LongCell(1L),
                                        new DoubleCell(1.0),
                                        new StringCell("0")
                                )
                        )
                )
        );

        Assertions.assertEquals(
                1,
                new Row(
                        List.of(
                                new BooleanCell(true),
                                new LongCell(1L),
                                new DoubleCell(1.0),
                                new StringCell("1")
                        )
                ).compareTo(
                        new Row(
                                List.of(
                                        new BooleanCell(true),
                                        new LongCell(1L),
                                        new DoubleCell(1.0),
                                        EmptyCell.getInstance()
                                )
                        )
                )
        );

    }

    @Test
    void compareNegative() {
        Assertions.assertThrows(
                RowsDifferentLengthsException.class,
                () -> new Row(
                        List.of(
                                new BooleanCell(true),
                                new LongCell(1L),
                                new DoubleCell(1.0),
                                new StringCell("1")
                        )
                ).compareTo(
                        new Row(
                                List.of(
                                        new BooleanCell(true),
                                        new LongCell(1L),
                                        new DoubleCell(1.0)
                                )
                        )
                )
        );

        Assertions.assertThrows(
                CellsTypeMismatchException.class,
                () -> new Row(
                        List.of(
                                new BooleanCell(true),
                                new LongCell(1L),
                                new DoubleCell(1.0),
                                new StringCell("1")
                        )
                ).compareTo(
                        new Row(
                                List.of(
                                        new LongCell(1L),
                                        new BooleanCell(true),
                                        new DoubleCell(1.0),
                                        new StringCell("1")
                                )
                        )
                )
        );
    }
}