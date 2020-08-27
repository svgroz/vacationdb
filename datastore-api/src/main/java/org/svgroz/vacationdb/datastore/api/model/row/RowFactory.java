package org.svgroz.vacationdb.datastore.api.model.row;

import org.eclipse.collections.api.list.ImmutableList;
import org.svgroz.vacationdb.datastore.api.model.cell.Cell;

/**
 * @author Simon Grozovsky svgroz@outlook.com
 */
public interface RowFactory {
    /**
     * @param cells row data
     * @return row of default type
     */
    Row of(final ImmutableList<Cell> cells);
    /**
     * @param cells row data
     * @return row of default type
     */
    Row of(final Cell... cells);
}
