package org.svgroz.vacationdb.datastore.impl;

import org.apache.avro.Schema;
import org.apache.avro.SchemaBuilder;
import org.svgroz.vacationdb.datastore.SchemaProducer;
import org.svgroz.vacationdb.datastore.exception.UnsupportedColumnType;
import org.svgroz.vacationdb.datastore.model.Column;
import org.svgroz.vacationdb.datastore.model.Table;

import java.util.List;
import java.util.Objects;

public class SchemaProducerImpl implements SchemaProducer {

    /**
     * Method for mapping from table metadata description to avro schema
     * @param table cannot be null
     * @return avro schema for given table
     * @throws NullPointerException if table is null
     */
    @Override
    public Schema createSchemaFromTableMetadata(Table table) throws NullPointerException {
        Objects.requireNonNull(table, "table is null");

        final String recordName = table.getName();

        SchemaBuilder.FieldAssembler<Schema> fields = SchemaBuilder.record(recordName)
                .fields();

        List<Column> columns = table.getColumns();

        for (Column column : columns) {
            SchemaBuilder.FieldBuilder<Schema> fieldBuilder = fields.name(column.getName());

            switch (column.getType()) {
                case BOOLEAN:
                    fields = fieldBuilder.type().optional().booleanType();
                    break;
                case LONG:
                    fields = fieldBuilder.type().optional().longType();
                    break;
                case DOUBLE:
                    fields = fieldBuilder.type().optional().doubleType();
                    break;
                case STRING:
                    fields = fieldBuilder.type().optional().stringType();
                    break;
                default:
                    throw new UnsupportedColumnType(column.getType());
            }
        }

        return fields.endRecord();
    }
}
