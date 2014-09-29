
package org.andlog.formatter;

import org.andlog.Builder;

import android.content.Context;

/**
 * {@link ContextFormatter} appends hash code after the type name of the
 * {@link Context} object to distinguish different instances in logs.
 * 
 * @author samael_wang
 */
public class ContextFormatter implements Formatter {

    @Override
    public boolean format(Builder builder, StringBuilder sb, Object obj) {
        if (obj instanceof Context) {
            sb.append(obj.getClass().getSimpleName()).append("{")
                    .append(Integer.toHexString(obj.hashCode())).append("}");
            return true;
        }

        return false;
    }

}
