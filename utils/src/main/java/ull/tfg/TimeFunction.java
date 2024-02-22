/**
 * @author Ivan Castilla Rodriguez
 * @version 1.0
 * @since
 */
public abstract class TimeFunction {

    /**
     *
     * @param params Type ITimeFunctionParams
     * @return double
     */
    public abstract double getValue(ITimeFunctionParams params);

    /**
     *
     * @param params
     */
    public abstract void setParameters(Object... params);

}