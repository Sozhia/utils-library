package ull.tfg.utils;

/**
 * Represents a time function that rounds, floors, or ceilings the value obtained from another time function.
 *
 * <p>Author: Iván</p>
 */
public class RoundFunction extends AbstractTimeFunction {
    public enum Type {
        ROUND,
        CEIL,
        FLOOR
    }

    private Type type = Type.ROUND;
    private AbstractTimeFunction func;
    private double scale = 1.0;
    private double shift = 0.0;

    /**
     * Constructs a RoundFunction.
     */
    public RoundFunction() {
    }

    /**
     * Constructs a RoundFunction with the specified type, function, and scale.
     *
     * @param type  The rounding type.
     * @param func  The inner TimeFunction.
     * @param scale The scale.
     */
    public RoundFunction(Type type, AbstractTimeFunction func, double scale) {
        this.type = type;
        this.func = func;
        this.scale = scale;
    }

    /**
     * Constructs a RoundFunction with the specified type, function, scale, and shift.
     *
     * @param type  The rounding type.
     * @param func  The inner TimeFunction.
     * @param scale The scale.
     * @param shift The shift.
     */
    public RoundFunction(Type type, AbstractTimeFunction func, double scale, double shift) {
        this.type = type;
        this.func = func;
        this.scale = scale;
        this.shift = shift;
    }

    @Override
    public double getValue(TimeParams params) {
        double val = func.getValue(params);
        return val;
    }
    /*
    @Override
    public double getValue(TimeParams params) {
        double val = func.getValue(params);
        if (scale != 0.0) {
            switch (type) {
                case CEIL:
                    val = ExtendedMath.ceil(val, scale) + shift;
                    break;
                case FLOOR:
                    val = ExtendedMath.floor(val, scale) + shift;
                    break;
                case ROUND:
                default:
                    val = ExtendedMath.round(val, scale) + shift;
                    break;
            }
        }
        return val;
    }*/

    @Override
    public void setParameters(Object... params) {
        if (params.length != 4) {
            throw new IllegalArgumentException("Should be four parameters for RoundFunction: " +
                    params.length + " passed.");
        }
        if (!(params[0] instanceof Type)) {
            throw new IllegalArgumentException("First parameter must be a RoundFunction.Type.");
        } else if (!(params[1] instanceof AbstractTimeFunction)) {
            throw new IllegalArgumentException("Second parameter must be a TimeFunction.");
        } else if (!(params[2] instanceof Number)) {
            throw new IllegalArgumentException("Third parameter must be a Number.");
        } else if (!(params[3] instanceof Number)) {
            throw new IllegalArgumentException("Fourth parameter must be a Number.");
        } else {
            setType((Type) params[0]);
            setFunc((AbstractTimeFunction) params[1]);
            setScale(((Number) params[2]).doubleValue());
            setShift(((Number) params[3]).doubleValue());
        }
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public AbstractTimeFunction getFunc() {
        return func;
    }

    public void setFunc(AbstractTimeFunction func) {
        this.func = func;
    }

    public double getScale() {
        return scale;
    }

    public void setScale(double scale) {
        this.scale = scale;
    }

    public double getShift() {
        return shift;
    }

    public void setShift(double shift) {
        this.shift = shift;
    }
}
