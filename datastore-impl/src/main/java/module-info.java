import org.svgroz.vacationdb.datastore.api.model.table.TableMetadataFactory;
import org.svgroz.vacationdb.datastore.impl.model.cell.DefaultCellFactory;
import org.svgroz.vacationdb.datastore.impl.model.column.DefaultColumnFactory;
import org.svgroz.vacationdb.datastore.impl.model.row.DefaultRowFactory;
import org.svgroz.vacationdb.datastore.impl.model.table.DefaultTableFactory;
import org.svgroz.vacationdb.datastore.impl.model.table.DefaultTableMetadataFactory;

/**
 * @author Simon Grozovsky svgroz@outlook.com
 */
module org.svgroz.vacationdb.datastore.impl {
    requires org.eclipse.collections.api;
    requires org.svgroz.vacationdb.datastore.api;

    provides org.svgroz.vacationdb.datastore.api.model.cell.CellFactory
            with DefaultCellFactory;

    provides org.svgroz.vacationdb.datastore.api.model.column.ColumnFactory
            with DefaultColumnFactory;

    provides org.svgroz.vacationdb.datastore.api.model.row.RowFactory
            with DefaultRowFactory;

    provides org.svgroz.vacationdb.datastore.api.model.table.TableFactory
            with DefaultTableFactory;

    provides TableMetadataFactory
            with DefaultTableMetadataFactory;
}
