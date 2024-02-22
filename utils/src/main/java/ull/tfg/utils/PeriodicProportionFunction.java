package ull.tfg.utils;

import java.util.Collection;

/**
 * A function used to calculate the percentage of a value depending on the time unit.
 *
 * <p>Author: Iván Castilla Rodríguez</p>
 */
public class PeriodicProportionFunction extends AbstractTimeFunction {
    private int[] nElem;
    private double[] prop;
    private double timeUnit;

    /**
     * Constructs a new PeriodicProportionFunction with default values.
     */
    public PeriodicProportionFunction() {
    }

    /**
     * Constructs a new PeriodicProportionFunction with specified elements, proportions, and time unit.
     *
     * @param elem The elements.
     * @param prop The proportions.
     * @param timeUnit The time unit.
     */
    public PeriodicProportionFunction(int[] elem, double[] prop, double timeUnit) {
        this.nElem = elem;
        this.prop = prop;
        this.timeUnit = timeUnit;
    }

    /**
     * Constructs a new PeriodicProportionFunction with specified elements, proportions, and time unit.
     *
     * @param elem The elements.
     * @param prop The proportions.
     * @param timeUnit The time unit.
     */
    public PeriodicProportionFunction(Collection<Integer> elem, Collection<Double> prop, double timeUnit) {
        this.nElem = new int[elem.size()];
        int i = 0;
        for (int e : elem)
            this.nElem[i++] = e;
        this.prop = new double[prop.size()];
        i = 0;
        for (double p : prop)
            this.prop[i++] = p;
        this.timeUnit = timeUnit;
    }

    /**
     * Calculates and returns the value of the PeriodicProportionFunction for the given time parameters.
     *
     * @param params The time parameters.
     * @return The calculated value.
     */
    @Override
    public double getValue(TimeParams params) {
        int unit = (int) (params.getTime() / timeUnit);
        int indexp = unit % prop.length;
        int indexv = (unit / prop.length) % nElem.length;
        return nElem[indexv] * prop[indexp];
    }

    /**
     * Sets the parameters of the PeriodicProportionFunction.
     *
     * @param params The parameters. Expected format: (int[], double[], double).
     * @throws IllegalArgumentException If the number of parameters is not 3 or if parameters are of incorrect types.
     */
    @Override
    public void setParameters(Object... params) {
        if (params.length != 3)
            throw new IllegalArgumentException("Need 3 parameters, received " +
                    params.length);
        if (!(params[0] instanceof int[]) || !(params[1] instanceof double[]) || !(params[2] instanceof Number))
            throw new IllegalArgumentException("Parameters must be int[], double[], and double.");
        setNElem((int[]) params[0]);
        setProp((double[]) params[1]);
        setTimeUnit(((Number) params[2]).doubleValue());
    }

    /**
     * Gets the elements.
     *
     * @return The elements.
     */
    public int[] getNElem() {
        return nElem;
    }

    /**
     * Sets the elements.
     *
     * @param elem The elements to set.
     */
    public void setNElem(int[] elem) {
        nElem = elem;
    }

    /**
     * Gets the proportions.
     *
     * @return The proportions.
     */
    public double[] getProp() {
        return prop;
    }

    /**
     * Sets the proportions.
     *
     * @param prop The proportions to set.
     */
    public void setProp(double[] prop) {
        this.prop = prop;
    }

    /**
     * Gets the time unit.
     *
     * @return The time unit.
     */
    public double getTimeUnit() {
        return timeUnit;
    }

    /**
     * Sets the time unit.
     *
     * @param timeUnit The time unit to set.
     */
    public void setTimeUnit(double timeUnit) {
        this.timeUnit = timeUnit;
    }
}

