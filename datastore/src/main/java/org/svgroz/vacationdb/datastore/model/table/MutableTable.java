package org.svgroz.vacationdb.datastore.model.table;

import org.svgroz.vacationdb.datastore.model.row.Row;


/**
 * @author Simon Grozovsky svgroz@outlook.com
 */
public interface MutableTable extends Table {
    /**
     * @param row supposed to be not null
     * @return false if table already has had row with same keys
     */
    boolean addRow(Row row);
}
