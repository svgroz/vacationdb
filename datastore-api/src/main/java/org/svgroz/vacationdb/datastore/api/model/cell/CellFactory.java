package org.svgroz.vacationdb.datastore.api.model.cell;

/**
 * @author Simon Grozovsky svgroz@outlook.com
 */
public interface CellFactory {
    /**
     * @param value supposed to be not null
     * @return {@link BooleanCell}
     */
    BooleanCell of(Boolean value);

    /**
     * @param value supposed to be not null
     * @return {@link BooleanCell}
     */
    LongCell of(Long value);

    /**
     * @param value supposed to be not null
     * @return {@link BooleanCell}
     */
    DoubleCell of(Double value);

    /**
     * @param value supposed to be not null
     * @return {@link BooleanCell}
     */
    StringCell of(String value);

    EmptyCell empty();

    boolean isEmpty(Cell cell);
}
