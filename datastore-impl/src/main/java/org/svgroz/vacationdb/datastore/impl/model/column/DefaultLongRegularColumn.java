package org.svgroz.vacationdb.datastore.impl.model.column;

import org.svgroz.vacationdb.datastore.api.model.cell.Cell;
import org.svgroz.vacationdb.datastore.api.model.cell.EmptyCell;
import org.svgroz.vacationdb.datastore.api.model.cell.LongCell;
import org.svgroz.vacationdb.datastore.api.model.column.LongRegularColumn;
import org.svgroz.vacationdb.datastore.api.model.column.RegularColumn;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * @author Simon Grozovsky svgroz@outlook.com
 */
public final class DefaultLongRegularColumn implements LongRegularColumn {
    private final String name;

    public DefaultLongRegularColumn(final String name) {
        this.name = Objects.requireNonNull(name, "name is null");
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isSupported(final Cell cell) {
        return cell instanceof LongCell || cell instanceof EmptyCell;
    }

    @Override
    public boolean isSupported(final Class<? extends Cell> cellClass) {
        return cellClass.isAssignableFrom(LongCell.class) || cellClass.isAssignableFrom(EmptyCell.class);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof DefaultLongRegularColumn)) return false;
        final DefaultLongRegularColumn that = (DefaultLongRegularColumn) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", DefaultLongRegularColumn.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .toString();
    }
}
