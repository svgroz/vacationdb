package org.svgroz.vacationdb.datastore.api.model.table;

import java.util.ServiceLoader;

/**
 * @author Simon Grozovsky svgroz@outlook.com
 */
public final class Tables {

    public static final TableFactory tableFactory = ServiceLoader.load(TableFactory.class).findFirst().orElseThrow();

    public static final TableMetadataFactory tableMetadataFactory = ServiceLoader.load(TableMetadataFactory.class).findFirst().orElseThrow();

    private Tables() {

    }
}
