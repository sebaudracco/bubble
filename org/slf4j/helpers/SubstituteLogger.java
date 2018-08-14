package org.slf4j.helpers;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Queue;
import org.slf4j.Logger;
import org.slf4j.Marker;
import org.slf4j.event.EventRecodingLogger;
import org.slf4j.event.LoggingEvent;
import org.slf4j.event.SubstituteLoggingEvent;

public class SubstituteLogger implements Logger {
    private volatile Logger _delegate;
    private final boolean createdPostInitialization;
    private Boolean delegateEventAware;
    private Queue<SubstituteLoggingEvent> eventQueue;
    private EventRecodingLogger eventRecodingLogger;
    private Method logMethodCache;
    private final String name;

    public SubstituteLogger(String name, Queue<SubstituteLoggingEvent> eventQueue, boolean createdPostInitialization) {
        this.name = name;
        this.eventQueue = eventQueue;
        this.createdPostInitialization = createdPostInitialization;
    }

    public String getName() {
        return this.name;
    }

    public boolean isTraceEnabled() {
        return delegate().isTraceEnabled();
    }

    public void trace(String msg) {
        delegate().trace(msg);
    }

    public void trace(String format, Object arg) {
        delegate().trace(format, arg);
    }

    public void trace(String format, Object arg1, Object arg2) {
        delegate().trace(format, arg1, arg2);
    }

    public void trace(String format, Object... arguments) {
        delegate().trace(format, arguments);
    }

    public void trace(String msg, Throwable t) {
        delegate().trace(msg, t);
    }

    public boolean isTraceEnabled(Marker marker) {
        return delegate().isTraceEnabled(marker);
    }

    public void trace(Marker marker, String msg) {
        delegate().trace(marker, msg);
    }

    public void trace(Marker marker, String format, Object arg) {
        delegate().trace(marker, format, arg);
    }

    public void trace(Marker marker, String format, Object arg1, Object arg2) {
        delegate().trace(marker, format, arg1, arg2);
    }

    public void trace(Marker marker, String format, Object... arguments) {
        delegate().trace(marker, format, arguments);
    }

    public void trace(Marker marker, String msg, Throwable t) {
        delegate().trace(marker, msg, t);
    }

    public boolean isDebugEnabled() {
        return delegate().isDebugEnabled();
    }

    public void debug(String msg) {
        delegate().debug(msg);
    }

    public void debug(String format, Object arg) {
        delegate().debug(format, arg);
    }

    public void debug(String format, Object arg1, Object arg2) {
        delegate().debug(format, arg1, arg2);
    }

    public void debug(String format, Object... arguments) {
        delegate().debug(format, arguments);
    }

    public void debug(String msg, Throwable t) {
        delegate().debug(msg, t);
    }

    public boolean isDebugEnabled(Marker marker) {
        return delegate().isDebugEnabled(marker);
    }

    public void debug(Marker marker, String msg) {
        delegate().debug(marker, msg);
    }

    public void debug(Marker marker, String format, Object arg) {
        delegate().debug(marker, format, arg);
    }

    public void debug(Marker marker, String format, Object arg1, Object arg2) {
        delegate().debug(marker, format, arg1, arg2);
    }

    public void debug(Marker marker, String format, Object... arguments) {
        delegate().debug(marker, format, arguments);
    }

    public void debug(Marker marker, String msg, Throwable t) {
        delegate().debug(marker, msg, t);
    }

    public boolean isInfoEnabled() {
        return delegate().isInfoEnabled();
    }

    public void info(String msg) {
        delegate().info(msg);
    }

    public void info(String format, Object arg) {
        delegate().info(format, arg);
    }

    public void info(String format, Object arg1, Object arg2) {
        delegate().info(format, arg1, arg2);
    }

    public void info(String format, Object... arguments) {
        delegate().info(format, arguments);
    }

    public void info(String msg, Throwable t) {
        delegate().info(msg, t);
    }

    public boolean isInfoEnabled(Marker marker) {
        return delegate().isInfoEnabled(marker);
    }

    public void info(Marker marker, String msg) {
        delegate().info(marker, msg);
    }

    public void info(Marker marker, String format, Object arg) {
        delegate().info(marker, format, arg);
    }

