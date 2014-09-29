
package org.andlog.formatter;

import org.andlog.Builder;

/**
 * Interface of formatters to format objects to log messages.
 * 
 * @author samael_wang
 */
public interface Formatter {

    /**
     * Format an object to a log message and append it to the given
     * {@code builder}.
     * 
     * @param builder {@link Builder} of which it works on.
     * @param sb {@link StringBuilder} to append the formatted
     *            message to. Must not be {@code null}.
     * @param obj {@link Object} to format. Could be {@code null}.
     * @return {@code true} if the formatter accepts and formats the
     *         {@code object}, {@code false} otherwise.
     */
    public boolean format(Builder builder, StringBuilder sb, Object obj);
}
