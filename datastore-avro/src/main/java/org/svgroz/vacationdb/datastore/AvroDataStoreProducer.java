package org.svgroz.vacationdb.datastore;

import org.apache.avro.Schema;
import org.svgroz.vacationdb.datastore.model.Table;

import java.util.Objects;

public class AvroDataStoreProducer implements DataStoreProducer {

    private final SchemaProducer schemaProducer;

    public AvroDataStoreProducer(final SchemaProducer schemaProducer) {
        this.schemaProducer = Objects.requireNonNull(schemaProducer, "schemaProducer is null");
    }

    @Override
    public void createTable(final Table table) {
        final Schema schema = schemaProducer.createSchemaFromTableMetadata(
                Objects.requireNonNull(table, "table is null")
        );
    }
}
