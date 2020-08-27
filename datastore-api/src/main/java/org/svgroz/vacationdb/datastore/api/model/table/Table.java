package org.svgroz.vacationdb.datastore.api.model.table;

import org.eclipse.collections.api.list.ImmutableList;
import org.svgroz.vacationdb.datastore.api.model.row.Row;
import org.svgroz.vacationdb.datastore.api.statement.Condition;

/**
 * @author Simon Grozovsky svgroz@outlook.com
 */
public interface Table {
    String getName();

    ImmutableList<Row> select(ImmutableList<Condition> conditions);
}
