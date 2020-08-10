package org.svgroz.vacationdb.grammar.exception;

public class UnsupportedColumnType extends GrammarException {
    private final String unsupportedType;

    public UnsupportedColumnType(String unsupportedType) {
        this.unsupportedType = unsupportedType;
    }

    public String getUnsupportedType() {
        return unsupportedType;
    }

    @Override
    public String toString() {
        return "UnsupportedColumnType{" +
                "unsupportedType='" + unsupportedType + '\'' +
                "} " + super.toString();
    }
}
