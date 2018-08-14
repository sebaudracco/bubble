package com.loopj.android.http;

public interface LogInterface {
    public static final int DEBUG = 3;
    public static final int ERROR = 6;
    public static final int INFO = 4;
    public static final int VERBOSE = 2;
    public static final int WARN = 5;
    public static final int WTF = 8;

    void mo6450d(String str, String str2);

    void mo6451d(String str, String str2, Throwable th);

    void mo6452e(String str, String str2);

    void mo6453e(String str, String str2, Throwable th);

    int getLoggingLevel();

    void mo6455i(String str, String str2);

    void mo6456i(String str, String str2, Throwable th);

    boolean isLoggingEnabled();

    void setLoggingEnabled(boolean z);

    void setLoggingLevel(int i);

    boolean shouldLog(int i);

    void mo6461v(String str, String str2);

    void mo6462v(String str, String str2, Throwable th);

    void mo6463w(String str, String str2);

    void mo6464w(String str, String str2, Throwable th);

    void wtf(String str, String str2);

    void wtf(String str, String str2, Throwable th);
}
