import org.svgroz.vacationdb.datastore.api.model.table.TableMetadataFactory;

/**
 * @author Simon Grozovsky svgroz@outlook.com
 */
module org.svgroz.vacationdb.datastore.api {
    requires org.eclipse.collections.api;

    uses org.svgroz.vacationdb.datastore.api.model.cell.CellFactory;
    uses org.svgroz.vacationdb.datastore.api.model.column.ColumnFactory;
    uses org.svgroz.vacationdb.datastore.api.model.row.RowFactory;
    uses org.svgroz.vacationdb.datastore.api.model.table.TableFactory;
    uses TableMetadataFactory;

    exports org.svgroz.vacationdb.datastore.api;
    exports org.svgroz.vacationdb.datastore.api.exception;
    exports org.svgroz.vacationdb.datastore.api.model;
    exports org.svgroz.vacationdb.datastore.api.model.cell;
    exports org.svgroz.vacationdb.datastore.api.model.column;
    exports org.svgroz.vacationdb.datastore.impl.model.index;
    exports org.svgroz.vacationdb.datastore.api.model.row;
    exports org.svgroz.vacationdb.datastore.api.model.table;
    exports org.svgroz.vacationdb.datastore.api.statement;
}
