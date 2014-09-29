
package org.andlog;

import org.andlog.formatter.Formatter;
import org.andlog.formatter.SimpleFormatter;

import android.text.TextUtils;

/**
 * The {@link Logger} created by {@link SimpleLoggerFactory} uses the type name
 * of the object / class it works on as log tag and simplest / fastest formatter
 * to format the message.
 * 
 * @author samael_wang
 */
public class SimpleLoggerFactory implements LoggerFactory {
    protected static final String ANONYMOUS = "Anonymous";

    @Override
    public Logger create(Object obj) {
        if (obj == null)
            throw new IllegalArgumentException("'obj' is null.");

        return new Logger(getSimpleName(obj), null, getBuilder());
    }

    @Override
    public Logger create(Class<?> cls) {
        if (cls == null)
            throw new IllegalArgumentException("'cls' is null.");

        return new Logger(cls.getSimpleName(), null, getBuilder());
    }

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

    /**
     * Get the {@link Builder} instance to use.
     * 
     * @return {@link Builder}
     */
    protected Builder getBuilder() {
        return new Builder(false/* extractMethodName */, new Formatter[] {
                new SimpleFormatter()
        });
    }

}
