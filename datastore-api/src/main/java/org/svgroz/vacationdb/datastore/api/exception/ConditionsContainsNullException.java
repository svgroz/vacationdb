package org.svgroz.vacationdb.datastore.api.exception;

import org.svgroz.vacationdb.datastore.api.statement.Condition;

import java.util.List;
import java.util.StringJoiner;

/**
 * @author Simon Grozovsky svgroz@outlook.com
 */
public class ConditionsContainsNullException extends DataStoreException {
    private final transient List<Condition> conditions;

    public ConditionsContainsNullException(final List<Condition> conditions) {
        this.conditions = conditions;
    }

    public List<Condition> getConditions() {
        return conditions;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ConditionsContainsNullException.class.getSimpleName() + "[", "]")
                .add("conditions=" + conditions)
                .toString();
    }
}
