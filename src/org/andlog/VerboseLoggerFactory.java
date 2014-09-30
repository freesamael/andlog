
package org.andlog;

import org.andlog.formatter.ArrayFormatter;
import org.andlog.formatter.BundleFormatter;
import org.andlog.formatter.ContextFormatter;
import org.andlog.formatter.Formatter;
import org.andlog.formatter.IntentFormatter;
import org.andlog.formatter.PendingIntentFormatter;
import org.andlog.formatter.SimpleFormatter;
import org.andlog.formatter.ThrowableFormatter;

/**
 * The {@link Logger} created by {@link VerboseLoggerFactory} logs the most
 * detailed messages but it's also the slowest one.
 * 
 * @author samael_wang
 */
public class VerboseLoggerFactory extends SimpleLoggerFactory {

    public VerboseLoggerFactory(Object obj) {
        super(obj);
    }

    public VerboseLoggerFactory(Class<?> cls) {
        super(cls);
    }

    public VerboseLoggerFactory(String tag) {
        super(tag);
    }

    @Override
    public Builder getBuilder() {
        return new Builder(true /* extractMethodName */, new Formatter[] {
                new ContextFormatter(),
                new ArrayFormatter(),
                new BundleFormatter(),
                new IntentFormatter(),
                new PendingIntentFormatter(),
                new ThrowableFormatter(),
                new SimpleFormatter()
        });
    }
}
