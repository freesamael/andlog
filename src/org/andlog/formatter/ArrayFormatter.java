
package org.andlog.formatter;

import java.util.Arrays;

import org.andlog.Builder;

/**
 * Format objects using one of the variants of {@link Arrays#toString(Object[])}
 * .
 * 
 * @author samael_wang
 */
public class ArrayFormatter implements Formatter {

    @Override
    public boolean format(Builder builder, StringBuilder sb, Object obj) {
        if (obj instanceof boolean[]) {
            sb.append(Arrays.toString((boolean[]) obj));
        } else if (obj instanceof byte[]) {
            sb.append(Arrays.toString((byte[]) obj));
        } else if (obj instanceof short[]) {
            sb.append(Arrays.toString((short[]) obj));
        } else if (obj instanceof char[]) {
            sb.append(Arrays.toString((char[]) obj));
        } else if (obj instanceof int[]) {
            sb.append(Arrays.toString((int[]) obj));
        } else if (obj instanceof long[]) {
            sb.append(Arrays.toString((long[]) obj));
        } else if (obj instanceof float[]) {
            sb.append(Arrays.toString((float[]) obj));
        } else if (obj instanceof double[]) {
            sb.append(Arrays.toString((double[]) obj));
        } else if (obj instanceof Object[]) {
            sb.append(Arrays.deepToString((Object[]) obj));
        }

        return false;
    }

}
