package ull.tfg.utils;

/**
 * A constant value wrapped by a time function.
 * @author Ivan Castilla Rodriguez
 *
 */
public class ConstantFunction extends AbstractTimeFunction {

    private double val;

    public ConstantFunction(double val) {
        super();
        this.val = val;
    }

    public void setValue(double val) {
        this.val = val;
    }

    public double getValue(TimeParams params) {
        return val;
    }

    @Override
    public void setParameters(Object... params) {
        if (params.length < 1)
            throw new IllegalArgumentException("Need (value), received " +
                    params.length + " parameters");
        else {
            setValue(((Number)params[0]).doubleValue());
        }

    }

}
