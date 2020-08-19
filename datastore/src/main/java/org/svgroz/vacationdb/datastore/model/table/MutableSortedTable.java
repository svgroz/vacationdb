package org.svgroz.vacationdb.datastore.model.table;

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
import org.svgroz.vacationdb.datastore.model.row.RowComparator;
import org.svgroz.vacationdb.datastore.statement.Condition;
import org.svgroz.vacationdb.datastore.statement.ConditionType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.SortedMap;
import java.util.StringJoiner;
import java.util.TreeMap;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * That class is null safety, thread safety, and immutable.
 *
 * @author Simon Grozovsky svgroz@outlook.com
 */
public class MutableSortedTable implements MutableTable {
    private final TableMetadata metadata;
    private final List<Row> unsortedData = new ArrayList<>();
    private final SortedMap<Row, Integer> unsortedDataIndex;

    /**
     * @param name    cannot be null
     * @param columns cannot be null, empty, or contains null values
     * @throws NullPointerException                if name or columns is null
     * @throws EmptyColumnsException               if columns is empty
     * @throws ColumnsContainsNullException        if columns contains null values
     * @throws ColumnsContainsSameNamesException   if columns contains same names
     * @throws ColumnsDoesNotContainsKeysException if columns does not contains key columns
     */
    public MutableSortedTable(final String name, final List<Column> columns) {
        Objects.requireNonNull(name, "name is null");
        Objects.requireNonNull(columns, "columns is null");

        this.metadata = TableMetadata.of(name, columns);

        final List<Integer> keyIndexes = new ArrayList<>();

        for (int i = 0; i < metadata.getColumns().size(); i++) {
            if (metadata.getColumns().get(i).isKey()) {
                keyIndexes.add(i);
            }
        }

        if (keyIndexes.isEmpty()) {
            throw new ColumnsDoesNotContainsKeysException(columns);
        }

        this.unsortedDataIndex = new TreeMap<>(new RowComparator(List.copyOf(keyIndexes)));
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

        if (row.getCells().size() != metadata.getColumns().size()) {
            throw new RowDoesNotMatchTableWidth(row, metadata);
        }

        final List<Cell> indexPart = new ArrayList<>();
        final List<Cell> dataPart = new ArrayList<>();

        for (int i = 0; i < row.getCells().size(); i++) {
            final Cell cell = row.getCells().get(i);
            final Column column = metadata.getColumns().get(i);

            if (!Cell.isEmpty(cell) && column.getType() != cell.supportedType()) {
                throw new RowHasIncompatibleCellTypeOrderException(row, metadata);
            }

            if (column.isKey()) {
                indexPart.add(cell);
            } else {
                dataPart.add(cell);
            }
        }

        int dataIndex = unsortedData.size();
        unsortedData.add(Row.of(dataPart));

        return unsortedDataIndex.put(Row.of(indexPart), dataIndex) == null;
    }

    List<Row> select(final List<Condition> conditions) {

        Stream<Map.Entry<Row, Integer>> stream = unsortedDataIndex.entrySet().stream();
        for (int i = 0; i < conditions.size(); i++) {
            final Condition condition = conditions.get(i);
            if (metadata.indexOf(condition.column()) != i) {
                throw new ColumnsInExpressionDoesNotMatchMetadataColumns(conditions, metadata.getColumns());
            }

            final Predicate<Row> rowPredicate = buildPredicate(i, condition);
            stream = stream.filter(entry -> rowPredicate.test(entry.getKey()));
        }

        return stream
                .map(entry ->
                        Stream.concat(
                                entry.getKey().getCells().stream(),
                                unsortedData.get(entry.getValue()).getCells().stream()
                        ).collect(Collectors.toUnmodifiableList())
                )
                .map(Row::of)
                .collect(Collectors.toUnmodifiableList());
    }

    Predicate<Row> buildPredicate(final int rowIndex, final Condition condition) {
        final ConditionType conditionType = condition.conditionType();
        final Cell cell = condition.cell();
        switch (conditionType) {
            case EQUALS:
                return (row) -> row.getCells().get(rowIndex).compareTo(cell) == 0;
            default:
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
