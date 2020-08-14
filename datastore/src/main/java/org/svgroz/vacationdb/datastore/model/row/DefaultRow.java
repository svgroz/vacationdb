package org.svgroz.vacationdb.datastore.model.row;

import org.svgroz.vacationdb.datastore.exception.CellsContainsNullException;
import org.svgroz.vacationdb.datastore.exception.EmptyCellsException;
import org.svgroz.vacationdb.datastore.model.cell.Cell;

import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * That class is null safety, thread safety, and immutable.
 *
 * @author Simon Grozovsky svgroz@outlook.com
 */
final class DefaultRow implements Row {
    private final List<Cell> cells;

    /**
     * @param cells cannot be null and cannot contains null values itself
     * @throws NullPointerException       if cells or keyIndexes is null
     * @throws EmptyCellsException        if cells is empty
     * @throws CellsContainsNullException if cells contains one or more null values
     */
    DefaultRow(final List<Cell> cells) {
        Objects.requireNonNull(cells, "cells is null");

        if (cells.isEmpty()) {
            throw new EmptyCellsException();
        }

        for (final Cell cell : cells) {
            if (cell == null) {
                throw new CellsContainsNullException();
            }
        }

        this.cells = List.copyOf(cells);
    }

    @Override
    public List<Cell> getCells() {
        return cells;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof DefaultRow)) return false;
        final DefaultRow row = (DefaultRow) o;
        return Objects.equals(cells, row.cells);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cells);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", DefaultRow.class.getSimpleName() + "[", "]")
                .add("cells=" + cells)
                .toString();
    }
}
