package com.fyber.utils;

import android.util.Log;
import com.adcolony.sdk.AdColonyAppOptions;
import java.util.HashSet;
import java.util.Set;

public class FyberLogger {
    private static boolean f6568a = false;
    private static FyberLogger f6569b = new FyberLogger();
    private Set<LoggerListener> f6570c = new HashSet();

    public enum Level {
        VERBOSE,
        DEBUG,
        INFO,
        WARNING,
        ERROR
    }

    public static boolean toggleLogging() {
        boolean z = !f6568a;
        f6568a = z;
        return z;
    }

    public static boolean isLogging() {
        return f6568a;
    }

    public static boolean enableLogging(boolean z) {
        f6568a = z;
        return z;
    }

    public static void m8449e(String str, String str2) {
        if (m8447a()) {
            Log.e("[FYB] " + str, StringUtils.nullToEmpty(str2));
            f6569b.log(Level.ERROR, str, str2, null);
        }
    }

    public static void m8450e(String str, String str2, Exception exception) {
        if (m8447a()) {
            Log.w("[FYB] " + str, StringUtils.nullToEmpty(str2), exception);
            f6569b.log(Level.ERROR, str, str2, exception);
        }
    }

    public static void m8448d(String str, String str2) {
        if (m8447a()) {
            Log.d("[FYB] " + str, StringUtils.nullToEmpty(str2));
            f6569b.log(Level.DEBUG, str, str2, null);
        }
    }

    public static void m8451i(String str, String str2) {
        if (m8447a()) {
            Log.i("[FYB] " + str, StringUtils.nullToEmpty(str2));
            f6569b.log(Level.INFO, str, str2, null);
        }
    }

    public static void m8452v(String str, String str2) {
        if (m8447a()) {
            Log.v("[FYB] " + str, StringUtils.nullToEmpty(str2));
            f6569b.log(Level.VERBOSE, str, str2, null);
        }
    }

    public static void m8453w(String str, String str2) {
        if (m8447a()) {
            Log.w("[FYB] " + str, StringUtils.nullToEmpty(str2));
            f6569b.log(Level.WARNING, str, str2, null);
        }
    }

    public static void m8454w(String str, String str2, Exception exception) {
        if (m8447a()) {
            Log.w("[FYB] " + str, StringUtils.nullToEmpty(str2), exception);
            f6569b.log(Level.WARNING, str, str2, exception);
        }
    }

    private static boolean m8447a() {
        return f6568a || Log.isLoggable(AdColonyAppOptions.FYBER, 2);
    }

    private FyberLogger() {
    }

    public void log(Level level, String str, String str2, Exception exception) {
        if (!this.f6570c.isEmpty()) {
            final Level level2 = level;
            final String str3 = str;
            final String str4 = str2;
            final Exception exception2 = exception;
            new Thread(new Runnable(this) {
                final /* synthetic */ FyberLogger f6567e;

                public final void run() {
                    for (LoggerListener log : this.f6567e.f6570c) {
                        log.log(level2, str3, str4, exception2);
                    }
                }
            }).start();
        }
    }

    public static boolean addLoggerListener(LoggerListener loggerListener) {
        return f6569b.f6570c.add(loggerListener);
    }

    public static boolean removeLoggerListener(LoggerListener loggerListener) {
        return f6569b.f6570c.remove(loggerListener);
    }
}
