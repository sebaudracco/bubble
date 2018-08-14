package com.fyber.utils;

import com.fyber.utils.FyberLogger.Level;

public interface LoggerListener {
    void log(Level level, String str, String str2, Exception exception);
}
