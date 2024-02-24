package ull.tfg.utils;

import simkit.random.RandomVariate;

/**
 * Represents a function that generates random values over time.
 *
 * <p>Author: Iván Castilla Rodríguez</p>
 */
public class RandomFunction extends AbstractTimeFunction {
    private RandomVariate rnd;

    /**
     * Constructs a RandomFunction with the given RandomVariate.
     *
     * @param rnd The RandomVariate used by this function.
     */
    public RandomFunction(RandomVariate rnd) {
        super();
        this.rnd = rnd;
    }

    /**
     * Constructs a RandomFunction without setting the RandomVariate.
     */
    public RandomFunction() {
        super();
    }

    /**
     * Returns the RandomVariate used by this function.
     *
     * @return The RandomVariate.
     */
    public RandomVariate getRandom() {
        return rnd;
    }

    /**
     * Sets the RandomVariate used by this function.
     *
     * @param rnd The RandomVariate to set.
     */
    public void setRandom(RandomVariate rnd) {
        this.rnd = rnd;
    }

    @Override
    public double getValue(TimeFunctionParams params) {
        return rnd.generate();
    }

    @Override
    public void setParameters(Object... params) {
        if (params.length < 1) {
            throw new IllegalArgumentException("Need (RandomVariate), received " +
                    params.length + " parameters");
        } else {
            setRandom((RandomVariate) params[0]);
        }
    }
}

