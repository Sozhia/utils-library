package ull.tfg.utils;

/**
 * Defines a time function which consists of an array of other time functions. The time unit
 * is used to choose the function corresponding to the current timestamp. Therefore,
 * getValue will return a value of part[i], where i = (ts / timeUnit) % functionArray.length.
 *
 * <p>Author: Iván Castilla Rodríguez</p>
 */
public class TimeFunctionSplitter extends AbstractTimeFunction {
    private AbstractTimeFunction[] part;
    private double timeUnit;

    public TimeFunctionSplitter() {

    }

    /**
     * Constructs a UniformlyDistributedSplitFunction.
     *
     * @param part     The array of time functions.
     * @param timeUnit The time unit.
     */
    public TimeFunctionSplitter(AbstractTimeFunction[] part, double timeUnit) {
        super();
        this.part = part;
        this.timeUnit = timeUnit;
    }

    @Override
    public double getValue(TimeFunctionParams params) {
        int unit = (int) (params.getTime() / timeUnit);
        int index = unit % part.length;
        return part[index].getValue(params);
    }

    @Override
    public void setParameters(Object... params) {
        if (params.length != 2) {
            throw new IllegalArgumentException("Need 2 parameters, received " +
                    params.length);
        }
        if (!(params[0] instanceof AbstractTimeFunction[]) || !(params[1] instanceof Number)) {
            throw new IllegalArgumentException("Parameters must be TimeFunction[] and double");
        }
        setPart((AbstractTimeFunction[]) params[0]);
        setTimeUnit(((Number) params[1]).doubleValue());
    }

    /**
     * Returns the array of time functions.
     *
     * @return The array of time functions.
     */
    public AbstractTimeFunction[] getPart() {
        return part;
    }

    /**
     * Sets the array of time functions.
     *
     * @param part The array of time functions to set.
     */
    public void setPart(AbstractTimeFunction[] part) {
        this.part = part;
    }

    /**
     * Returns the time unit.
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

