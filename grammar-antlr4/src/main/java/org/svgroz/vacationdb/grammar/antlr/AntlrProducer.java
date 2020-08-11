package org.svgroz.vacationdb.grammar.antlr;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.svgroz.vacationdb.grammar.Producer;
import org.svgroz.vacationdb.grammar.exception.IllegalStringInputException;
import org.svgroz.vacationdb.grammar.expression.GeneralExpression;
import org.svgroz.vacationdb.parser.VQLLexer;
import org.svgroz.vacationdb.parser.VQLParser;

import java.util.Objects;

public class AntlrProducer implements Producer {
    @Override
    public GeneralExpression produce(String rawExpression) throws IllegalStringInputException {
        CharStream charStream = CharStreams.fromString(
                Objects.requireNonNull(rawExpression, "rawExpression is null")
        );

        VQLLexer vqlLexer = new VQLLexer(charStream);
        CommonTokenStream tokens = new CommonTokenStream(vqlLexer);
        VQLParser parser = new VQLParser(tokens);

        CreateTableListener createTableListener = new CreateTableListener();
        parser.addParseListener(createTableListener);
        parser.create_table().enterRule(createTableListener);



        return createTableListener.getCreateTableExpression();
    }
}
