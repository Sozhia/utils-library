package ull.tfg.utils;

/**
 * Represents a time function that ensures the returned value is always non-negative.
 *
 * <p>Author: Iván Castilla</p>
 */
public class PositiveTimeFunction extends AbstractTimeFunction {
    private AbstractTimeFunction innerTimeFunction;

    /**
     * Constructs a PositiveTimeFunction with the given innerTimeFunction.
     *
     * @param innerTimeFunction The inner TimeFunction.
     */
    public PositiveTimeFunction(AbstractTimeFunction innerTimeFunction) {
        this.innerTimeFunction = innerTimeFunction;
    }

    /**
     * Constructs a PositiveTimeFunction without setting the innerTimeFunction.
     */
    public PositiveTimeFunction() {
    }

    /**
     * Returns a positive value by calling getValue on the innerTimeFunction. If the value is negative, returns 0.0.
     *
     * @param params The parameters used to determine the value.
     * @return A positive value corresponding to the definition of this function.
     */
    @Override
    public double getValue(TimeFunctionParams params) {
        double res = innerTimeFunction.getValue(params);
        if (res < 0.0) {
            res = 0.0;
        }
        return res;
    }

    @Override
    public void setParameters(Object... params) {
        if (params.length != 1) {
            throw new IllegalArgumentException("Should be one parameter for PositiveTimeFunction: " +
                    params.length + " passed.");
        }
        if (!(params[0] instanceof AbstractTimeFunction)) {
            throw new IllegalArgumentException("Parameter must be a TimeFunction.");
        } else {
            setInnerTimeFunction((AbstractTimeFunction) params[0]);
        }
    }

    /**
     * Gets the inner TimeFunction.
     *
     * @return The inner TimeFunction.
     */
    public AbstractTimeFunction getInnerTimeFunction() {
        return innerTimeFunction;
    }

    /**
     * Sets the inner TimeFunction.
     *
     * @param innerTimeFunction The inner TimeFunction to set.
     */
    public void setInnerTimeFunction(AbstractTimeFunction innerTimeFunction) {
        this.innerTimeFunction = innerTimeFunction;
    }
}

