package org.svgroz.vacationdb.datastore.model.row;

import org.svgroz.vacationdb.datastore.model.cell.Cell;

import java.util.Arrays;
import java.util.List;

/**
 * @author Simon Grozovsky svgroz@outlook.com
 */
public interface Row {
    List<Cell> getCells();

    /**
     * @param cells row data
     * @return row of default type
     */
    static Row of(final List<Cell> cells) {
        return new DefaultRow(cells);
    }

    /**
     * @param cells row data
     * @return row of default type
     */
    static Row of(final Cell... cells) {
        return new DefaultRow(Arrays.asList(cells));
    }
}
