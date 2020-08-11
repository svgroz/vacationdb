package org.svgroz.vacationdb.datastore.comparator;


import org.svgroz.vacationdb.datastore.exception.EmptyCellsException;
import org.svgroz.vacationdb.datastore.exception.RowsDifferentLengthsException;
import org.svgroz.vacationdb.datastore.model.Cell;
import org.svgroz.vacationdb.datastore.model.Row;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class RowComparator implements Comparator<Row> {

    @Override
    public int compare(final Row first, final Row second) {
        Objects.requireNonNull(first, "first is null");
        Objects.requireNonNull(second, "second is null");

        final List<Cell> firstColumns = first.getCells();
        final List<Cell> secondColumns = second.getCells();

        if (firstColumns.size() != secondColumns.size()) {
            throw new RowsDifferentLengthsException(first, second);
        }

        if (firstColumns.isEmpty()) {
            throw new EmptyCellsException();
        }

        int result = 0;
        for (int i = 0; i < first.getCells().size(); i++) {
            Cell cellFromFirstRow = firstColumns.get(i);
            Cell cellFromSecondRow = secondColumns.get(i);

            result = result + compareCells(cellFromFirstRow, cellFromSecondRow);
            if (result != 0) {
                return result;
            }
        }

        return result;
    }

    @SuppressWarnings("rawtypes unchecked")
    private int compareCells(Cell first, Cell second) {
        return first.compareTo(second);
    }
}