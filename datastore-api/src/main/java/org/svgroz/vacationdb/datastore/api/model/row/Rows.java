package org.svgroz.vacationdb.datastore.api.model.row;

import java.util.ServiceLoader;

/**
 * @author Simon Grozovsky svgroz@outlook.com
 */
public final class Rows {
    public static final RowFactory factory = ServiceLoader.load(RowFactory.class).findFirst().orElseThrow();
}
