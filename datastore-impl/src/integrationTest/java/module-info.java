import org.svgroz.vacationdb.datastore.api.model.table.TableMetadataFactory;

/**
 * @author Simon Grozovsky svgroz@outlook.com
 */
open module org.svgroz.vacationdb.datastore.impl.test {
    requires org.junit.jupiter.api;
    requires org.eclipse.collections.api;

    requires org.svgroz.vacationdb.datastore.api;
    requires org.svgroz.vacationdb.datastore.impl;

    uses org.svgroz.vacationdb.datastore.api.model.cell.CellFactory;
    uses org.svgroz.vacationdb.datastore.api.model.column.ColumnFactory;
    uses org.svgroz.vacationdb.datastore.api.model.row.RowFactory;
    uses org.svgroz.vacationdb.datastore.api.model.table.TableFactory;
    uses TableMetadataFactory;
}
