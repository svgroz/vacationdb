package org.svgroz.vacationdb.datastore.model.index;

import org.junit.jupiter.api.Test;
import org.svgroz.vacationdb.datastore.model.cell.Cell;
import org.svgroz.vacationdb.datastore.model.row.Row;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * @author Simon Grozovsky svgroz@outlook.com
 */
class TreeIndexTest {

    @Test
    void test1() throws Exception {
        final TreeIndex treeIndex = new TreeIndex(new Random()::nextLong);

        ExecutorService executorService = Executors.newFixedThreadPool(30);

        for (int i = 0; i < 1000; i++) {
            executorService.execute(() -> addToIndex(treeIndex));
        }

        executorService.shutdown();
        executorService.awaitTermination(10, TimeUnit.MINUTES);

        System.out.println(treeIndex);
    }

    void addToIndex(TreeIndex treeIndex) {
        ThreadLocalRandom current = ThreadLocalRandom.current();
        for (long i = 0; i < 10; i++) {
            for (long j = 0; j < 10; j++) {
                for (long k = 0; k < 10; k++) {
                    treeIndex.addNewValue(
                            Row.of(
                                    Cell.of(current.nextLong(3)),
                                    Cell.of(current.nextLong(3)),
                                    Cell.of(current.nextLong(10)))
                    );
                }
            }
        }
    }
}
