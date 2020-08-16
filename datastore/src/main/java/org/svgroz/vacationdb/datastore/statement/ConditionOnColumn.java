package org.svgroz.vacationdb.datastore.statement;

import org.svgroz.vacationdb.datastore.model.cell.Cell;
import org.svgroz.vacationdb.datastore.model.column.KeyColumn;

/**
 * @author Simon Grozovsky svgroz@outlook.com
 */
public interface ConditionOnColumn {
    KeyColumn column();

    Cell cell();

    Condition condition();
}
