
package org.andlog.formatter;

import org.andlog.Builder;

/**
 * Simple formatter simply appends the {@code object} to the given
 * {@code builder} directly. It should always be the last of formatters when
 * constructing {@link Builder} since it accepts all types of objects.
 * 
 * @author samael_wang
 */
public class SimpleFormatter implements Formatter {

    @Override
    public boolean format(Builder builder, StringBuilder sb, Object obj) {
        sb.append(obj);
        return true;
    }

}
