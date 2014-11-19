
package org.andlog.formatter;

import org.andlog.Builder;

import android.app.PendingIntent;
import android.content.Intent;

/**
 * Extracts and format the internal intent by a hidden {@code getIntent} method.
 * 
 * @author samael_wang
 */
public class PendingIntentFormatter implements Formatter {
    private final String METHOD_GET_INTENT = "getIntent";

    @Override
    public boolean format(Builder builder, StringBuilder sb, Object obj) {
        if (obj instanceof PendingIntent) {
            // Try to extract intent.
            Intent intent = null;
            try {
                intent = (Intent) obj.getClass().getMethod(METHOD_GET_INTENT, (Class[]) null)
                        .invoke(obj, (Object[]) null);
            } catch (Exception e) {
                // Ignore the error.
            }

            // Format the intent if possible.
            if (intent != null) {
                sb.append("PendingIntent {");
                builder.format(sb, intent);
                sb.append('}');
            } else {
                sb.append(obj.toString());
            }

            return true;
        }
        return false;
    }

}
