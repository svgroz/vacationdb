package org.svgroz.vacationdb.datastore.impl.model.cell;

import org.svgroz.vacationdb.datastore.api.model.DataType;
import org.svgroz.vacationdb.datastore.api.model.cell.Cell;
import org.svgroz.vacationdb.datastore.api.model.cell.EmptyCell;

import java.util.StringJoiner;

/**
 * Cell that represents empty values
 *
 * @author Simon Grozovsky svgroz@outlook.com
 */
final class DefaultEmptyCell implements EmptyCell {

    private static final DefaultEmptyCell INSTANCE = new DefaultEmptyCell();

    private DefaultEmptyCell() {
    }

    public static DefaultEmptyCell getInstance() {
        return INSTANCE;
    }

    @Override
    public DataType supportedType() {
        return DataType.EMPTY;
    }

    /**
     * @param cell instance of {@link Cell }
     * @return true if cell is empty
     */
    public static boolean isEmpty(Cell cell) {
        return cell == INSTANCE;
    }

    @Override
    public int compareTo(final Cell o) {
        return isEmpty(o) ? 0 : -1;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", DefaultEmptyCell.class.getSimpleName() + "[", "]")
                .toString();
    }
}
