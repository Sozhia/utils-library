package ull.tfg.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class LinearFunctionTest {

    LinearFunction constantFunction = new LinearFunction();
    AbstractTimeFunction scale = new ConstantFunction(0.23);
    AbstractTimeFunction shift = new ConstantFunction(0.11);

    TimeFunctionParams params = new TimeFunctionParams() {
        @Override
        public double getTime() {
            return 2.0;
        }
    };


    @Test
    void testSetParameters() {

        constantFunction.setParameters(scale,shift);
        Assertions.assertEquals(scale, constantFunction.getScale());
        Assertions.assertEquals(shift, constantFunction.getShift());
    }

    @Test
    void getScale() {
        constantFunction.setParameters(scale,shift);
        Assertions.assertEquals(scale, constantFunction.getScale());
    }

    @Test
    void getShift() {
        constantFunction.setParameters(scale,shift);
        Assertions.assertEquals(shift, constantFunction.getShift());
    }

    @Test
    void setScale() {
        AbstractTimeFunction newScale = new ConstantFunction(0.15);
        constantFunction.setScale(newScale);
        Assertions.assertEquals(newScale, constantFunction.getScale());
        constantFunction.setScale(scale);
    }

    @Test
    void setShift() {
        AbstractTimeFunction newShift = new ConstantFunction(0.18);
        constantFunction.setShift(newShift);
        Assertions.assertEquals(newShift, constantFunction.getShift());
        constantFunction.setScale(shift);
    }

    @Test
    void getValue() {
        Assertions.assertEquals(0.23,constantFunction.getValue(params));

    }

    @Test
    void setParameters() {
    }
}