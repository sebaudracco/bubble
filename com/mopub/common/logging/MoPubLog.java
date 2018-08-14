package com.mopub.common.logging;

import android.support.annotation.NonNull;
import android.util.Log;
import com.mopub.common.VisibleForTesting;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class MoPubLog {
    private static final Logger LOGGER = Logger.getLogger(LOGGER_NAMESPACE);
    public static final String LOGGER_NAMESPACE = "com.mopub";
    private static final String LOGTAG = "MoPub";
    private static final MoPubLogHandler LOG_HANDLER = new MoPubLogHandler();

    private static final class MoPubLogHandler extends Handler {
        private static final Map<Level, Integer> LEVEL_TO_LOG = new HashMap(7);

        private MoPubLogHandler() {
        }

        static {
            LEVEL_TO_LOG.put(Level.FINEST, Integer.valueOf(2));
            LEVEL_TO_LOG.put(Level.FINER, Integer.valueOf(2));
            LEVEL_TO_LOG.put(Level.FINE, Integer.valueOf(2));
            LEVEL_TO_LOG.put(Level.CONFIG, Integer.valueOf(3));
            LEVEL_TO_LOG.put(Level.INFO, Integer.valueOf(4));
            LEVEL_TO_LOG.put(Level.WARNING, Integer.valueOf(5));
            LEVEL_TO_LOG.put(Level.SEVERE, Integer.valueOf(6));
        }

        public void publish(LogRecord logRecord) {
            if (isLoggable(logRecord)) {
                int priority;
                if (LEVEL_TO_LOG.containsKey(logRecord.getLevel())) {
                    priority = ((Integer) LEVEL_TO_LOG.get(logRecord.getLevel())).intValue();
                } else {
                    priority = 2;
                }
                String message = logRecord.getMessage() + "\n";
                Throwable error = logRecord.getThrown();
                if (error != null) {
                    message = message + Log.getStackTraceString(error);
                }
                Log.println(priority, "MoPub", message);
            }
        }

        public void close() {
        }

        public void flush() {
        }
    }

    static {
        LOGGER.setUseParentHandlers(false);
        LOGGER.setLevel(Level.ALL);
        LOG_HANDLER.setLevel(Level.FINE);
        LogManager.getLogManager().addLogger(LOGGER);
        addHandler(LOGGER, LOG_HANDLER);
    }

    private MoPubLog() {
    }

    public static void m12059c(String message) {
        m12060c(message, null);
    }

    public static void m12067v(String message) {
        m12068v(message, null);
    }

    public static void m12061d(String message) {
        m12062d(message, null);
    }

    public static void m12065i(String message) {
        m12066i(message, null);
    }

    public static void m12069w(String message) {
        m12070w(message, null);
    }

    public static void m12063e(String message) {
        m12064e(message, null);
    }

    public static void m12060c(String message, Throwable throwable) {
        LOGGER.log(Level.FINEST, message, throwable);
    }

    public static void m12068v(String message, Throwable throwable) {
        LOGGER.log(Level.FINE, message, throwable);
    }

    public static void m12062d(String message, Throwable throwable) {
        LOGGER.log(Level.CONFIG, message, throwable);
    }

    public static void m12066i(String message, Throwable throwable) {
        LOGGER.log(Level.INFO, message, throwable);
    }

    public static void m12070w(String message, Throwable throwable) {
        LOGGER.log(Level.WARNING, message, throwable);
    }

    public static void m12064e(String message, Throwable throwable) {
        LOGGER.log(Level.SEVERE, message, throwable);
    }

    @VisibleForTesting
    public static void setSdkHandlerLevel(@NonNull Level level) {
        LOG_HANDLER.setLevel(level);
    }

    private static void addHandler(@NonNull Logger logger, @NonNull Handler handler) {
        Handler[] currentHandlers = logger.getHandlers();
        int length = currentHandlers.length;
        int i = 0;
        while (i < length) {
            if (!currentHandlers[i].equals(handler)) {
                i++;
            } else {
                return;
            }
        }
        logger.addHandler(handler);
    }
}
