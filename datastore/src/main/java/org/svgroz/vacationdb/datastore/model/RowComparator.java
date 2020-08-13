package org.svgroz.vacationdb.datastore.model;

import org.svgroz.vacationdb.datastore.exception.EmptyCellsException;
import org.svgroz.vacationdb.datastore.exception.RowsDifferentLengthsException;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * @author Simon Grozovsky svgroz@outlook.com
 */
public class RowComparator implements Comparator<Row> {

    private final List<Integer> keyIndexes;

    public RowComparator(final List<Integer> keyIndexes) {
        this.keyIndexes = keyIndexes;
    }

    @Override
    public int compare(final Row first, final Row second) {
        final List<Cell> firstColumns = first.getCells();
        final List<Cell> secondColumns = second.getCells();

        if (firstColumns.size() != secondColumns.size()) {
            throw new RowsDifferentLengthsException(first, second);
        }

        if (firstColumns.isEmpty()) {
            throw new EmptyCellsException();
        }

        for (final Integer i : keyIndexes) {
            Cell cellFromTheFirstRow = firstColumns.get(i);
            Cell cellFromTheSecondRow = secondColumns.get(i);

            int result = cellFromTheFirstRow.compareTo(cellFromTheSecondRow);
            if (result != 0) {
                return result;
            }
        }

        return 0;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof RowComparator)) return false;
        final RowComparator that = (RowComparator) o;
        return Objects.equals(keyIndexes, that.keyIndexes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(keyIndexes);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", RowComparator.class.getSimpleName() + "[", "]")
                .add("keyIndexes=" + keyIndexes)
                .toString();
    }
}
