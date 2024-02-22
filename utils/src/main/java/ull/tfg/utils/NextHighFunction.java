package ull.tfg.utils;

public class NextHighFunction extends AbstractTimeFunction {
    private AbstractTimeFunction func;
    private double scale = 1.0;
    private double shift = 0.0;

    /**
     * Constructs a new NextHighFunction with default values.
     */
    public NextHighFunction() {
    }

    /**
     * Constructs a new NextHighFunction with specified function, scale, and shift.
     *
     * @param func The base function.
     * @param scale The scale factor.
     * @param shift The shift factor.
     */
    public NextHighFunction(AbstractTimeFunction func, double scale, double shift) {
        this.func = func;
        this.scale = scale;
        this.shift = shift;
    }

    /**
     * Calculates and returns the next highest value of the function for the given time parameters.
     *
     * @param params The time parameters.
     * @return The next highest value of the function.
     */
    @Override
    public double getValue(TimeParams params) {
        final double ts = params.getTime();
        final double auxVal = Math.ceil((ts + func.getValue(params) - shift) / scale) * scale + shift;
        return auxVal - ts;
    }

    /**
     * Sets the parameters of the NextHighFunction.
     *
     * @param params The parameters. Expected format: (Function, Scale, Shift).
     * @throws IllegalArgumentException If the number of parameters is not 3 or if parameters are of incorrect types.
     */
    @Override
    public void setParameters(Object... params) {
        if (params.length != 3) {
            throw new IllegalArgumentException("Should be three parameters for NextHighFunction: " +
                    params.length + " passed.");
        }
        if (!(params[0] instanceof AbstractTimeFunction)) {
            throw new IllegalArgumentException("First parameter must be a TimeFunction.");
        }
        if (!(params[1] instanceof Number)) {
            throw new IllegalArgumentException("Second parameter must be a Number.");
        }
        if (!(params[2] instanceof Number)) {
            throw new IllegalArgumentException("Third parameter must be a Number.");
        }

        setFunc((AbstractTimeFunction) params[0]);
        setScale(((Number) params[1]).doubleValue());
        setShift(((Number) params[2]).doubleValue());
    }

    /**
     * Gets the base function.
     *
     * @return The base function.
     */
    public AbstractTimeFunction getFunc() {
        return func;
    }

    /**
     * Sets the base function.
     *
     * @param func The base function to set.
     */
    public void setFunc(AbstractTimeFunction func) {
        this.func = func;
    }

    /**
     * Gets the scale factor.
     *
     * @return The scale factor.
     */
    public double getScale() {
        return scale;
    }

    /**
     * Sets the scale factor.
     *
     * @param scale The scale factor to set.
     */
    public void setScale(double scale) {
        this.scale = scale;
    }

    /**
     * Gets the shift factor.
     *
     * @return The shift factor.
     */
    public double getShift() {
        return shift;
    }

    /**
     * Sets the shift factor.
     *
     * @param shift The shift factor to set.
     */
    public void setShift(double shift) {
        this.shift = shift;
    }
}
