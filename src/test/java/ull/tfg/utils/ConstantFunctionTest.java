package ull.tfg.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ConstantFunctionTest {
    ConstantFunction constantFunction = new ConstantFunction(0d);

    TimeFunctionParams params = () -> 1.0;

    double expectedValue = 10.0;

    @Test
    void testConstructor(){
        ConstantFunction constantFunction = new ConstantFunction(0d);
        Assertions.assertEquals(ConstantFunction.class, constantFunction.getClass());
    }


    @Test
    void setConstantValue() {
        constantFunction.setConstantValueValue(0.23);
        Assertions.assertEquals(0.23,constantFunction.getValue(params));
    }

    @Test
    void getConstantValue() {
        constantFunction.setConstantValueValue(0.23);
        Assertions.assertEquals(0.23,constantFunction.getValue(params));
    }

    @Test
    void getValue() {
        double result = constantFunction.getValue(params);
        Assertions.assertEquals(0d, result);
    }

    @Test
    void setParameters() {
        constantFunction.setParameters(expectedValue);
        Assertions.assertEquals(expectedValue,
                constantFunction.getValue(params),
                0.0001);
    }

}
