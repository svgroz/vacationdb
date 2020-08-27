package org.svgroz.vacationdb.datastore.impl.model.cell;

import org.svgroz.vacationdb.datastore.api.model.cell.Cell;
import org.svgroz.vacationdb.datastore.api.model.cell.EmptyCell;

import java.util.StringJoiner;

/**
 * Cell that represents empty values
 *
 * @author Simon Grozovsky svgroz@outlook.com
 */
public final class DefaultEmptyCell implements EmptyCell {

    private static final DefaultEmptyCell INSTANCE = new DefaultEmptyCell();

    private DefaultEmptyCell() {
    }

    public static DefaultEmptyCell getInstance() {
        return INSTANCE;
    }

    @Override
    public int compareTo(final Cell o) {
        if (o instanceof EmptyCell) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", DefaultEmptyCell.class.getSimpleName() + "[", "]")
                .toString();
    }
}
