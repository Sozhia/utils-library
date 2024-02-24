package ull.tfg.utils;

import simkit.random.RandomVariateFactory;

/**
 * Represents a polynomial function: a1*x^n-1 + a2*x^n-2 + ... + an
 *
 * <p>Author: Roberto Muñoz</p>
 */
public class PolynomialFunction extends AbstractTimeFunction {
    /** Function coefficients */
    private AbstractTimeFunction coefficients[];
    /** Highest coefficient index */
    private int length;

    /**
     * Creates a polynomial function with coefficients expressed as other time functions.
     *
     * @param coefficients Coefficients of this function, expressed as other time functions.
     */
    public PolynomialFunction(AbstractTimeFunction coefficients[]) {
        this.length = coefficients.length;
        this.coefficients = coefficients;
    }

    /**
     * Creates a polynomial function with coefficients expressed as constants.
     *
     * @param coefficients Coefficients of this function, expressed as constants.
     */
    public PolynomialFunction(double coefficients[]) {
        this.length = coefficients.length;
        this.coefficients = new AbstractTimeFunction[length];
        int i = 0;
        for (double j : coefficients) {
            this.coefficients[i++] = new RandomFunction(RandomVariateFactory.getInstance("ConstantVariate", j));
            // this.coefficients[i++] = (AbstractTimeFunction) RandomVariateFactory.getInstance("ConstantVariate", j);;
        }
    }

    /**
     * Creates a polynomial function whose parameters must be set using {@link #setParameters(Object...)}.
     */
    public PolynomialFunction() {}

    @Override
    public double getValue(TimeFunctionParams params) {
        double value = 0;
        for (int i = 0; i < length; i++) {
            value += Math.pow(params.getTime(), length - i - 1) * coefficients[i].getValue(params);
        }
        return value;
    }

    /**
     * Returns the coefficients of this function.
     *
     * @return The coefficients.
     */
    public AbstractTimeFunction[] getCoefficients() {
        return coefficients;
    }

    /**
     * Sets the coefficients of this function.
     *
     * @param coefficients The coefficients to set.
     */
    public void setCoefficients(AbstractTimeFunction[] coefficients) {
        this.coefficients = coefficients;
    }

    @Override
    public void setParameters(Object... params) {
        this.coefficients = (AbstractTimeFunction[]) params;
    }
}
