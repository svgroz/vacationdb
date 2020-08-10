package org.svgroz.vacationdb.datastore;

import org.apache.avro.Schema;
import org.svgroz.vacationdb.datastore.model.Table;

public interface SchemaProducer {
    Schema createSchemaFromTableMetadata(Table table);
}
