package org.svgroz.vacationdb.datastore.model;

import org.svgroz.vacationdb.datastore.exception.CellsContainsNullException;
import org.svgroz.vacationdb.datastore.exception.EmptyCellsException;

import java.util.List;
import java.util.Objects;

/**
 * That class is null safety, thread safety, and immutable.
 */
public class Row {
    private final List<Cell> cells;

    /**
     * @param cells cannot be null and cannot contains null values itself
     * @throws NullPointerException       if cells is null
     * @throws EmptyCellsException        if cells is empty
     * @throws CellsContainsNullException if cells contains one or more null values
     */
    public Row(final List<Cell> cells) throws NullPointerException, EmptyCellsException, CellsContainsNullException {
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

    public List<Cell> getCells() {
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
