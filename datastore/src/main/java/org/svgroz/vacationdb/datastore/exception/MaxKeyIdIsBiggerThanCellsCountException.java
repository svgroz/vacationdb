package org.svgroz.vacationdb.datastore.exception;

import org.svgroz.vacationdb.datastore.model.cell.Cell;

import java.util.List;
import java.util.StringJoiner;

/**
 * @author Simon Grozovsky svgroz@outlook.com
 */
public class MaxKeyIdIsBiggerThanCellsCountException extends DataStoreException {
    private final List<Integer> keyIndexes;
    private final List<Cell> cells;

    public MaxKeyIdIsBiggerThanCellsCountException(final List<Integer> keyIndexes, final List<Cell> cells) {
        this.keyIndexes = keyIndexes;
        this.cells = cells;
    }

    public List<Integer> getKeyIndexes() {
        return keyIndexes;
    }

    public List<Cell> getCells() {
        return cells;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", MaxKeyIdIsBiggerThanCellsCountException.class.getSimpleName() + "[", "]")
                .add("keyIndexes=" + keyIndexes)
                .add("cells=" + cells)
                .toString();
    }
}
