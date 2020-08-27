package org.svgroz.vacationdb.datastore.api.model.row;

import org.eclipse.collections.api.list.ImmutableList;
import org.svgroz.vacationdb.datastore.api.model.cell.Cell;

/**
 * @author Simon Grozovsky svgroz@outlook.com
 */
public interface Row {
    ImmutableList<Cell> getCells();
}
