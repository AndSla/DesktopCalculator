package com.nauka.calcmethods;

import com.nauka.CalculateMethod;

import java.math.BigDecimal;
import java.math.MathContext;

public class Division implements CalculateMethod {

    @Override
    public String calculate(BigDecimal a, BigDecimal b, MathContext mathContext) {
        if (b.doubleValue() == 0) {
            return "DIVIDE_BY_0";
        } else {
            return String.valueOf(a.divide(b, mathContext));
        }
    }

}
