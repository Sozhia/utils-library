package ull.tfg.utils;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

import simkit.random.RandomVariateFactory;

/**
 * A factory for creating instances of TimeFunction.
 * It first searches for TimeFunction classes and then for RandomVariate ones.
 *
 * Based on RandomVariateFactory by Arnold Buss.
 *
 * Author: Iván Castilla Rodríguez
 */
public class TimeFunctionFactory {

    // Cache of RandomVariate Classes already found, indexed by their name
    protected static Map<String, Class<?>> cache;

    // Set of packages to search for RandomVariates if the class name given is not fully qualified
    protected static Set<String> searchPackages;

    // Indicates whether to print out information while searching for RandomVariate Classes
    protected static boolean verbose;

    public static void setVerbose(boolean verbose) {
        TimeFunctionFactory.verbose = verbose;
    }

    public static boolean isVerbose() {
        return verbose;
    }

    public static Map<String, Class<?>> getCache() {
        return new WeakHashMap<>(cache);
    }

    static {
        searchPackages = new LinkedHashSet<>();
        searchPackages.add("es.ull.iis.function");
        cache = new WeakHashMap<>();
    }

    // Private constructor to prevent instantiation
    private TimeFunctionFactory() {
    }

    /**
     * Creates an instance of TimeFunction based on the fully-qualified class name and parameters.
     *
     * @param className  The fully-qualified class name of the desired instance
     * @param parameters The desired parameters for the instance
     * @return Instance of TimeFunction
     * @throws IllegalArgumentException If the className is null or a class with that name cannot be found
     */
    public static AbstractTimeFunction getInstance(String className, Object... parameters) {
        if (className == null) {
            throw new IllegalArgumentException("Null class name");
        }

        // Check cache first
        Class<?> timeFunctionClass = cache.get(className);

        if (timeFunctionClass == null) {
            timeFunctionClass = findFullyQualifiedNameFor(className);
            if (timeFunctionClass == null) {
                // Try appending "Function" to the class name
                timeFunctionClass = findFullyQualifiedNameFor(className + "Function");
            }
            // If all attempts fail, use RandomVariate
            if (timeFunctionClass == null) {
                return new RandomFunction(RandomVariateFactory.getInstance(className, parameters));
            } else {
                cache.put(className, timeFunctionClass);
            }
        }

        AbstractTimeFunction instance;
        try {
            instance = (AbstractTimeFunction) timeFunctionClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        instance.setParameters(parameters);
        return instance;
    }

    /**
     * Adds the given fully qualified package name to the list of packages to search when attempting to find RandomVariates by name.
     */
    public static void addSearchPackage(String newPackage) {
        searchPackages.add(newPackage);
    }

    /**
     * Sets the list of packages to search when attempting to find a RandomVariate by name. Replaces existing search packages.
     *
     * @param packages New Set of search packages
     */
    public static void setSearchPackages(Set<String> packages) {
        searchPackages = new LinkedHashSet<>(packages);
    }

    /**
     * Returns a copy of the packages to search when attempting to find a RandomVariate by name.
     *
     * @return Copy of search packages Set
     */
    public static Set<String> getSearchPackages() {
        return new LinkedHashSet<>(searchPackages);
    }

    /**
     * Finds the TimeFunction Class corresponding to the given name.
     * First attempts to find the TimeFunction assuming that the name is fully qualified.
     * Then searches the specified search packages.
     */
    public static Class<?> findFullyQualifiedNameFor(String className) {
        Class<?> theClass = null;
        // First see if the name passed is "fully qualified"
        try {
            theClass = Thread.currentThread().getContextClassLoader().loadClass(className);
            return theClass;
        } catch (ClassNotFoundException e) {
        }
        // If not, then try the search path
        for (String searchPackage : searchPackages) {
            if (verbose) {
                System.out.println("Checking " + searchPackage + "." + className);
            }
            try {
                theClass = Thread.currentThread().getContextClassLoader().loadClass(searchPackage + "." + className);
                if (!ull.tfg.utils.AbstractTimeFunction.class.isAssignableFrom(theClass)) {
                    continue;
                }
            } catch (ClassNotFoundException e) {
                continue;
            }
        }
        if (verbose) {
            System.out.println("Returning " + theClass);
        }
        return theClass;
    }
}

