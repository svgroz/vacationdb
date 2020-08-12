package org.svgroz.vacationdb.datastore.model;

import org.svgroz.vacationdb.datastore.exception.*;

import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * That class is null safety, thread safety, and immutable.
 */
public class Row implements Comparable<Row> {
    private final List<Cell> cells;
    private final KeyIndexesContainer keyIndexesContainer;

    /**
     * @param cells      cannot be null and cannot contains null values itself
     * @param keyIndexes cannot be null
     * @throws NullPointerException       if cells or keyIndexes is null
     * @throws EmptyCellsException        if cells is empty
     * @throws CellsContainsNullException if cells contains one or more null values
     */
    public Row(final List<Cell> cells, final KeyIndexesContainer keyIndexes) {
        Objects.requireNonNull(cells, "cells is null");

        if (cells.isEmpty()) {
            throw new EmptyCellsException();
        }

        for (final Cell cell : cells) {
            if (cell == null) {
                throw new CellsContainsNullException();
            }
        }

        this.keyIndexesContainer = Objects.requireNonNull(keyIndexes);

        if (keyIndexesContainer.getMaxId() > cells.size()) {
            throw new MaxKeyIdIsBiggerThanCellsCountException(keyIndexesContainer.getIndexes(), cells);
        }

        this.cells = List.copyOf(cells);
    }

    @Override
    public int compareTo(final Row target) {
        Objects.requireNonNull(target, "target is null");

        final List<Cell> firstColumns = cells;
        final List<Cell> secondColumns = target.getCells();

        if (firstColumns.size() != secondColumns.size()) {
            throw new RowsDifferentLengthsException(this, target);
        }

        if (firstColumns.isEmpty()) {
            throw new EmptyCellsException();
        }

        for (int i = 0; i < firstColumns.size(); i++) {
            Cell cellFromTheFirstRow = firstColumns.get(i);
            Cell cellFromTheSecondRow = secondColumns.get(i);

            int result = cellFromTheFirstRow.compareTo(cellFromTheSecondRow);
            if (result != 0) {
                return result;
            }
        }

        return 0;
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
        return new StringJoiner(", ", Row.class.getSimpleName() + "[", "]")
                .add("cells=" + cells)
                .add("keyIndexesContainer=" + keyIndexesContainer)
                .toString();
    }
}
