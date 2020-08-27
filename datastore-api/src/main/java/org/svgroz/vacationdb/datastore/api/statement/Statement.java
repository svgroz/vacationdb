package org.svgroz.vacationdb.datastore.api.statement;

import java.util.List;

/**
 * @author Simon Grozovsky svgroz@outlook.com
 */
public interface Statement {
    StatementType type();

    List<Condition> conditions();
}
