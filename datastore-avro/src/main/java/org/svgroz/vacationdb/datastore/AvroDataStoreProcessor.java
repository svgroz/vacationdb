package org.svgroz.vacationdb.datastore;

import org.apache.avro.Schema;
import org.svgroz.vacationdb.datastore.model.table.TableMetadata;

import java.util.Objects;

public class AvroDataStoreProcessor implements DataStoreProcessor {

    private final SchemaProducer schemaProducer;

    public AvroDataStoreProcessor(final SchemaProducer schemaProducer) {
        this.schemaProducer = Objects.requireNonNull(schemaProducer, "schemaProducer is null");
    }

    @Override
    public void createTable(final TableMetadata table) {
        final Schema schema = schemaProducer.createSchemaFromTableMetadata(
                Objects.requireNonNull(table, "table is null")
        );
    }
}
