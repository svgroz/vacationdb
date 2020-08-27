package org.svgroz.vacationdb.datastore.api.model.column;

import java.util.ServiceLoader;

/**
 * @author Simon Grozovsky svgroz@outlook.com
 */
public final class Columns {

    public static final KeyColumnFactory key = ServiceLoader.load(KeyColumnFactory.class).findFirst().orElseThrow();

    public static final RegularColumnFactory regular = ServiceLoader.load(RegularColumnFactory.class).findFirst().orElseThrow();

    private Columns() {

    }


}
