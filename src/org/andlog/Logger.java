
package org.andlog;

import android.text.TextUtils;
import android.util.Log;

/**
 * Logger to log messages to {@link Log}.
 * 
 * @author samael_wang
 */
public class Logger {
    protected String mTag;
    protected String mPrefix;
    protected Builder mBuilder;

    public Logger(String tag, String prefix, Builder builder) {
        if (TextUtils.isEmpty(tag))
            throw new IllegalArgumentException("'tag' is null or empty.");
        if (builder == null)
            throw new IllegalArgumentException("'builder' is null.");

        mTag = tag;
        mPrefix = prefix;
        mBuilder = builder;
    }

    /**
     * Log a {@link Log#VERBOSE} level message.
     * 
     * @param objs {@link Object}s to log.
     * @see Log#v(String, String)
     */
    public void verbose(Object... objs) {
        if (BuildConfig.DEBUG || Log.isLoggable(mTag, Log.VERBOSE)) {
            String msg = mBuilder.build(mPrefix, objs);
            if (!TextUtils.isEmpty(msg)) 
                Log.v(mTag, msg);
        }
    }

    /**
     * Log a {@link Log#DEBUG} level message.
     * 
     * @param objs {@link Object}s to log.
     * @see Log#d(String, String)
     */
    public void debug(Object... objs) {
        if (BuildConfig.DEBUG || Log.isLoggable(mTag, Log.DEBUG)) {
            String msg = mBuilder.build(mPrefix, objs);
            if (!TextUtils.isEmpty(msg)) 
                Log.d(mTag, msg);
        }
    }

    /**
     * Log a {@link Log#INFO} level message.
     * 
     * @param objs {@link Object}s to log.
     * @see Log#i(String, String)
     */
    public void info(Object... objs) {
        if (BuildConfig.DEBUG || Log.isLoggable(mTag, Log.INFO)) {
            String msg = mBuilder.build(mPrefix, objs);
            if (!TextUtils.isEmpty(msg)) 
                Log.i(mTag, msg);
        }
    }

    /**
     * Log a {@link Log#WARN} level message.
     * 
     * @param objs {@link Object}s to log.
     * @see Log#w(String, String)
     */
    public void warning(Object... objs) {
        if (BuildConfig.DEBUG || Log.isLoggable(mTag, Log.WARN)) {
            String msg = mBuilder.build(mPrefix, objs);
            if (!TextUtils.isEmpty(msg)) 
                Log.w(mTag, msg);
        }
    }

    /**
     * Log a {@link Log#ERROR} level message
     * 
     * @param objs {@link Object}s to log.
     * @see Log#e(String, String)
     */
    public void error(Object... objs) {
        if (BuildConfig.DEBUG || Log.isLoggable(mTag, Log.ERROR)) {
            String msg = mBuilder.build(mPrefix, objs);
            if (!TextUtils.isEmpty(msg)) 
                Log.e(mTag, msg);
        }
    }

    /**
     * Log a {@link Log#ASSERT} level message.
     * 
     * @param objs {@link Object}s to log.
     * @see Log#wtf(String, String)
     */
    public void fatal(Object... objs) {
        if (BuildConfig.DEBUG || Log.isLoggable(mTag, Log.ASSERT)) {
            String msg = mBuilder.build(mPrefix, objs);
            if (!TextUtils.isEmpty(msg)) 
                Log.wtf(mTag, msg);
        }
    }
    
}
