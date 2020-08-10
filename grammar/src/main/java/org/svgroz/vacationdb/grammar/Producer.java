package org.svgroz.vacationdb.grammar;

import org.svgroz.vacationdb.grammar.exception.GrammarException;
import org.svgroz.vacationdb.grammar.expression.GeneralExpression;

public interface Producer {
    GeneralExpression produce(String rawExpression) throws GrammarException;
}
