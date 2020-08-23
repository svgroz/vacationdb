package org.svgroz.vacationdb.datastore.impl;

import org.apache.avro.Schema;
import org.eclipse.collections.api.factory.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.svgroz.vacationdb.datastore.model.DataType;
import org.svgroz.vacationdb.datastore.model.column.Column;
import org.svgroz.vacationdb.datastore.model.table.TableMetadata;

class SchemaProducerImplTest {

    @Test
    void createSchemaFromTableMetadata() {
        SchemaProducerImpl schemaProducer = new SchemaProducerImpl();

        final String tableName = "FOO";
        final String columnName = "BAR";
        final Column column = Column.of(columnName, DataType.BOOLEAN, true);

        TableMetadata table = TableMetadata.of(
                tableName,
                Lists.immutable.of(column)
        );

        Schema schema = schemaProducer.createSchemaFromTableMetadata(table);

        Assertions.assertNotNull(schema);
        Assertions.assertEquals(tableName, schema.getName());
        Assertions.assertNotNull(schema.getFields());
        Assertions.assertEquals(1, schema.getFields().size());
        Schema.Field field = schema.getFields().get(0);

        Assertions.assertNotNull(field);
        Assertions.assertEquals(columnName, field.name());
        // TODO how to check field type?
    }
}