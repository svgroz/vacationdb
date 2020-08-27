package org.svgroz.vacationdb.datastore.api;

import org.svgroz.vacationdb.datastore.api.model.table.TableMetadata;

public interface DataStoreProcessor {
    void createTable(TableMetadata table);
}
