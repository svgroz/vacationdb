package org.svgroz.vacationdb.datastore.model.table;

import org.eclipse.collections.api.block.predicate.Predicate;
import org.eclipse.collections.api.block.predicate.primitive.ObjectIntPredicate;
import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.map.primitive.ImmutableObjectIntMap;
import org.eclipse.collections.api.map.primitive.MutableObjectIntMap;
import org.eclipse.collections.api.tuple.primitive.ObjectIntPair;
import org.eclipse.collections.impl.factory.primitive.ObjectIntMaps;
import org.svgroz.vacationdb.datastore.exception.ColumnsContainsNullException;
import org.svgroz.vacationdb.datastore.exception.ColumnsContainsSameNamesException;
import org.svgroz.vacationdb.datastore.exception.ColumnsDoesNotContainsKeysException;
import org.svgroz.vacationdb.datastore.exception.ColumnsInExpressionDoesNotMatchMetadataColumns;
import org.svgroz.vacationdb.datastore.exception.EmptyColumnsException;
import org.svgroz.vacationdb.datastore.exception.RowDoesNotMatchTableWidth;
import org.svgroz.vacationdb.datastore.exception.RowHasIncompatibleCellTypeOrderException;
import org.svgroz.vacationdb.datastore.model.cell.Cell;
import org.svgroz.vacationdb.datastore.model.column.Column;
import org.svgroz.vacationdb.datastore.model.row.Row;
import org.svgroz.vacationdb.datastore.statement.Condition;
import org.svgroz.vacationdb.datastore.statement.ConditionType;

import java.util.Objects;
import java.util.StringJoiner;
import java.util.stream.Stream;

/**
 * That class is null safety, thread safety, and immutable.
 *
 * @author Simon Grozovsky svgroz@outlook.com
 */
public class MutableSortedTable implements MutableTable {
    private final TableMetadata metadata;
    private final MutableList<Row> unsortedData = Lists.mutable.empty();
    private final MutableObjectIntMap<Row> unsortedDataIndex = ObjectIntMaps.mutable.empty();

    /**
     * @param name    cannot be null
     * @param columns cannot be null, empty, or contains null values
     * @throws NullPointerException                if name or columns is null
     * @throws EmptyColumnsException               if columns is empty
     * @throws ColumnsContainsNullException        if columns contains null values
     * @throws ColumnsContainsSameNamesException   if columns contains same names
     * @throws ColumnsDoesNotContainsKeysException if columns does not contains key columns
     */
    public MutableSortedTable(final String name, final ImmutableList<Column> columns) {
        Objects.requireNonNull(name, "name is null");
        Objects.requireNonNull(columns, "columns is null");

        this.metadata = TableMetadata.of(name, columns);

        final MutableList<Integer> keyIndexes = Lists.mutable.empty();

        for (int i = 0; i < metadata.columnsCount(); i++) {
            if (metadata.getColumnByIndex(i).isKey()) {
                keyIndexes.add(i);
            }
        }

        if (keyIndexes.isEmpty()) {
            throw new ColumnsDoesNotContainsKeysException(columns);
        }

//        this.unsortedDataIndex = new TreeMap<>(new RowComparator(keyIndexes.toImmutable()));
    }

    @Override
    public String getName() {
        return metadata.getName();
    }

    /**
     * @param row supposed to be not null
     * @return false if table already has had row with same keys
     * @throws RowDoesNotMatchTableWidth                if row wider than table
     * @throws RowHasIncompatibleCellTypeOrderException if cells has incompatible order
     */
    @Override
    public boolean addRow(final Row row) {
        Objects.requireNonNull(row);

        if (row.getCells().size() != metadata.columnsCount()) {
            throw new RowDoesNotMatchTableWidth(row, metadata);
        }

        final MutableList<Cell> indexPart = Lists.mutable.empty();
        final MutableList<Cell> dataPart = Lists.mutable.empty();

        for (int i = 0; i < row.getCells().size(); i++) {
            final Cell cell = row.getCells().get(i);
            final Column column = metadata.getColumnByIndex(i);

            if (!Cell.isEmpty(cell) && column.getType() != cell.supportedType()) {
                throw new RowHasIncompatibleCellTypeOrderException(row, metadata);
            }

            if (column.isKey()) {
                indexPart.add(cell);
            } else {
                dataPart.add(cell);
            }
        }


        final Row indexRow = Row.of(indexPart.toImmutable());
        final Row dataRow = Row.of(dataPart.toImmutable());

        int dataIndex = unsortedData.size();
        unsortedData.add(dataRow);

        return unsortedDataIndex.getIfAbsentPut(indexRow, dataIndex) == dataIndex;
    }

    // TODO rewrite
    ImmutableList<Row> select(final ImmutableList<Condition> conditions) {

        final MutableList<ObjectIntPredicate<Row>> predicates = Lists.mutable.empty();

        for (int i = 0; i < conditions.size(); i++) {
            final Condition condition = conditions.get(i);
            if (metadata.indexOf(condition.column()) != i) {
                throw new ColumnsInExpressionDoesNotMatchMetadataColumns(conditions, metadata);
            }

            predicates.add(buildPredicate(i, condition));
        }

        if (predicates.isEmpty()) {
            return Lists.immutable.empty();
        }

        final ObjectIntPredicate<Row> selectPredicate = (row, i) -> {
            for (final ObjectIntPredicate<Row> predicate : predicates) {
                if (!predicate.accept(row, i)) {
                    return false;
                }
            }
            return true;
        };

        final ImmutableObjectIntMap<Row> indexes = unsortedDataIndex.select(selectPredicate).toImmutable();

        final MutableList<Row> result = Lists.mutable.ofInitialCapacity(indexes.size());

        for (final ObjectIntPair<Row> rowObjectIntPair : indexes.keyValuesView()) {
            final int indexInData = rowObjectIntPair.getTwo();

            final Row indexPart = rowObjectIntPair.getOne();
            final Row dataPart = unsortedData.get(indexInData);

            result.add(Row
                    .of(
                            Lists.immutable.fromStream(
                                    Stream.concat(
                                            indexPart.getCells().stream(),
                                            dataPart.getCells().stream()
                                    )
                            )
                    )
            );
        }

        return result.toImmutable();
    }

    ObjectIntPredicate<Row> buildPredicate(final int rowIndex, final Condition condition) {
        final ConditionType conditionType = condition.conditionType();
        final Cell cell = condition.cell();
        switch (conditionType) {
            case EQUALS:
                return (row, i) -> row.getCells().get(rowIndex).compareTo(cell) == 0;
            default:
                // TODO replace with DataStoreException condition
                throw new RuntimeException("Unsupported condition type");
        }
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof MutableSortedTable)) return false;
        final MutableSortedTable table = (MutableSortedTable) o;
        return Objects.equals(metadata, table.metadata) &&
                Objects.equals(unsortedData, table.unsortedData) &&
                Objects.equals(unsortedDataIndex, table.unsortedDataIndex);
    }

    @Override
    public int hashCode() {
        return Objects.hash(metadata, unsortedData, unsortedDataIndex);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", MutableSortedTable.class.getSimpleName() + "[", "]")
                .add("metadata=" + metadata)
                .add("unsortedData=" + unsortedData)
                .add("unsortedDataIndex=" + unsortedDataIndex)
                .toString();
    }
}
