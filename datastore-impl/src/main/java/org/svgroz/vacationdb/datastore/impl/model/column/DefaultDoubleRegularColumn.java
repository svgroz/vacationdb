package org.svgroz.vacationdb.datastore.impl.model.column;

import org.svgroz.vacationdb.datastore.api.model.cell.Cell;
import org.svgroz.vacationdb.datastore.api.model.cell.DoubleCell;
import org.svgroz.vacationdb.datastore.api.model.cell.EmptyCell;
import org.svgroz.vacationdb.datastore.api.model.column.DoubleKeyColumn;
import org.svgroz.vacationdb.datastore.api.model.column.DoubleRegularColumn;
import org.svgroz.vacationdb.datastore.api.model.column.KeyColumn;
import org.svgroz.vacationdb.datastore.api.model.column.RegularColumn;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * @author Simon Grozovsky svgroz@outlook.com
 */
public final class DefaultDoubleRegularColumn implements DoubleRegularColumn {
    private final String name;

    public DefaultDoubleRegularColumn(final String name) {
        this.name = Objects.requireNonNull(name, "name is null");
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isSupported(final Cell cell) {
        return cell instanceof DoubleCell || cell instanceof EmptyCell;
    }

    @Override
    public boolean isSupported(final Class<? extends Cell> cellClass) {
        return cellClass.isAssignableFrom(DoubleCell.class) || cellClass.isAssignableFrom(EmptyCell.class);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof DefaultDoubleRegularColumn)) return false;
        final DefaultDoubleRegularColumn that = (DefaultDoubleRegularColumn) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", DefaultDoubleRegularColumn.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .toString();
    }
}
