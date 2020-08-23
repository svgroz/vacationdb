package org.svgroz.vacationdb.datastore.model.row;

import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.ImmutableList;
import org.svgroz.vacationdb.datastore.model.cell.Cell;

/**
 * @author Simon Grozovsky svgroz@outlook.com
 */
public interface Row {
    ImmutableList<Cell> getCells();

    /**
     * @param cells row data
     * @return row of default type
     */
    static Row of(final ImmutableList<Cell> cells) {
        return new DefaultRow(cells);
    }

    /**
     * @param cells row data
     * @return row of default type
     */
    static Row of(final Cell... cells) {
        return new DefaultRow(Lists.immutable.of(cells));
    }
}
