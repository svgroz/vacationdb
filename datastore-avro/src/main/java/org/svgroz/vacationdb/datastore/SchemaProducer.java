package org.svgroz.vacationdb.datastore;

import org.apache.avro.Schema;
import org.svgroz.vacationdb.datastore.model.table.TableMetadata;

public interface SchemaProducer {
    Schema createSchemaFromTableMetadata(TableMetadata table);
}
