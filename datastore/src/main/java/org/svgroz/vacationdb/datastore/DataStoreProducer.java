package org.svgroz.vacationdb.datastore;

import org.svgroz.vacationdb.datastore.model.table.TableMetadata;

public interface DataStoreProducer {
    void createTable(TableMetadata table);
}
