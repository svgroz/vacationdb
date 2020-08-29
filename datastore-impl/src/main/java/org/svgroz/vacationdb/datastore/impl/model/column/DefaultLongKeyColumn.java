package org.svgroz.vacationdb.datastore.impl.model.column;

import org.svgroz.vacationdb.datastore.api.model.cell.LongCell;
import org.svgroz.vacationdb.datastore.api.model.cell.Cell;
import org.svgroz.vacationdb.datastore.api.model.cell.EmptyCell;
import org.svgroz.vacationdb.datastore.api.model.column.KeyColumn;
import org.svgroz.vacationdb.datastore.api.model.column.LongKeyColumn;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * @author Simon Grozovsky svgroz@outlook.com
 */
public final class DefaultLongKeyColumn implements LongKeyColumn {
    private final String name;

    public DefaultLongKeyColumn(final String name) {
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
        if (!(o instanceof DefaultLongKeyColumn)) return false;
        final DefaultLongKeyColumn that = (DefaultLongKeyColumn) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", DefaultLongKeyColumn.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .toString();
    }
}
