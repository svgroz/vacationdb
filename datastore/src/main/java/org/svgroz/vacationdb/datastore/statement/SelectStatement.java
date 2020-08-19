package org.svgroz.vacationdb.datastore.statement;

/**
 * @author Simon Grozovsky svgroz@outlook.com
 */
public interface SelectStatement extends Statement {
    @Override
    default StatementType type() {
        return StatementType.SELECT;
    }
}
