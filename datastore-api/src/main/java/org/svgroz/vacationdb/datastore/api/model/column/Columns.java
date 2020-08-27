package org.svgroz.vacationdb.datastore.api.model.column;

import org.svgroz.vacationdb.datastore.api.model.table.TableFactory;

import java.util.ServiceLoader;

/**
 * @author Simon Grozovsky svgroz@outlook.com
 */
public final class Columns {

    public static final ColumnFactory factory = ServiceLoader.load(ColumnFactory.class).findFirst().orElseThrow();

    private Columns() {

    }


}
