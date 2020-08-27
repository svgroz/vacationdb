package org.svgroz.vacationdb.datastore.impl.model.row;

import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.ImmutableList;
import org.svgroz.vacationdb.datastore.api.model.cell.Cell;
import org.svgroz.vacationdb.datastore.api.model.row.Row;
import org.svgroz.vacationdb.datastore.api.model.row.RowFactory;

import java.util.Objects;

/**
 * @author Simon Grozovsky svgroz@outlook.com
 */
public class DefaultRowFactory implements RowFactory {

    private static final Row EMPTY_ROW = Lists.immutable::empty;

    @Override
    public Row of(final ImmutableList<Cell> cells) {
        Objects.requireNonNull(cells, "cells is null");

        if (cells.isEmpty()) {
            return EMPTY_ROW;
        }

        return new DefaultRow(cells);
    }

    @Override
    public Row of(final Cell... cells) {
        return of(Lists.immutable.of(cells));
    }
}
