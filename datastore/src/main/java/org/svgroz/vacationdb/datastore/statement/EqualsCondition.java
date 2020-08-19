package org.svgroz.vacationdb.datastore.statement;

import org.svgroz.vacationdb.datastore.model.cell.Cell;
import org.svgroz.vacationdb.datastore.model.column.KeyColumn;

/**
 * @author Simon Grozovsky svgroz@outlook.com
 */
public interface EqualsCondition extends Condition {

    static EqualsCondition of(final KeyColumn column, final Cell cell) {
        return new DefaultEqualsCondition(column, cell);
    }

    @Override
    default ConditionType conditionType() {
        return ConditionType.EQUALS;
    }
}
