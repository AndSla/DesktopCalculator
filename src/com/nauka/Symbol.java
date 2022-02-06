package com.nauka;

public enum Symbol {

    ADD('\u002B', 1),
    SUBTRACT('\u002D', 1),
    MULTIPLY('\u00D7', 2),
    DIVIDE('\u00F7', 2),
    DOT('.', 0),
    POWER_TWO('\u00B2', -1),
    POWER_Y('\u02B8', -1),
    SQUARE_ROOT('\u221A', -1),
    PLUS_MINUS('\u00B1', -1),
    LEFT_PARENTHESIS('(', -1),
    RIGHT_PARENTHESIS(')', -1);

    private final char symbol;
    private final int rank;

    Symbol(char symbol, int rank) {
        this.symbol = symbol;
        this.rank = rank;
    }

    public char getSymbol() {
        return symbol;
    }

    public int getRank() {
        return rank;
    }

}
