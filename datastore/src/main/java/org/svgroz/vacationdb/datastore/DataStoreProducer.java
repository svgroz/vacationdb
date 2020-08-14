package org.svgroz.vacationdb.datastore;

import org.svgroz.vacationdb.datastore.model.table.ITableMetadata;

public interface DataStoreProducer {
    void createTable(ITableMetadata table);
}
