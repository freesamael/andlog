
package org.andlog.formatter;

import java.util.HashMap;
import java.util.Map;

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

            // Use Map.toString() to extract the content of the Bundle.
            Map<String, Object> map = new HashMap<String, Object>();
            for (String key : bundle.keySet()) {
                map.put(key, bundle.get(key));
            }
            sb.append(map.toString());

            return true;
        }

        return false;
    }

}
