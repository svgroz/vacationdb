package org.svgroz.vacationdb.datastore.impl;

import org.apache.avro.Schema;
import org.apache.avro.SchemaBuilder;
import org.svgroz.vacationdb.datastore.SchemaProducer;
import org.svgroz.vacationdb.datastore.exception.UnsupportedColumnTypeException;
import org.svgroz.vacationdb.datastore.model.DataType;
import org.svgroz.vacationdb.datastore.model.column.Column;
import org.svgroz.vacationdb.datastore.model.table.TableMetadata;

import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

public class SchemaProducerImpl implements SchemaProducer {

    private final Map<DataType, Function<SchemaBuilder.FieldBuilder<Schema>, SchemaBuilder.FieldAssembler<Schema>>> classFunctionMap =
            new EnumMap<>(
                    Map.of(
                            DataType.BOOLEAN, (fieldBuilder) -> fieldBuilder.type().optional().booleanType(),
                            DataType.LONG, (fieldBuilder) -> fieldBuilder.type().optional().longType(),
                            DataType.DOUBLE, (fieldBuilder) -> fieldBuilder.type().optional().doubleType(),
                            DataType.STRING, (fieldBuilder) -> fieldBuilder.type().optional().stringType()
                    )
            );

    /**
     * Method for mapping from table metadata description to avro schema
     *
     * @param metadata cannot be null
     * @return avro schema for given table
     * @throws NullPointerException if table is null
     */
    @Override
    public Schema createSchemaFromTableMetadata(final TableMetadata metadata) {
        Objects.requireNonNull(metadata, "metadata is null");

        final String recordName = metadata.getName();

        SchemaBuilder.FieldAssembler<Schema> fields = SchemaBuilder.record(recordName)
                .fields();



        for (int i = 0; i < metadata.columnsCount(); i++) {
            final Column column = metadata.getColumnByIndex(i);

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
