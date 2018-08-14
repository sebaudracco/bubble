package cz.msebera.android.httpclient.extras;

import android.util.Log;

public class HttpClientAndroidLog {
    private boolean debugEnabled = false;
    private boolean errorEnabled = false;
    private boolean infoEnabled = false;
    private String logTag;
    private boolean traceEnabled = false;
    private boolean warnEnabled = false;

    public HttpClientAndroidLog(Object tag) {
        this.logTag = tag.toString();
    }

    public void enableDebug(boolean enable) {
        this.debugEnabled = enable;
    }

    public boolean isDebugEnabled() {
        return this.debugEnabled;
    }

    public void debug(Object message) {
        if (isDebugEnabled()) {
            Log.d(this.logTag, message.toString());
        }
    }

    public void debug(Object message, Throwable t) {
        if (isDebugEnabled()) {
            Log.d(this.logTag, message.toString(), t);
        }
    }

    public void enableError(boolean enable) {
        this.errorEnabled = enable;
    }

    public boolean isErrorEnabled() {
        return this.errorEnabled;
    }

    public void error(Object message) {
        if (isErrorEnabled()) {
            Log.e(this.logTag, message.toString());
        }
    }

    public void error(Object message, Throwable t) {
        if (isErrorEnabled()) {
            Log.e(this.logTag, message.toString(), t);
        }
    }

    public void enableWarn(boolean enable) {
        this.warnEnabled = enable;
    }

    public boolean isWarnEnabled() {
        return this.warnEnabled;
    }

    public void warn(Object message) {
        if (isWarnEnabled()) {
            Log.w(this.logTag, message.toString());
        }
    }

    public void warn(Object message, Throwable t) {
        if (isWarnEnabled()) {
            Log.w(this.logTag, message.toString(), t);
        }
    }

    public void enableInfo(boolean enable) {
        this.infoEnabled = enable;
    }

    public boolean isInfoEnabled() {
        return this.infoEnabled;
    }

    public void info(Object message) {
        if (isInfoEnabled()) {
            Log.i(this.logTag, message.toString());
        }
    }

    public void info(Object message, Throwable t) {
        if (isInfoEnabled()) {
            Log.i(this.logTag, message.toString(), t);
        }
    }

    public void enableTrace(boolean enable) {
        this.traceEnabled = enable;
    }

    public boolean isTraceEnabled() {
        return this.traceEnabled;
    }

    public void trace(Object message) {
        if (isTraceEnabled()) {
            Log.i(this.logTag, message.toString());
        }
    }

    public void trace(Object message, Throwable t) {
        if (isTraceEnabled()) {
            Log.i(this.logTag, message.toString(), t);
        }
    }
}
