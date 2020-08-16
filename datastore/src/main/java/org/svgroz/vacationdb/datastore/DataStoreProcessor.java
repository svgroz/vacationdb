package org.svgroz.vacationdb.datastore;

import org.svgroz.vacationdb.datastore.model.table.TableMetadata;

public interface DataStoreProcessor {
    void createTable(TableMetadata table);
}
