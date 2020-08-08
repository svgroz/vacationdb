package org.svgroz.vacation;

import org.antlr.v4.runtime.*;
import org.apache.avro.Schema;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.specific.SpecificDatumWriter;
import org.apache.avro.specific.SpecificRecord;
import org.svgroz.vacationdb.parser.VQLBaseListener;
import org.svgroz.vacationdb.parser.VQLLexer;
import org.svgroz.vacationdb.parser.VQLParser;

import java.io.File;

public class Application {
    public static void main(String[] args) throws Exception {
//        Schema parse = new Schema.Parser().parse(new File("C:\\Users\\SVGroz\\IdeaProjects\\vacationdb\\src\\main\\avro\\foo.avsc"));
//
//        GenericRecord record = new GenericData.Record(parse);
//
//        record.put("foo", "X");
//        record.put("bar", "Y");
//
//        File file = new File("C:\\Users\\SVGroz\\IdeaProjects\\vacationdb\\mydata.txt");
//
//        GenericDatumWriter<GenericRecord> datumWriter = new GenericDatumWriter<>(parse);
//        DataFileWriter<GenericRecord> dataFileWriter = new DataFileWriter<>(datumWriter);
//        dataFileWriter.create(parse, file);
//
//        dataFileWriter.append(record);
//        dataFileWriter.append(record);
//        dataFileWriter.append(record);
//        dataFileWriter.append(record);
//        dataFileWriter.close();
//
//        GenericDatumReader<GenericData> x = new GenericDatumReader<>(parse);
//        DataFileReader<GenericData> z = new DataFileReader<GenericData>(file, x);

        CharStream charStream = CharStreams.fromString("CREATE TABLE X (INT Z);()))00090099009090900000 CREATE ASDFA");
        VQLLexer vqlLexer = new VQLLexer(charStream);
        CommonTokenStream tokens = new CommonTokenStream(vqlLexer);
        VQLParser parser = new VQLParser(tokens);

        MyListener myListener = new MyListener();
        parser.addParseListener(myListener);
        parser.create_table().enterRule(myListener);

    }

    static class MyListener extends VQLBaseListener {
        @Override
        public void enterEveryRule(ParserRuleContext ctx) {
            super.enterEveryRule(ctx);
        }

        @Override
        public void enterColumn(VQLParser.ColumnContext ctx) {

        }

        @Override
        public void exitColumn(VQLParser.ColumnContext ctx) {
            System.out.println("COLUMN TYPE: " + ctx.COLUMN_TYPE());
            System.out.println("COLUMN ID: " + ctx.ID());
        }
    }
}
