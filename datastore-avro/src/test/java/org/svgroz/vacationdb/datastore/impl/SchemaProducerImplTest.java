package org.svgroz.vacationdb.datastore.impl;

import org.apache.avro.Schema;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.svgroz.vacationdb.datastore.model.Column;
import org.svgroz.vacationdb.datastore.model.ColumnType;
import org.svgroz.vacationdb.datastore.model.Table;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SchemaProducerImplTest {

    @Test
    void createSchemaFromTableMetadata() {
        SchemaProducerImpl schemaProducer = new SchemaProducerImpl();

        final String tableName = "FOO";
        final String columnName = "BAR";
        final ColumnType columnType = ColumnType.STRING;
        final Column column = new Column(columnName, columnType);

        Table table = new Table(
                tableName,
                List.of(column)
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