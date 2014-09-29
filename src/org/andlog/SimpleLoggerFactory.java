
package org.andlog;

import org.andlog.formatter.Formatter;
import org.andlog.formatter.SimpleFormatter;

/**
 * The {@link Logger} created by {@link SimpleLoggerFactory} uses the type name
 * of the object / class it works on as log tag and simplest / fastest formatter
 * to format the message.
 * 
 * @author samael_wang
 */
public class SimpleLoggerFactory extends LoggerFactory {

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
     * Get the {@link Builder} instance to use.
     * 
     * @return {@link Builder}
     */
    public Builder getBuilder() {
        return new Builder(false/* extractMethodName */, new Formatter[] {
                new SimpleFormatter()
        });
    }
}
