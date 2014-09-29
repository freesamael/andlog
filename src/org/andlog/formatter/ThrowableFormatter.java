
package org.andlog.formatter;

import org.andlog.Builder;

import android.util.Log;

/**
 * Format {@link Throwable}s using {@link Log#getStackTraceString(Throwable)}.
 * 
 * @author samael_wang
 */
public class ThrowableFormatter implements Formatter {

    @Override
    public boolean format(Builder builder, StringBuilder sb, Object obj) {
        if (obj instanceof Throwable) {
            sb.append(Log.getStackTraceString((Throwable) obj));
            return true;
        }
        return false;
    }

}
