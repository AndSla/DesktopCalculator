package com.nauka.calcmethods;

import com.nauka.CalculateMethod;

import java.math.BigDecimal;
import java.math.MathContext;

public class Power implements CalculateMethod {

    @Override
    public String calculate(BigDecimal a, BigDecimal b, MathContext mathContext) {
        return String.valueOf(a.pow(b.intValue(), mathContext));
    }

}
