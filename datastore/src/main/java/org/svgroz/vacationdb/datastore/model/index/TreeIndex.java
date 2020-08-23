package org.svgroz.vacationdb.datastore.model.index;

import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.list.MutableList;
import org.svgroz.vacationdb.datastore.model.cell.Cell;
import org.svgroz.vacationdb.datastore.model.index.tree.DataNode;
import org.svgroz.vacationdb.datastore.model.index.tree.RootNode;
import org.svgroz.vacationdb.datastore.model.row.Row;

import java.util.Comparator;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;

/**
 * @author Simon Grozovsky svgroz@outlook.com
 */
public class TreeIndex {
    private final AtomicReference<RootNode> root;
    private final AtomicLong versionSequence = new AtomicLong(Long.MIN_VALUE);

    public TreeIndex() {
        this.root = new AtomicReference<>(new RootNode(versionSequence.getAndIncrement(), Lists.immutable.empty()));
    }

    RootNode addNewValue(final Row row) {
        final RootNode currentRoot = root.get();

        for (; ; ) {
            final RootNode rootNode = addNewValue(row, currentRoot);
            if (root.compareAndSet(currentRoot, rootNode)) {
                return rootNode;
            }
        }
    }

    RootNode addNewValue(Row row, RootNode rootNode) {
        final ImmutableList<Cell> cells = row.getCells();

        DataNode dataNode = new DataNode(cells.get(cells.size() - 1), Lists.immutable.empty());
        for (int i = cells.size() - 2; i >= 0; i--) {
            dataNode = new DataNode(cells.get(i), Lists.immutable.of(dataNode));
        }

        return new RootNode(
                versionSequence.incrementAndGet(),
                foo(rootNode.getChildren(), dataNode)
        );

    }

    ImmutableList<DataNode> foo(final ImmutableList<DataNode> rootSource, DataNode value) {
        ImmutableList<DataNode> source = rootSource;
        MutableList<DataNode> way = Lists.mutable.empty();

        for (; ; ) {
            final Cell cellValue = value.getValue();
            final Optional<DataNode> any = source.stream()
                    .filter(c -> c.getValue().equals(cellValue))
                    .findAny();

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
            final Cell currentCellValue = stepNode.getValue();

            Stream<DataNode> newChildrenStream = Stream.concat(
                    dataNode.getChildren().stream().filter(c -> !c.getValue().equals(currentCellValue)),
                    Stream.of(stepNode)
            ).sorted(Comparator.comparing(DataNode::getValue));

            stepNode = new DataNode(
                    dataNode.getValue(),
                    Lists.immutable.fromStream(newChildrenStream)
            );
        }

        final Cell currentCellValue = stepNode.getValue();

        return Lists.immutable.fromStream(
                Stream.concat(
                        rootSource.stream().filter(c -> !c.getValue().equals(currentCellValue)),
                        Stream.of(stepNode)
                ).sorted(Comparator.comparing(DataNode::getValue))
        );
    }
}

