
package org.andlog.formatter;

import java.util.Iterator;

import org.andlog.Builder;

import android.os.Bundle;

/**
 * {@link BundleFormatter} extracts all values from the {@link Bundle}.
 * 
 * @author samael_wang
 */
public class BundleFormatter implements Formatter {

    @Override
    public boolean format(Builder builder, StringBuilder sb, Object obj) {
        if (obj instanceof Bundle) {
            Bundle bundle = (Bundle) obj;

            // The extraction resembles Map.toString() implementation.
            if (bundle.isEmpty()) {
                sb.append("Bundle {}");
            } else {
                sb.append("Bundle {");
                Iterator<String> iter = bundle.keySet().iterator();
                while (iter.hasNext()) {
                    String key = iter.next();
                    sb.append(key);
                    Object value = bundle.get(key);
                    sb.append('=');
                    if (value != this) {
                        builder.format(sb, value);
                    } else {
                        sb.append("(this Bundle)");
                    }
                    if (iter.hasNext()) {
                        sb.append(", ");
                    }
                }
                sb.append('}');
            }

            return true;
        }

        return false;
    }

}
