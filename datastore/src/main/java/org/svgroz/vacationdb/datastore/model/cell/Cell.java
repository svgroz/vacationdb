package org.svgroz.vacationdb.datastore.model.cell;

import org.svgroz.vacationdb.datastore.model.DataType;

import java.util.Objects;

/**
 * Basic cells interface
 *
 * @author Simon Grozovsky svgroz@outlook.com
 */
public interface Cell extends Comparable<Cell> {

    /**
     * @return supported type
     */
    DataType supportedType();

    /**
     * @param value supposed to be not null
     * @return {@link BooleanCell}
     */
    static TypedCell<Boolean> of(Boolean value) {
        Objects.requireNonNull(value, "value is null");
        return value ? BooleanCell.INSTANCE_TRUE : BooleanCell.INSTANCE_FALSE;
    }

    /**
     * @param value supposed to be not null
     * @return {@link BooleanCell}
     */
    static TypedCell<Long> of(Long value) {
        return new LongCell(value);
    }

    /**
     * @param value supposed to be not null
     * @return {@link BooleanCell}
     */
    static TypedCell<Double> of(Double value) {
        return new DoubleCell(value);
    }

    /**
     * @param value supposed to be not null
     * @return {@link BooleanCell}
     */
    static TypedCell<String> of(String value) {
        return new StringCell(value);
    }

    /**
     * @return {@link EmptyCell}
     */
    static Cell empty() {
        return EmptyCell.getInstance();
    }

    /**
     * @param cell supposed to be not null
     * @return true if cell is instance of {@link EmptyCell}
     */
    static boolean isEmpty(Cell cell) {
        return DataType.EMPTY == cell.supportedType();
    }
}
