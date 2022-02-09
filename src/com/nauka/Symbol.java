package com.nauka;

public enum Symbol {

    ADD('\u002B', 1,true),
    SUBTRACT('\u002D', 1, true),
    MULTIPLY('\u00D7', 2, true),
    DIVIDE('\u00F7', 2, true),
    POWER_TWO('\u00B2', -1, true),
    POWER_Y('\u02B8', -1, true),
    SQUARE_ROOT('\u221A', -1, true),
    DOT('.', 0, false),
    PLUS_MINUS('\u00B1', -1, false),
    LEFT_PARENTHESIS('(', -1, false),
    RIGHT_PARENTHESIS(')', -1, false);

    private final char symbol;
    private final int rank;
    private final boolean isOperator;

    Symbol(char symbol, int rank, boolean isOperator) {
        this.symbol = symbol;
        this.rank = rank;
        this.isOperator = isOperator;
    }

    public char getSymbol() {
        return symbol;
    }

    public int getRank() {
        return rank;
    }

    public boolean isOperator() {
        return isOperator;
    }

}
