package org.svgroz.vacationdb.datastore.model.index;

import org.junit.jupiter.api.Test;
import org.svgroz.vacationdb.datastore.model.cell.Cell;
import org.svgroz.vacationdb.datastore.model.row.Row;

/**
 * @author Simon Grozovsky svgroz@outlook.com
 */
class TreeIndexTest {
    @Test
    void test1() {
        TreeIndex treeIndex = new TreeIndex();

        for (long i = 0; i < 10; i++) {
            for (long j = 0; j < 10; j++) {
                for (long k = 0; k < 10; k++) {
                    treeIndex.addNewValue(Row.of(Cell.of(i), Cell.of(j), Cell.of(k)));
                }
            }
        }

        System.out.println(treeIndex);
    }
}
