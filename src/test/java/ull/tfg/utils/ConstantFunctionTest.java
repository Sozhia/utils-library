package ull.tfg.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ConstantFunctionTest {
    ConstantFunction constantFunction = new ConstantFunction(0d);
    double expectedValue = 10.0;


    @Test
    void testGetValue() {
        double result = constantFunction.getValue(null);
        Assertions.assertEquals(0d, result);
    }

    @Test
    void testSetParameters() {
        constantFunction.setParameters(expectedValue);
        Assertions.assertEquals(expectedValue,
                                constantFunction.getConstantValue(),
                         0.0001);
    }
}
