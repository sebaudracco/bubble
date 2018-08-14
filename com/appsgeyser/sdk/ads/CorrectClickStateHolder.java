package com.appsgeyser.sdk.ads;

public class CorrectClickStateHolder {
    private static CorrectClickStateHolder instance;
    private int state = 0;

    public static CorrectClickStateHolder getInstance() {
        CorrectClickStateHolder localInstance = instance;
        if (localInstance == null) {
            synchronized (CorrectClickStateHolder.class) {
                try {
                    localInstance = instance;
                    if (localInstance == null) {
                        CorrectClickStateHolder localInstance2 = new CorrectClickStateHolder();
                        try {
                            instance = localInstance2;
                            localInstance = localInstance2;
                        } catch (Throwable th) {
                            Throwable th2 = th;
                            localInstance = localInstance2;
                            throw th2;
                        }
                    }
                } catch (Throwable th3) {
                    th2 = th3;
                    throw th2;
                }
            }
        }
        return localInstance;
    }

    private CorrectClickStateHolder() {
    }

    void reset() {
        this.state = 0;
    }

    void allowClick() {
        this.state++;
    }

    boolean isClickAllowed() {
        return this.state > 0;
    }
}
