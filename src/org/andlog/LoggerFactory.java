
package org.andlog;

import android.text.TextUtils;

/**
 * Abstract class of factories used to create {@link Logger} instances.
 * 
 * @author samael_wang
 */
public abstract class LoggerFactory {
    protected static final String ANONYMOUS = "Anonymous";

    /**
     * Create a {@link Logger} for the given {@code object}.
     * 
     * @param obj {@link Object} of which the {@link Logger} works on.
     *            Depending on the implementation, the object could be used to
     *            generate the log tag, prefix or other values in the log.
     * @return {@link Logger}
     */
    public abstract Logger create(Object obj);

    /**
     * Create a {@link Logger} for the given {@code clazz}.
     * 
     * @param cls {@link Class} of which the {@link Logger} works on.
     *            Depending on the implementation, the class could be used to
     *            generate the log tag, prefix or other values in the log.
     * @return {@link Logger}
     */
    public abstract Logger create(Class<?> cls);

    /**
     * Get the simple name of the {@code object}.
     * 
     * @param obj {@link Object} to find name. Must not be {@code null}.
     * @return The type name of the object or {@value #ANONYMOUS} if type name
     *         not available.
     */
    protected String getSimpleName(Object obj) {
        String name;

        // Get the object's type name, or its superclass's type name.
        if (TextUtils.isEmpty(name = obj.getClass().getSimpleName())) {
            name = obj.getClass().getSuperclass().getSimpleName();
        }

        return TextUtils.isEmpty(name) ? ANONYMOUS : name;
    }
}
