package ull.tfg.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class LinearFunctionTest {

    LinearFunction instance = new LinearFunction();
    AbstractTimeFunction scale = new ConstantFunction(0.23);
    AbstractTimeFunction shift = new ConstantFunction(0.11);


    @Test
    void testSetParameters() {

        instance.setParameters(scale,shift);
        Assertions.assertEquals(scale, instance.getScale());
        Assertions.assertEquals(shift, instance.getShift());
    }

    @Test
    void getScale() {
        instance.setParameters(scale,shift);
        Assertions.assertEquals(scale, instance.getScale());
    }

    @Test
    void getShift() {
        instance.setParameters(scale,shift);
        Assertions.assertEquals(shift, instance.getShift());
    }

    @Test
    void setScale() {
        AbstractTimeFunction newScale = new ConstantFunction(0.15);
        instance.setScale(newScale);
        Assertions.assertEquals(newScale, instance.getScale());
        instance.setScale(scale);
    }

    @Test
    void setShift() {
        AbstractTimeFunction newShift = new ConstantFunction(0.18);
        instance.setShift(newShift);
        Assertions.assertEquals(newShift, instance.getShift());
        instance.setScale(shift);
    }

    @Test
    void getValue() {

    }

    @Test
    void setParameters() {
    }
}