    public void info(Marker marker, String format, Object arg1, Object arg2) {
        delegate().info(marker, format, arg1, arg2);
    }

    public void info(Marker marker, String format, Object... arguments) {
        delegate().info(marker, format, arguments);
    }

    public void info(Marker marker, String msg, Throwable t) {
        delegate().info(marker, msg, t);
    }

    public boolean isWarnEnabled() {
        return delegate().isWarnEnabled();
    }

    public void warn(String msg) {
        delegate().warn(msg);
    }

    public void warn(String format, Object arg) {
        delegate().warn(format, arg);
    }

    public void warn(String format, Object arg1, Object arg2) {
        delegate().warn(format, arg1, arg2);
    }

    public void warn(String format, Object... arguments) {
        delegate().warn(format, arguments);
    }

    public void warn(String msg, Throwable t) {
        delegate().warn(msg, t);
    }

    public boolean isWarnEnabled(Marker marker) {
        return delegate().isWarnEnabled(marker);
    }

    public void warn(Marker marker, String msg) {
        delegate().warn(marker, msg);
    }

    public void warn(Marker marker, String format, Object arg) {
        delegate().warn(marker, format, arg);
    }

    public void warn(Marker marker, String format, Object arg1, Object arg2) {
        delegate().warn(marker, format, arg1, arg2);
    }

    public void warn(Marker marker, String format, Object... arguments) {
        delegate().warn(marker, format, arguments);
    }

    public void warn(Marker marker, String msg, Throwable t) {
        delegate().warn(marker, msg, t);
    }

    public boolean isErrorEnabled() {
        return delegate().isErrorEnabled();
    }

    public void error(String msg) {
        delegate().error(msg);
    }

    public void error(String format, Object arg) {
        delegate().error(format, arg);
    }

    public void error(String format, Object arg1, Object arg2) {
        delegate().error(format, arg1, arg2);
    }

    public void error(String format, Object... arguments) {
        delegate().error(format, arguments);
    }

    public void error(String msg, Throwable t) {
        delegate().error(msg, t);
    }

    public boolean isErrorEnabled(Marker marker) {
        return delegate().isErrorEnabled(marker);
    }

    public void error(Marker marker, String msg) {
        delegate().error(marker, msg);
    }

    public void error(Marker marker, String format, Object arg) {
        delegate().error(marker, format, arg);
    }

    public void error(Marker marker, String format, Object arg1, Object arg2) {
        delegate().error(marker, format, arg1, arg2);
    }

    public void error(Marker marker, String format, Object... arguments) {
        delegate().error(marker, format, arguments);
    }

    public void error(Marker marker, String msg, Throwable t) {
        delegate().error(marker, msg, t);
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (this.name.equals(((SubstituteLogger) o).name)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return this.name.hashCode();
    }

    Logger delegate() {
        if (this._delegate != null) {
            return this._delegate;
        }
        if (this.createdPostInitialization) {
            return NOPLogger.NOP_LOGGER;
        }
        return getEventRecordingLogger();
    }

    private Logger getEventRecordingLogger() {
        if (this.eventRecodingLogger == null) {
            this.eventRecodingLogger = new EventRecodingLogger(this, this.eventQueue);
        }
        return this.eventRecodingLogger;
    }

    public void setDelegate(Logger delegate) {
        this._delegate = delegate;
    }

    public boolean isDelegateEventAware() {
        if (this.delegateEventAware != null) {
            return this.delegateEventAware.booleanValue();
        }
        try {
            this.logMethodCache = this._delegate.getClass().getMethod("log", new Class[]{LoggingEvent.class});
            this.delegateEventAware = Boolean.TRUE;
        } catch (NoSuchMethodException e) {
            this.delegateEventAware = Boolean.FALSE;
        }
        return this.delegateEventAware.booleanValue();
    }

    public void log(LoggingEvent event) {
        if (isDelegateEventAware()) {
            try {
                this.logMethodCache.invoke(this._delegate, new Object[]{event});
            } catch (IllegalAccessException e) {
            } catch (IllegalArgumentException e2) {
            } catch (InvocationTargetException e3) {
            }
        }
    }

    public boolean isDelegateNull() {
        return this._delegate == null;
    }

    public boolean isDelegateNOP() {
        return this._delegate instanceof NOPLogger;
    }
}
