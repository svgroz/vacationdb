package org.svgroz.vacationdb.datastore.impl.model.index;

import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.list.MutableList;
import org.svgroz.vacationdb.datastore.api.model.cell.Cell;
import org.svgroz.vacationdb.datastore.impl.model.index.tree.DataNode;
import org.svgroz.vacationdb.datastore.impl.model.index.tree.RootNode;
import org.svgroz.vacationdb.datastore.api.model.row.Row;

import java.util.Comparator;
import java.util.Objects;
import java.util.Optional;
import java.util.StringJoiner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.Supplier;

/**
 * @author Simon Grozovsky svgroz@outlook.com
 */
public class TreeIndex {
    private static final Comparator<DataNode> DATA_NODE_COMPARATOR = Comparator.comparing(DataNode::getValue);
    private final AtomicReference<RootNode> root;
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock(false);
    private final Supplier<Long> versionSupplier;
    private final AtomicInteger addedWithoutBlocking = new AtomicInteger(0);
    private final AtomicInteger addedWithBlocking = new AtomicInteger(0);

    public TreeIndex(final Supplier<Long> versionSupplier) {
        this.versionSupplier = Objects.requireNonNull(versionSupplier, "versionSupplier is null");
        this.root = new AtomicReference<>(new RootNode(versionSupplier.get(), Lists.immutable.empty()));
    }

    RootNode addNewValue(final Row row) {

        readWriteLock.readLock().lock();
        try {
            boolean rootUpdated;
            // TODO replace with constructor argument
            int attempts = 20;

            RootNode newRootNode;
            do {
                attempts = attempts - 1;
                final RootNode currentRoot = root.get();
                newRootNode = addNewValue(row, currentRoot);
                rootUpdated = root.compareAndSet(currentRoot, newRootNode);
            } while (attempts > 0 && !rootUpdated);

            if (rootUpdated) {
                addedWithoutBlocking.incrementAndGet();
                return newRootNode;
            }
        } finally {
            readWriteLock.readLock().unlock();
        }

        readWriteLock.writeLock().lock();
        try {
            RootNode currentRoot;
            RootNode newRootNode;
            do {
                currentRoot = root.get();
                newRootNode = addNewValue(row, currentRoot);
            } while (!root.compareAndSet(currentRoot, newRootNode));
            addedWithBlocking.incrementAndGet();
            return newRootNode;
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    RootNode addNewValue(Row row, RootNode rootNode) {
        final ImmutableList<Cell> cells = row.getCells();

        DataNode dataNode = new DataNode(cells.get(cells.size() - 1), Lists.immutable.empty());
        for (int i = cells.size() - 2; i >= 0; i--) {
            dataNode = new DataNode(cells.get(i), Lists.immutable.of(dataNode));
        }

        return new RootNode(
                versionSupplier.get(),
                foo(rootNode.getChildren(), dataNode)
        );

    }

    ImmutableList<DataNode> foo(final ImmutableList<DataNode> rootSource, DataNode value) {
        ImmutableList<DataNode> source = rootSource;
        MutableList<DataNode> way = Lists.mutable.empty();

        for (; ; ) {
            int index = source.binarySearch(value, DATA_NODE_COMPARATOR);
            final Optional<DataNode> any = index > -1 ? Optional.of(source.get(index)) : Optional.empty();

            if (any.isEmpty() || value.getChildren().isEmpty()) {
                break;
            } else {
                source = any.get().getChildren();
                value = value.getChildren().get(0);
            }

            way.add(any.get());
        }

        DataNode stepNode = value;

        for (int i = way.size() - 1; i >= 0; i--) {
            DataNode dataNode = way.get(i);

            stepNode = new DataNode(
                    dataNode.getValue(),
                    replace(dataNode.getChildren(), stepNode)
            );
        }

        return replace(rootSource, stepNode);
    }

    private ImmutableList<DataNode> replace(final ImmutableList<DataNode> dataNodes, final DataNode dataNode) {

//        MutableSortedSet<DataNode> result = SortedSets.mutable.withAll(DATA_NODE_COMPARATOR, dataNodes);
//        result.remove(dataNode);
//        result.add(dataNode);
//        return Lists.immutable.withAll(result);

        final MutableList<DataNode> result = Lists.mutable.withInitialCapacity(dataNodes.size() + 1);
        boolean dataNodeIsAdded = false;
        for (final DataNode node : dataNodes) {
            if (dataNodeIsAdded) {
                result.add(node);
            } else {
                final int compare = DATA_NODE_COMPARATOR.compare(node, dataNode);
                if (compare > 0) {
                    result.add(dataNode);
                    result.add(node);
                    dataNodeIsAdded = true;
                } else if (compare < 0) {
                    result.add(node);
                } else {
                    result.add(dataNode);
                    dataNodeIsAdded = true;
                }
            }
        }
        if (!dataNodeIsAdded) {
            result.add(dataNode);
        }

        return result.toImmutable();
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", TreeIndex.class.getSimpleName() + "[", "]")
                .add("addedWithoutBlocking=" + addedWithoutBlocking)
                .add("addedWithBlocking=" + addedWithBlocking)
                .toString();
    }
}
