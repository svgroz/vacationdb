package org.svgroz.vacationdb.datastore.model;

import org.svgroz.vacationdb.datastore.exception.EmptyCellsException;

import java.util.List;
import java.util.Objects;

public class Row {
    private final List<Cell<?>> cells;

    public Row(final List<Cell<?>> cells) {
        this.cells = List.copyOf(
                Objects.requireNonNull(cells, "cells is null")
        );

        if (this.cells.isEmpty()) {
            throw new EmptyCellsException();
        }
    }

    public List<Cell<?>> getCells() {
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
