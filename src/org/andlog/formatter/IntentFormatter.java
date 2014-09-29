
package org.andlog.formatter;

import org.andlog.Builder;

import android.content.Intent;

/**
 * Format the intent using a hidden {@code toShortString} method.
 * 
 * @author samael_wang
 */
public class IntentFormatter implements Formatter {
    private static final String INTENT_EXTRAS = "(has extras)";
    private static final String METHOD_TO_SHORT_STRING = "toShortString";

    @Override
    public boolean format(Builder builder, StringBuilder sb, Object obj) {
        if (obj instanceof Intent) {
            StringBuilder isb = new StringBuilder("Intent { ");

            /*
             * Try to use hidden method: public void toShortString(StringBuilder
             * b, boolean secure, boolean comp, boolean extras, boolean clip).
             * If failed, use ordinary toString().
             */
            try {
                obj.getClass().getMethod(METHOD_TO_SHORT_STRING, new Class[] {
                        StringBuilder.class, boolean.class, boolean.class,
                        boolean.class, boolean.class
                }).invoke(obj, new Object[] {
                        isb, false, true, true, true
                });
            } catch (Exception e) {
                isb.append(obj.toString());
            }
            isb.append(" }");

            // Format extras.
            int eidx = isb.indexOf(INTENT_EXTRAS);
            if (eidx != -1) {
                StringBuilder esb = new StringBuilder("extras=");
                builder.format(esb, ((Intent) obj).getExtras());

                // Replace original string.
                isb.replace(eidx, eidx + INTENT_EXTRAS.length(),
                        esb.toString());
            }

            sb.append(isb);

            return true;
        }
        return false;
    }
}
