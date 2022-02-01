package com.nauka;

public enum Symbol {

    ADD('\u002B', 1),
    SUBTRACT('\u2212', 1),
    MULTIPLY('\u00D7', 2),
    DIVIDE('\u00F7', 2),
    DOT('.', 0);

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
