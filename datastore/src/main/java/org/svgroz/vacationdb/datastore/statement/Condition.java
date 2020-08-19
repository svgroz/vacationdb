package org.svgroz.vacationdb.datastore.statement;

import org.svgroz.vacationdb.datastore.model.cell.Cell;
import org.svgroz.vacationdb.datastore.model.column.KeyColumn;

/**
 * @author Simon Grozovsky svgroz@outlook.com
 */
public interface Condition {

    static EqualsCondition equalsCondition(final KeyColumn column, final Cell value) {
        return EqualsCondition.of(column, value);
    }

    KeyColumn column();

    Cell cell();

    ConditionType conditionType();
}
