
package org.andlog;

import org.andlog.formatter.Formatter;
import org.andlog.formatter.SimpleFormatter;

import android.text.TextUtils;

/**
 * Class to build log messages.
 * 
 * @author samael_wang
 */
public class Builder {
    private static final String NEWLINE = "\n";
    private static final String INDENT = "  ";
    private static final String SEPARATOR = ": ";
    private boolean mExtractMethodName;
    private Formatter[] mFormatters;

    /**
     * Create an instance with given {@code formatters}.
     * 
     * @param extractMethodName {@code true} to extract method names when
     *            building log messages. Extracting method is kinda slow. Use
     *            with care.
     * @param formatters {@link Formatter}s used to format objects. If more than
     *            one formatters are given, the formatters are visited in order
     *            when an object is passed in until any of them accepts and
     *            formats the object. This indicates the order of formatters
     *            matters. If {@link SimpleFormatter} is the first formatter,
     *            for example, all other formatters will never be visited since
     *            {@link SimpleFormatter} accepts all types of objects.
     */
    public Builder(boolean extractMethodName, Formatter... formatters) {
        if (formatters == null || formatters.length == 0)
            throw new IllegalArgumentException("'formatters' is null or empty.");

        mExtractMethodName = extractMethodName;
        mFormatters = formatters;
    }

    /**
     * Format the given {@code object} using the formatters of this
     * {@link Builder}.
     * 
     * @param stringBuilder {@link StringBuilder} to append the formatted
     *            message to. Must not be {@code null}.
     * @param obj {@link Object} to format. Could be {@code null}.
     */
    public void format(StringBuilder stringBuilder, Object obj) {
        /* Iterate all formatters until any of them accepts the object. */
        for (Formatter fmt : mFormatters) {
            if (fmt.format(this, stringBuilder, obj))
                break;
        }
    }

    /**
     * Build a log message.
     * 
     * @param prefix Prefix of the message. Could be {@code null}.
     * @param objs {@link Object}s to build message. Must not be {@code null}.
     * @return Formatted {@link String} to log.
     */
    public String build(String prefix, Object... objs) {
        // Only build message if necessary.
        if (mExtractMethodName || objs.length > 0) {

            // Create StringBuilder.
            StringBuilder sb = TextUtils.isEmpty(prefix) ? new StringBuilder()
                    : new StringBuilder(prefix).append(SEPARATOR);

            // Append method name.
            if (mExtractMethodName)
                sb.append(getStackTraceElement().getMethodName()).append(SEPARATOR);

            // Append formatted messages from objects.
            for (Object obj : objs)
                format(sb, obj);

            // Indent all new lines.
            int idx = sb.indexOf(NEWLINE);
            while (idx != -1) {
                int base = idx + NEWLINE.length();
                sb.insert(base, INDENT);
                idx = sb.indexOf(NEWLINE, base);
            }

            return sb.toString();
        }

        return null;
    }

    /**
     * Get the stack trace element of current thread.
     * 
     * @return {@link StackTraceElement}
     */
    private StackTraceElement getStackTraceElement() {
        // The code snippet comes from java.util.logging.LogRecord.
        boolean sawLogger = false;
        for (StackTraceElement element : new Throwable().getStackTrace()) {
            String current = element.getClassName();
            if (current.startsWith(Logger.class.getName())) {
                sawLogger = true;
            } else if (sawLogger) {
                return element;
            }
        }

        throw new IllegalStateException(
                "new Throwable().getStackTrace() returns null or empty stack.");
    }

}
