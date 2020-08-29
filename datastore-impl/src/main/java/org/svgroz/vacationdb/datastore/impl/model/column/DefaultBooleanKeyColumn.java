package org.svgroz.vacationdb.datastore.impl.model.column;

import org.eclipse.collections.api.set.ImmutableSet;
import org.svgroz.vacationdb.datastore.api.model.DataType;
import org.svgroz.vacationdb.datastore.api.model.cell.BooleanCell;
import org.svgroz.vacationdb.datastore.api.model.cell.Cell;
import org.svgroz.vacationdb.datastore.api.model.cell.EmptyCell;
import org.svgroz.vacationdb.datastore.api.model.column.BooleanKeyColumn;
import org.svgroz.vacationdb.datastore.api.model.column.KeyColumn;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * @author Simon Grozovsky svgroz@outlook.com
 */
public final class DefaultBooleanKeyColumn implements BooleanKeyColumn {
    private final String name;

    public DefaultBooleanKeyColumn(final String name) {
        this.name = Objects.requireNonNull(name, "name is null");
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isSupported(final Cell cell) {
        return cell instanceof BooleanCell || cell instanceof EmptyCell;
    }

    @Override
    public boolean isSupported(final Class<? extends Cell> cellClass) {
        return cellClass.isAssignableFrom(BooleanCell.class) || cellClass.isAssignableFrom(EmptyCell.class);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof DefaultBooleanKeyColumn)) return false;
        final DefaultBooleanKeyColumn that = (DefaultBooleanKeyColumn) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", DefaultBooleanKeyColumn.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .toString();
    }
}
