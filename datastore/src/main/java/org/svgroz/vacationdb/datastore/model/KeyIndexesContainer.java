package org.svgroz.vacationdb.datastore.model;

import org.svgroz.vacationdb.datastore.exception.KeyIndexesIsEmptyException;

import java.util.*;
import java.util.stream.IntStream;

/**
 * This class has guaranties that key indexes does not contains null values,
 * does not contains same values, does not contains indexes less than zero
 */
public class KeyIndexesContainer implements Iterable<Integer> {
    private final List<Integer> indexes;
    private final int maxId;

    KeyIndexesContainer(final List<Integer> indexes) {
        Objects.requireNonNull(indexes);
        if (indexes.isEmpty()) {
            throw new KeyIndexesIsEmptyException();
        }

        this.indexes = List.copyOf(indexes);
        this.maxId = Collections.max(indexes);
    }

    public List<Integer> getIndexes() {
        return indexes;
    }

    public int getMaxId() {
        return maxId;
    }

    @Override
    public Iterator<Integer> iterator() {
        return indexes.iterator();
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", KeyIndexesContainer.class.getSimpleName() + "[", "]")
                .add("indexes=" + indexes)
                .add("maxId=" + maxId)
                .toString();
    }
}
