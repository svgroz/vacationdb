package org.svgroz.vacationdb.datastore.impl;

import org.apache.avro.Schema;
import org.apache.avro.SchemaBuilder;
import org.svgroz.vacationdb.datastore.SchemaProducer;
import org.svgroz.vacationdb.datastore.exception.UnsupportedColumnTypeException;
import org.svgroz.vacationdb.datastore.model.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

public class SchemaProducerImpl implements SchemaProducer {

    private final Map<Class<? extends Cell>, Function<SchemaBuilder.FieldBuilder<Schema>, SchemaBuilder.FieldAssembler<Schema>>> classFunctionMap =
            Map.of(
                    BooleanCell.class, (fieldBuilder) -> fieldBuilder.type().optional().booleanType(),
                    LongCell.class, (fieldBuilder) -> fieldBuilder.type().optional().longType(),
                    DoubleCell.class, (fieldBuilder) -> fieldBuilder.type().optional().doubleType(),
                    StringCell.class, (fieldBuilder) -> fieldBuilder.type().optional().stringType()
            );

    /**
     * Method for mapping from table metadata description to avro schema
     *
     * @param table cannot be null
     * @return avro schema for given table
     * @throws NullPointerException if table is null
     */
    @Override
    public Schema createSchemaFromTableMetadata(final Table table) throws NullPointerException {
        Objects.requireNonNull(table, "table is null");

        final String recordName = table.getName();

        SchemaBuilder.FieldAssembler<Schema> fields = SchemaBuilder.record(recordName)
                .fields();

        List<Column> columns = table.getMetadata().getColumns();

        for (Column column : columns) {
            SchemaBuilder.FieldBuilder<Schema> fieldBuilder = fields.name(column.getName());

            var fieldBuilderFunction = classFunctionMap.get(column.getType());
            if (fieldBuilderFunction == null) {
                throw new UnsupportedColumnTypeException(column.getType());
            }

            fields = fieldBuilderFunction.apply(fieldBuilder);
        }

        return fields.endRecord();
    }
}
