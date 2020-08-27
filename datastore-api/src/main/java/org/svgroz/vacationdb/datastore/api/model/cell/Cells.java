package org.svgroz.vacationdb.datastore.api.model.cell;

import org.svgroz.vacationdb.datastore.api.model.table.TableFactory;

import java.util.ServiceLoader;

/**
 * @author Simon Grozovsky svgroz@outlook.com
 */
public final class Cells {
    public static final CellFactory factory = ServiceLoader.load(CellFactory.class).findFirst().orElseThrow();
}
