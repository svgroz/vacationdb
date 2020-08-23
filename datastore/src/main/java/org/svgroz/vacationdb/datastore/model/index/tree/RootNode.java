package org.svgroz.vacationdb.datastore.model.index.tree;

import org.eclipse.collections.api.list.ImmutableList;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * @author Simon Grozovsky svgroz@outlook.com
 */
public class RootNode {

    private final long version;
    private final ImmutableList<DataNode> children;


    public RootNode(final long version, final ImmutableList<DataNode> children) {
        this.version = version;
        this.children = children;
    }

    public long getVersion() {
        return version;
    }

    public ImmutableList<DataNode> getChildren() {
        return children;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof RootNode)) return false;
        final RootNode rootNode = (RootNode) o;
        return version == rootNode.version &&
                Objects.equals(children, rootNode.children);
    }

    @Override
    public int hashCode() {
        return Objects.hash(version, children);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", RootNode.class.getSimpleName() + "[", "]")
                .add("version=" + version)
                .add("children=" + children)
                .toString();
    }
}
