package org.svgroz.vacationdb.datastore.impl.model.cell;

import org.svgroz.vacationdb.datastore.api.model.cell.BooleanCell;
import org.svgroz.vacationdb.datastore.api.model.cell.Cell;
import org.svgroz.vacationdb.datastore.api.model.cell.CellFactory;
import org.svgroz.vacationdb.datastore.api.model.cell.DoubleCell;
import org.svgroz.vacationdb.datastore.api.model.cell.EmptyCell;
import org.svgroz.vacationdb.datastore.api.model.cell.LongCell;
import org.svgroz.vacationdb.datastore.api.model.cell.StringCell;

import java.util.Objects;

/**
 * @author Simon Grozovsky svgroz@outlook.com
 */
public class DefaultCellFactory implements CellFactory {
    @Override
    public BooleanCell of(final Boolean value) {
        Objects.requireNonNull(value, "value is null");
        return value ? DefaultBooleanCell.INSTANCE_TRUE : DefaultBooleanCell.INSTANCE_FALSE;
    }

    @Override
    public LongCell of(final Long value) {
        Objects.requireNonNull(value, "value is null");
        return new DefaultLongCell(value);
    }

    @Override
    public DoubleCell of(final Double value) {
        Objects.requireNonNull(value, "value is null");
        return new DefaultDoubleCell(value);
    }

    @Override
    public StringCell of(final String value) {
        Objects.requireNonNull(value, "value is null");
        return new DefaultStringCell(value);
    }

    @Override
    public EmptyCell empty() {
        return DefaultEmptyCell.getInstance();
    }

    @Override
    public boolean isEmpty(final Cell cell) {
        return cell instanceof EmptyCell;
    }
}
