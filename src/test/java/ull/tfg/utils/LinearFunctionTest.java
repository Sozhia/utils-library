package ull.tfg.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class LinearFunctionTest {


    AbstractTimeFunction scale = new ConstantFunction(0.23);
    AbstractTimeFunction shift = new ConstantFunction(0.11);

    LinearFunction linearFunction = new LinearFunction(scale,shift);

    TimeFunctionParams params = () -> 2.0;


    @Test
    void SetParameters() {

        linearFunction.setParameters(scale,shift);
        Assertions.assertEquals(scale, linearFunction.getScale());
        Assertions.assertEquals(shift, linearFunction.getShift());
    }

    @Test
    void setScale() {
        AbstractTimeFunction newScale = new ConstantFunction(0.15);
        linearFunction.setScale(newScale);
        Assertions.assertEquals(newScale, linearFunction.getScale());
        linearFunction.setScale(scale);
    }

    @Test
    void setShift() {
        AbstractTimeFunction newShift = new ConstantFunction(0.18);
        linearFunction.setShift(newShift);
        Assertions.assertEquals(newShift, linearFunction.getShift());
        linearFunction.setScale(shift);
    }

    // Buscar alternativas al churro ese
    @Test
    void getValue() {
        Assertions.assertEquals(0.5700000000000001, linearFunction.getValue(params));

    }

    @Test
    void getScale() {
        linearFunction.setParameters(scale,shift);
        Assertions.assertEquals(scale, linearFunction.getScale());
    }

    @Test
    void getShift() {
        linearFunction.setParameters(scale,shift);
        Assertions.assertEquals(shift, linearFunction.getShift());
    }

}