import org.svgroz.vacationdb.datastore.api.model.cell.CellFactory;

/**
 * @author Simon Grozovsky svgroz@outlook.com
 */
module org.svgroz.vacationdb {
    requires org.svgroz.vacationdb.datastore.api;
    uses CellFactory;
}