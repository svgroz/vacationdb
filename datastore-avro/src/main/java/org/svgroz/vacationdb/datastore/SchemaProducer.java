package org.svgroz.vacationdb.datastore;

import org.apache.avro.Schema;
import org.svgroz.vacationdb.datastore.model.table.ITableMetadata;

public interface SchemaProducer {
    Schema createSchemaFromTableMetadata(ITableMetadata table);
}
