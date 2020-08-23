package org.svgroz.vacationdb.datastore.model.row;

import org.eclipse.collections.api.factory.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.svgroz.vacationdb.datastore.model.cell.Cell;

/**
 * @author Simon Grozovsky svgroz@outlook.com
 */
class RowComparatorTest {

    @Test
    void comparePositive() {
        {
            final RowComparator comparator = new RowComparator(Lists.immutable.of(0, 1, 2, 3, 4));
            final Row first = Row.of(Lists.immutable.of(Cell.of(true), Cell.of(0L), Cell.of(0.0), Cell.of(""), Cell.empty()));
            final Row second = Row.of(Lists.immutable.of(Cell.of(true), Cell.of(0L), Cell.of(0.0), Cell.of(""), Cell.empty()));

            Assertions.assertEquals(0, comparator.compare(first, second));
        }
        {
            final RowComparator comparator = new RowComparator(Lists.immutable.of(0, 1, 2, 3));
            final Row first = Row.of(Lists.immutable.of(Cell.of(true), Cell.of(0L), Cell.of(0.0), Cell.of(""), Cell.empty()));
            final Row second = Row.of(Lists.immutable.of(Cell.of(true), Cell.of(0L), Cell.of(0.0), Cell.of(""), Cell.of(100L)));

            Assertions.assertEquals(0, comparator.compare(first, second));
        }
        {
            final RowComparator comparator = new RowComparator(Lists.immutable.of(1, 2, 3, 4));
            final Row first = Row.of(Lists.immutable.of(Cell.of(true), Cell.of(0L), Cell.of(0.0), Cell.of(""), Cell.empty()));
            final Row second = Row.of(Lists.immutable.of(Cell.of(false), Cell.of(0L), Cell.of(0.0), Cell.of(""), Cell.empty()));

            Assertions.assertEquals(0, comparator.compare(first, second));
        }
        {
            final RowComparator comparator = new RowComparator(Lists.immutable.of(1, 2, 3, 4));
            final Row first = Row.of(Lists.immutable.of(Cell.of(true), Cell.of(0L), Cell.of(0.0), Cell.of(""), Cell.empty()));
            final Row second = Row.of(Lists.immutable.of(Cell.empty(), Cell.of(0L), Cell.of(0.0), Cell.of(""), Cell.empty()));

            Assertions.assertEquals(0, comparator.compare(first, second));
        }
        {
            final RowComparator comparator = new RowComparator(Lists.immutable.of(0, 2, 4));
            final Row first = Row.of(Lists.immutable.of(Cell.of(true), Cell.of(0L), Cell.of(0.0), Cell.of(""), Cell.empty()));
            final Row second = Row.of(Lists.immutable.of(Cell.of(true), Cell.empty(), Cell.of(0.0), Cell.empty(), Cell.empty()));

            Assertions.assertEquals(0, comparator.compare(first, second));
        }
    }
}