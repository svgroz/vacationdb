package org.svgroz.vacationdb.datastore;

import org.apache.avro.Schema;
import org.apache.avro.SchemaBuilder;
import org.junit.jupiter.api.Test;

public class Foo {
    @Test
    void test1() throws Exception {
        Schema schema = SchemaBuilder.record("foo")
                .prop("prop1", "value1")
                .fields()
                .endRecord();

        System.out.println(schema);
    }
}
