package com.nauka.calcmethods;

import com.nauka.CalculateMethod;

import java.math.BigDecimal;
import java.math.MathContext;

public class SquareRoot implements CalculateMethod {

    @Override
    public String calculate(BigDecimal a, BigDecimal b, MathContext mathContext) {
        return String.valueOf(Math.sqrt(b.doubleValue()));
    }

}
