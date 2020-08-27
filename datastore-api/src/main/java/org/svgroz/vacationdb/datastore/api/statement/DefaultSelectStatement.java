package org.svgroz.vacationdb.datastore.api.statement;

import org.svgroz.vacationdb.datastore.api.exception.ConditionsContainsNullException;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * @author Simon Grozovsky svgroz@outlook.com
 */
public class DefaultSelectStatement implements SelectStatement {

    private final List<Condition> conditions;

    public DefaultSelectStatement(final List<Condition> conditions) {
        Objects.requireNonNull(conditions, "condition is null");
        if (conditions.isEmpty()) {
            this.conditions = Collections.emptyList();
            return;
        }

        for (final Condition condition : conditions) {
            if (condition == null) {
                throw new ConditionsContainsNullException(conditions);
            }
        }
        this.conditions = List.copyOf(conditions);
    }

    @Override
    public List<Condition> conditions() {
        return conditions;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof DefaultSelectStatement)) return false;
        final DefaultSelectStatement that = (DefaultSelectStatement) o;
        return Objects.equals(conditions, that.conditions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(conditions);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", DefaultSelectStatement.class.getSimpleName() + "[", "]")
                .add("conditions=" + conditions)
                .toString();
    }
}
