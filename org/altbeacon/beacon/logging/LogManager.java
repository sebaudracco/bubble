package org.altbeacon.beacon.logging;

public final class LogManager {
    private static Logger sLogger = Loggers.infoLogger();
    private static boolean sVerboseLoggingEnabled = false;

    public static void setLogger(Logger logger) {
        if (logger == null) {
            throw new NullPointerException("Logger may not be null.");
        }
        sLogger = logger;
    }

    public static Logger getLogger() {
        return sLogger;
    }

    public static boolean isVerboseLoggingEnabled() {
        return sVerboseLoggingEnabled;
    }

    public static void setVerboseLoggingEnabled(boolean enabled) {
        sVerboseLoggingEnabled = enabled;
    }

    public static void m16377v(String tag, String message, Object... args) {
        sLogger.mo9828v(tag, message, args);
    }

    public static void m16378v(Throwable t, String tag, String message, Object... args) {
        sLogger.mo9829v(t, tag, message, args);
    }

    public static void m16371d(String tag, String message, Object... args) {
        sLogger.mo9822d(tag, message, args);
    }

    public static void m16372d(Throwable t, String tag, String message, Object... args) {
        sLogger.mo9823d(t, tag, message, args);
    }

    public static void m16375i(String tag, String message, Object... args) {
        sLogger.mo9826i(tag, message, args);
    }

    public static void m16376i(Throwable t, String tag, String message, Object... args) {
        sLogger.mo9827i(t, tag, message, args);
    }

    public static void m16379w(String tag, String message, Object... args) {
        sLogger.mo9830w(tag, message, args);
    }

    public static void m16380w(Throwable t, String tag, String message, Object... args) {
        sLogger.mo9831w(t, tag, message, args);
    }

    public static void m16373e(String tag, String message, Object... args) {
        sLogger.mo9824e(tag, message, args);
    }

    public static void m16374e(Throwable t, String tag, String message, Object... args) {
        sLogger.mo9825e(t, tag, message, args);
    }

    private LogManager() {
    }
}
