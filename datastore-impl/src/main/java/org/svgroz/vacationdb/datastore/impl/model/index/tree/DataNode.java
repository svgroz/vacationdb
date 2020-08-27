package org.svgroz.vacationdb.datastore.impl.model.index.tree;

import org.eclipse.collections.api.list.ImmutableList;
import org.svgroz.vacationdb.datastore.api.model.cell.Cell;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * @author Simon Grozovsky svgroz@outlook.com
 */
public class DataNode {
    private final Cell value;
    private final ImmutableList<DataNode> children;

    public DataNode(final Cell value, final ImmutableList<DataNode> children) {
        this.value = value;
        this.children = children;
    }

    public Cell getValue() {
        return value;
    }

    public ImmutableList<DataNode> getChildren() {
        return children;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof DataNode)) return false;
        final DataNode dataNode = (DataNode) o;
        return Objects.equals(value, dataNode.value) &&
                Objects.equals(children, dataNode.children);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, children);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", DataNode.class.getSimpleName() + "[", "]")
                .add("value=" + value)
                .add("children=" + children)
                .toString();
    }
}
