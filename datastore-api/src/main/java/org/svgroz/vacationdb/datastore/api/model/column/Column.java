package org.svgroz.vacationdb.datastore.api.model.column;

import org.svgroz.vacationdb.datastore.api.model.cell.Cell;

/**
 * @author Simon Grozovsky svgroz@outlook.com
 */
public interface Column {
    /**
     * @return name of columns
     */
    String getName();

    boolean isSupported(Cell cell);

    boolean isSupported(Class<? extends Cell> cellClass);
}
