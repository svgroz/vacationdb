package org.svgroz.vacationdb.datastore.model;

import org.svgroz.vacationdb.datastore.exception.CellsContainsNullException;
import org.svgroz.vacationdb.datastore.exception.EmptyCellsException;
import org.svgroz.vacationdb.datastore.exception.RowsDifferentLengthsException;

import java.util.List;
import java.util.Objects;

/**
 * That class is null safety, thread safety, and immutable.
 */
public class Row implements Comparable<Row> {
    private final List<CellType> cells;

    /**
     * @param cells cannot be null and cannot contains null values itself
     * @throws NullPointerException       if cells is null
     * @throws EmptyCellsException        if cells is empty
     * @throws CellsContainsNullException if cells contains one or more null values
     */
    public Row(final List<CellType> cells) throws NullPointerException, EmptyCellsException, CellsContainsNullException {
        Objects.requireNonNull(cells, "cells is null");

        if (cells.isEmpty()) {
            throw new EmptyCellsException();
        }

        for (final CellType cell : cells) {
            if (cell == null) {
                throw new CellsContainsNullException();
            }
        }

        this.cells = List.copyOf(cells);
    }

    @Override
    public int compareTo(final Row target) {
        Objects.requireNonNull(target, "target is null");

        final List<CellType> firstColumns = cells;
        final List<CellType> secondColumns = target.getCells();

        if (firstColumns.size() != secondColumns.size()) {
            throw new RowsDifferentLengthsException(this, target);
        }

        if (firstColumns.isEmpty()) {
            throw new EmptyCellsException();
        }

        for (int i = 0; i < firstColumns.size(); i++) {
            CellType cellFromTheFirstRow = firstColumns.get(i);
            CellType cellFromTheSecondRow = secondColumns.get(i);

            int result = cellFromTheFirstRow.compareTo(cellFromTheSecondRow);
            if (result != 0) {
                return result;
            }
        }

        return 0;
    }

    public List<CellType> getCells() {
        return cells;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof Row)) return false;
        final Row row = (Row) o;
        return Objects.equals(cells, row.cells);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cells);
    }

    @Override
    public String toString() {
        return "Row{" +
                "cells=" + cells +
                '}';
    }
}
