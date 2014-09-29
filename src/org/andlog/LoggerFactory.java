
package org.andlog;

/**
 * Interface of factories used to create {@link Logger} instances.
 * 
 * @author samael_wang
 */
public interface LoggerFactory {

    /**
     * Create a {@link Logger} for the given {@code object}.
     * 
     * @param obj {@link Object} of which the {@link Logger} works on. Depending
     *            on the implementation, the object could be used to generate
     *            the log tag, prefix or other values in the log.
     * @return {@link Logger}
     */
    public abstract Logger create(Object obj);

    /**
     * Create a {@link Logger} for the given {@code clazz}.
     * 
     * @param cls {@link Class} of which the {@link Logger} works on. Depending
     *            on the implementation, the class could be used to generate the
     *            log tag, prefix or other values in the log.
     * @return {@link Logger}
     */
    public abstract Logger create(Class<?> cls);

}
