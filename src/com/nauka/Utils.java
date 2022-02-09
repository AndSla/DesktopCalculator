package com.nauka;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Objects;

public class Utils {

    static boolean isLastCharDigit(String equation) {
        char lastChar = equation.charAt(equation.length() - 1);
        return Character.isDigit(lastChar);
    }

    static int getRank(Character symbol) {
        for (Symbol value : Symbol.values()) {
            if (value.getSymbol() == symbol) {
                return value.getRank();
            }
        }
        return -1;
    }

    static boolean isOperator(String symbol) {
        if (symbol.length() == 1) {
            for (Symbol value : Symbol.values()) {
                if (value.getSymbol() == symbol.charAt(0)) {
                    return value.isOperator();
                }
            }
        }
        return false;
    }

    static Deque<String> convertToPostFixNotation(String equation) {

        Deque<String> postFixNotationStack = new ArrayDeque<>();
        Deque<Character> operatorsStack = new ArrayDeque<>();

        StringBuilder number = new StringBuilder();

        for (int i = 0; i < equation.length(); i++) {
            char scan = equation.charAt(i);

            if (i == 0 && equation.charAt(i) == Symbol.SUBTRACT.getSymbol()) {
                number.append("-");
                continue;
            }

            if (Character.isDigit(scan) || scan == Symbol.DOT.getSymbol()) {
                number.append(scan);
            } else {
                postFixNotationStack.add(number.toString());
                number.setLength(0);
                if (!operatorsStack.isEmpty()) {
                    int scannedOperatorRank = getRank(scan);
                    int stackedOperatorRank = getRank(operatorsStack.peekLast());
                    if (stackedOperatorRank >= scannedOperatorRank) {
                        postFixNotationStack.add(String.valueOf(operatorsStack.pollLast()));
                    }
                }
                operatorsStack.add(scan);
            }

            if (i == equation.length() - 1) {
                postFixNotationStack.add(number.toString());
            }

        }

        while (!operatorsStack.isEmpty()) {
            postFixNotationStack.add(String.valueOf(operatorsStack.pollLast()));
        }

        return postFixNotationStack;

    }

    static boolean isDotAllowed(String equation) {
        char[] equationChars = equation.toCharArray();
        Deque<Character> equationQueue = new ArrayDeque<>();
        for (char c : equationChars) {
            equationQueue.offerLast(c);
        }

        if (equation.length() == 0 || isOperator(String.valueOf(equationQueue.peekLast()))) {
            return false;
        }

        while (!equationQueue.isEmpty()) {
            char scan = equationQueue.pollLast();

            if (isOperator(String.valueOf(scan))) {
                break;
            }

            if (scan == Symbol.DOT.getSymbol()) {
                return false;
            }

        }

        return true;
    }

    static String calculate(Deque<String> postFixNotationStack) {

        String result = "0";
        Deque<String> subEquation = new ArrayDeque<>();
        MathContext mathContext = new MathContext(12, RoundingMode.HALF_EVEN);

        while (postFixNotationStack.size() > 0) {

            String scan = postFixNotationStack.pollFirst();
            subEquation.offerLast(scan);

            if (isOperator(scan)) {
                String operator = subEquation.pollLast();
                BigDecimal b = BigDecimal.valueOf(Double.parseDouble(Objects.requireNonNull(subEquation.pollLast())));
                BigDecimal a = BigDecimal.valueOf(Double.parseDouble(Objects.requireNonNull(subEquation.pollLast())));

                if (String.valueOf(Symbol.ADD.getSymbol()).equals(operator)) {
                    result = String.valueOf(a.add(b, mathContext));
                    subEquation.offerLast(result);
                }

                if (String.valueOf(Symbol.SUBTRACT.getSymbol()).equals(operator)) {
                    result = String.valueOf(a.subtract(b, mathContext));
                    subEquation.offerLast(result);
                }

                if (String.valueOf(Symbol.MULTIPLY.getSymbol()).equals(operator)) {
                    result = String.valueOf(a.multiply(b, mathContext));
                    subEquation.offerLast(result);
                }

                if (String.valueOf(Symbol.DIVIDE.getSymbol()).equals(operator)) {
                    if (b.doubleValue() == 0) {
                        result = "DIVIDE_BY_0";
                        break;
                    } else {
                        result = String.valueOf(a.divide(b, mathContext));
                        subEquation.offerLast(result);
                    }
                }

                while (!subEquation.isEmpty()) {
                    postFixNotationStack.offerFirst(subEquation.pollLast());
                }

            }

        }

        return cutTrailingZeros(result);

    }

    static String cutTrailingZeros(String result) {
        StringBuilder sb = new StringBuilder(result);
        String prettyResult = result;

        if (result.matches("-*\\d+" + Symbol.DOT.getSymbol() + "\\d+")) {

            while (true) {
                int i = sb.length() - 1;

                if (sb.charAt(i) == '0') {
                    sb.deleteCharAt(i);
                } else if (sb.charAt(i) == Symbol.DOT.getSymbol()) {
                    sb.deleteCharAt(i);
                    prettyResult = sb.toString();
                    break;
                } else {
                    prettyResult = sb.toString();
                    break;
                }

            }

        }

        return prettyResult;

    }

}
