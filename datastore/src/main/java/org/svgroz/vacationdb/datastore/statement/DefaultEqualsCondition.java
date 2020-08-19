package org.svgroz.vacationdb.datastore.statement;

import org.svgroz.vacationdb.datastore.model.cell.Cell;
import org.svgroz.vacationdb.datastore.model.column.KeyColumn;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * @author Simon Grozovsky svgroz@outlook.com
 */
final class DefaultEqualsCondition implements EqualsCondition {
    private final KeyColumn column;
    private final Cell cell;

    DefaultEqualsCondition(final KeyColumn column, final Cell cell) {
        this.column = Objects.requireNonNull(column, "column is null");
        this.cell = Objects.requireNonNull(cell, "cell is null");
    }

    @Override
    public KeyColumn column() {
        return column;
    }

    @Override
    public Cell cell() {
        return cell;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof DefaultEqualsCondition)) return false;
        final DefaultEqualsCondition that = (DefaultEqualsCondition) o;
        return Objects.equals(column, that.column) &&
                Objects.equals(cell, that.cell);
    }

    @Override
    public int hashCode() {
        return Objects.hash(column, cell);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", DefaultEqualsCondition.class.getSimpleName() + "[", "]")
                .add("column=" + column)
                .add("cell=" + cell)
                .toString();
    }
}
