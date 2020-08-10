package org.svgroz.vacationdb.datastore;

import org.apache.avro.Schema;
import org.apache.avro.SchemaBuilder;
import org.svgroz.vacationdb.datastore.exception.UnsupportedColumnType;
import org.svgroz.vacationdb.datastore.model.Column;
import org.svgroz.vacationdb.datastore.model.Table;

import java.util.EnumMap;
import java.util.List;
import java.util.Objects;

public class AvroDataStoreProducer implements DataStoreProducer {

    @Override
    public void createTable(Table table) {
        Objects.requireNonNull(table, "table is null");

        String recordName = Objects.requireNonNull(table.getName(), "table.name is null");

        SchemaBuilder.FieldAssembler<Schema> fields = SchemaBuilder.record(recordName)
                .fields();

        List<Column> columns = Objects.requireNonNull(table.getColumns());

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


    }
}
