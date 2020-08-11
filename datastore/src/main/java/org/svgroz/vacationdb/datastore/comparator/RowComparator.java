package org.svgroz.vacationdb.datastore.comparator;


import org.svgroz.vacationdb.datastore.exception.DifferentCellsTypesException;
import org.svgroz.vacationdb.datastore.exception.EmptyCellsException;
import org.svgroz.vacationdb.datastore.exception.RowsDifferentLengthsException;
import org.svgroz.vacationdb.datastore.model.Cell;
import org.svgroz.vacationdb.datastore.model.ColumnType;
import org.svgroz.vacationdb.datastore.model.Row;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class RowComparator implements Comparator<Row> {
    @Override
    public int compare(final Row first, final Row second) {
        Objects.requireNonNull(first, "first is null");
        Objects.requireNonNull(second, "second is null");

        final List<Cell<?>> firstColumns = first.getCells();
        final List<Cell<?>> secondColumns = second.getCells();

        if (firstColumns.size() != secondColumns.size()) {
            throw new RowsDifferentLengthsException(first, second);
        }

        if (firstColumns.isEmpty()) {
            throw new EmptyCellsException();
        }

        int result = 0;
        for (int i = 0; i < first.getCells().size(); i++) {
            final int n = i;
            Cell<?> cellFromFirstRow = firstColumns.get(i);
            Cell<?> cellFromSecondRow = secondColumns.get(i);
        }

        return 0;
    }

    private int compareColumns(Cell<?> first, Cell<?> second) {
        final ColumnType firstType = first.getType();
        final ColumnType secondType = second.getType();
        if (firstType != secondType) {
            throw new DifferentCellsTypesException(first, second);
        }

        final Object firstValue = first.getValue();
        final Object secondValue = second.getValue();

        switch (firstType) {
            case BOOLEAN:
                return Boolean.compare((Boolean)firstValue, (Boolean) secondValue);
            case LONG:
                return Long.compare((Long) firstValue, (Long) secondValue);
            case DOUBLE:
                return Double.compare((Double)firstValue, (Double)secondValue);
            case STRING:
                return 0;
        }

        return 0;
    }
}
