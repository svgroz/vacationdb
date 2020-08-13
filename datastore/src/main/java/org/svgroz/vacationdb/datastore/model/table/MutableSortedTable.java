package org.svgroz.vacationdb.datastore.model.table;

import org.svgroz.vacationdb.datastore.exception.ColumnsContainsNullException;
import org.svgroz.vacationdb.datastore.exception.ColumnsContainsSameNamesException;
import org.svgroz.vacationdb.datastore.exception.ColumnsDoesNotContainsKeysException;
import org.svgroz.vacationdb.datastore.exception.EmptyColumnsException;
import org.svgroz.vacationdb.datastore.exception.RowDoesNotMatchTableWidth;
import org.svgroz.vacationdb.datastore.exception.RowHasIncompatibleCellTypeOrderException;
import org.svgroz.vacationdb.datastore.model.cell.Cell;
import org.svgroz.vacationdb.datastore.model.column.Column;
import org.svgroz.vacationdb.datastore.model.row.Row;
import org.svgroz.vacationdb.datastore.model.row.RowComparator;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.TreeSet;

/**
 * That class is null safety, thread safety, and immutable.
 *
 * @author Simon Grozovsky svgroz@outlook.com
 */
public class MutableSortedTable implements MutableTable {
    private final TableMetadata metadata;
    private final TreeSet<Row> data;

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

        this.metadata = new TableMetadata(name, columns);

        final List<Integer> keyIndexes = new ArrayList<>();

        for (int i = 0; i < metadata.getColumns().size(); i++) {
            if (metadata.getColumns().get(i).isKey()) {
                keyIndexes.add(i);
            }
        }

        if (keyIndexes.isEmpty()) {
            throw new ColumnsDoesNotContainsKeysException(columns);
        }

        this.data = new TreeSet<>(new RowComparator(List.copyOf(keyIndexes)));
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

        for (int i = 0; i < row.getCells().size(); i++) {
            final Cell cell = row.getCells().get(i);

            if (Cell.isEmpty(cell)) {
                continue;
            }

            final Column column = metadata.getColumns().get(i);

            if (column.getType() != cell.supportedType()) {
                throw new RowHasIncompatibleCellTypeOrderException(row, metadata);
            }
        }

        return data.add(row);
    }
}
