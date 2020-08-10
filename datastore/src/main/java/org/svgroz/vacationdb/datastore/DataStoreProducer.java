package org.svgroz.vacationdb.datastore;

import org.svgroz.vacationdb.datastore.model.Table;

public interface DataStoreProducer {
    void createTable(Table table);
}
