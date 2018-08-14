package com.yandex.metrica;

import java.util.Map;

public interface IReporter {
    void onPauseSession();

    void onResumeSession();

    void reportError(String str, Throwable th);

    void reportEvent(String str);

    void reportEvent(String str, String str2);

    void reportEvent(String str, Map<String, Object> map);

    void reportUnhandledException(Throwable th);

    void setSessionTimeout(int i);
}
