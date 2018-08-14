package org.slf4j.impl;

import android.util.Log;
import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MarkerIgnoringBase;
import org.slf4j.helpers.MessageFormatter;

class AndroidLoggerAdapter extends MarkerIgnoringBase {
    private static final long serialVersionUID = -1227274521521287937L;

    AndroidLoggerAdapter(String tag) {
        this.name = tag;
    }

    public boolean isTraceEnabled() {
        return isLoggable(2);
    }

    public void trace(String msg) {
        log(2, msg, null);
    }

    public void trace(String format, Object arg) {
        formatAndLog(2, format, arg);
    }

    public void trace(String format, Object arg1, Object arg2) {
        formatAndLog(2, format, arg1, arg2);
    }

    public void trace(String format, Object... argArray) {
        formatAndLog(2, format, argArray);
    }

    public void trace(String msg, Throwable t) {
        log(2, msg, t);
    }

    public boolean isDebugEnabled() {
        return isLoggable(3);
    }

    public void debug(String msg) {
        log(3, msg, null);
    }

    public void debug(String format, Object arg) {
        formatAndLog(3, format, arg);
    }

    public void debug(String format, Object arg1, Object arg2) {
        formatAndLog(3, format, arg1, arg2);
    }

    public void debug(String format, Object... argArray) {
        formatAndLog(3, format, argArray);
    }

    public void debug(String msg, Throwable t) {
        log(2, msg, t);
    }

    public boolean isInfoEnabled() {
        return isLoggable(4);
    }

    public void info(String msg) {
        log(4, msg, null);
    }

    public void info(String format, Object arg) {
        formatAndLog(4, format, arg);
    }

    public void info(String format, Object arg1, Object arg2) {
        formatAndLog(4, format, arg1, arg2);
    }

    public void info(String format, Object... argArray) {
        formatAndLog(4, format, argArray);
    }

    public void info(String msg, Throwable t) {
        log(4, msg, t);
    }

    public boolean isWarnEnabled() {
        return isLoggable(5);
    }

    public void warn(String msg) {
        log(5, msg, null);
    }

    public void warn(String format, Object arg) {
        formatAndLog(5, format, arg);
    }

    public void warn(String format, Object arg1, Object arg2) {
        formatAndLog(5, format, arg1, arg2);
    }

    public void warn(String format, Object... argArray) {
        formatAndLog(5, format, argArray);
    }

    public void warn(String msg, Throwable t) {
        log(5, msg, t);
    }

    public boolean isErrorEnabled() {
        return isLoggable(6);
    }

    public void error(String msg) {
        log(6, msg, null);
    }

    public void error(String format, Object arg) {
        formatAndLog(6, format, arg);
    }

    public void error(String format, Object arg1, Object arg2) {
        formatAndLog(6, format, arg1, arg2);
    }

    public void error(String format, Object... argArray) {
        formatAndLog(6, format, argArray);
    }

    public void error(String msg, Throwable t) {
        log(6, msg, t);
    }

    private void formatAndLog(int priority, String format, Object... argArray) {
        if (isLoggable(priority)) {
            FormattingTuple ft = MessageFormatter.arrayFormat(format, argArray);
            logInternal(priority, ft.getMessage(), ft.getThrowable());
        }
    }

    private void log(int priority, String message, Throwable throwable) {
        if (isLoggable(priority)) {
            logInternal(priority, message, throwable);
        }
    }

    private boolean isLoggable(int priority) {
        return Log.isLoggable(this.name, priority);
    }

    private void logInternal(int priority, String message, Throwable throwable) {
        if (throwable != null) {
            message = message + '\n' + Log.getStackTraceString(throwable);
        }
        Log.println(priority, this.name, message);
    }
}
