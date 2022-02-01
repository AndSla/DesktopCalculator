package com.nauka;

import java.util.ArrayDeque;
import java.util.Deque;

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
        if (symbol.charAt(0) != Symbol.DOT.getSymbol()) {
            for (Symbol value : Symbol.values()) {
                if (value.getSymbol() == symbol.charAt(0)) {
                    return true;
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

    static String calculate(Deque<String> postFixNotationStack) {

        String result = "";
        Deque<String> subEquation = new ArrayDeque<>();

        while (postFixNotationStack.size() > 0) {

            String scan = postFixNotationStack.pollFirst();
            subEquation.offerLast(scan);

            if (isOperator(scan)) {
                String operator = subEquation.pollLast();
                double b = Double.parseDouble(subEquation.pollLast());
                double a = Double.parseDouble(subEquation.pollLast());

                if (String.valueOf(Symbol.ADD.getSymbol()).equals(operator)) {
                    result = String.valueOf(a + b);
                    subEquation.offerLast(result);
                }

                if (String.valueOf(Symbol.SUBTRACT.getSymbol()).equals(operator)) {
                    result = String.valueOf(a - b);
                    subEquation.offerLast(result);
                }

                if (String.valueOf(Symbol.MULTIPLY.getSymbol()).equals(operator)) {
                    result = String.valueOf(a * b);
                    subEquation.offerLast(result);
                }

                if (String.valueOf(Symbol.DIVIDE.getSymbol()).equals(operator)) {
                    result = String.valueOf(a / b);
                    subEquation.offerLast(result);
                }

                while (!subEquation.isEmpty()){
                    postFixNotationStack.offerFirst(subEquation.pollLast());
                }

            }

        }

        return result;

    }

}
