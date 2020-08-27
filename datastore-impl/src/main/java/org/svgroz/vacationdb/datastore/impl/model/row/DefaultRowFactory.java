package org.svgroz.vacationdb.datastore.impl.model.row;

import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.ImmutableList;
import org.svgroz.vacationdb.datastore.api.model.cell.Cell;
import org.svgroz.vacationdb.datastore.api.model.row.Row;
import org.svgroz.vacationdb.datastore.api.model.row.RowFactory;

/**
 * @author Simon Grozovsky svgroz@outlook.com
 */
public class DefaultRowFactory implements RowFactory {
    @Override
    public Row of(final ImmutableList<Cell> cells) {
        return new DefaultRow(cells);
    }

    @Override
    public Row of(final Cell... cells) {
        return new DefaultRow(Lists.immutable.of(cells));
    }
}
