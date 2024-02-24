package ull.tfg.utils;

import java.util.ArrayList;

/**
 * A time function that can be restarted several times and replicates the previous generation of values in the same order.
 *
 * <p>Author: Iván Castilla</p>
 */
public class ReplicableTimeFunction extends AbstractTimeFunction {
    private AbstractTimeFunction innerTimeFunction;
    final private ArrayList<Double> genValues;
    private int counter;

    /**
     * Constructs a ReplicableTimeFunction with the given innerTimeFunction.
     *
     * @param innerTimeFunction The inner TimeFunction.
     */
    public ReplicableTimeFunction(AbstractTimeFunction innerTimeFunction) {
        this();
        this.innerTimeFunction = innerTimeFunction;
    }

    /**
     * Constructs a ReplicableTimeFunction with an empty list of generated values and counter initialized to 0.
     */
    public ReplicableTimeFunction() {
        genValues = new ArrayList<>();
        counter = 0;
    }

    /**
     * Resets the counter to 0, allowing the ReplicableTimeFunction to be restarted.
     */
    public void reStart() {
        counter = 0;
    }

    @Override
    public double getValue(TimeFunctionParams params) {
        if (counter == genValues.size()) {
            genValues.add(innerTimeFunction.getValue(params));
        }
        return genValues.get(counter++);
    }

    @Override
    public void setParameters(Object... params) {
        if (params.length != 1) {
            throw new IllegalArgumentException("Should be one parameter for ReplicableTimeFunction: " +
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

