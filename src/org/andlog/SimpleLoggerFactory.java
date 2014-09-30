
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
    private static final int LOG_TAG_MAX = 23;
    private String mTag;

    /**
     * Construct a {@link SimpleLoggerFactory} which uses the given {@code obj}
     * to generate log tag.
     * 
     * @param obj The {@link Object} used to generate log tag.
     */
    public SimpleLoggerFactory(Object obj) {
        if (obj == null)
            throw new IllegalArgumentException("'obj' is null.");

        mTag = trimTag(getSimpleName(obj));
    }

    /**
     * Construct a {@link SimpleLoggerFactory} which uses the given {@code cls}
     * to generate log tag.
     * 
     * @param cls The class used to generate log tag.
     */
    public SimpleLoggerFactory(Class<?> cls) {
        if (cls == null)
            throw new IllegalArgumentException("'cls' is null.");

        mTag = trimTag(cls.getSimpleName());
    }

    /**
     * Construct a {@link SimpleLoggerFactory} which uses the given {@code tag}
     * directly.
     * 
     * @param tag Log tag to use.
     */
    public SimpleLoggerFactory(String tag) {
        if (TextUtils.isEmpty(tag))
            throw new IllegalArgumentException("'tag' is null or empty.");

        mTag = trimTag(tag);
    }

    @Override
    public Logger create() {
        return new Logger(mTag, null, getBuilder());
    }

    /**
     * Get the simple name of the {@code object}.
     * 
     * @param obj {@link Object} to find name. Must not be {@code null}.
     * @return The type name of the object or {@value #ANONYMOUS} if type name
     *         not available.
     */
    public String getSimpleName(Object obj) {
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
    public Builder getBuilder() {
        return new Builder(false/* extractMethodName */, new Formatter[] {
                new SimpleFormatter()
        });
    }

    /**
     * Trim the {@code tag} to ensure it's loggable. Android limits the max
     * length of a property's key to be 32 characters. Setting the loggable
     * level of a specific tag is done by {@code setprop log.tag.LOGTAG value},
     * which indicates the max length of {@code LOGTAG} can only be 23
     * characters. This method trims the tag to 23 characters or less.
     * 
     * @param tag Candidate log tag.
     * @return Trimmed log tag.
     */
    public String trimTag(String tag) {
        if (tag.length() > LOG_TAG_MAX)
            return tag.substring(0, LOG_TAG_MAX - 1);
        return tag;
    }
}
