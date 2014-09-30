
package org.andlog;

/**
 * Interface of factories used to create {@link Logger} instances.
 * 
 * @author samael_wang
 */
public interface LoggerFactory {

    /**
     * Create a {@link Logger}.
     * 
     * @return {@link Logger}
     */
    public Logger create();

}
