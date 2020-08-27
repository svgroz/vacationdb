package org.svgroz.vacationdb.datastore.api.statement;

import org.svgroz.vacationdb.datastore.api.model.cell.Cell;
import org.svgroz.vacationdb.datastore.api.model.column.KeyColumn;

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
