package org.altbeacon.beacon.logging;

import android.util.Log;

final class WarningAndroidLogger extends AbstractAndroidLogger {
    WarningAndroidLogger() {
    }

    public void mo9828v(String tag, String message, Object... args) {
    }

    public void mo9829v(Throwable t, String tag, String message, Object... args) {
    }

    public void mo9822d(String tag, String message, Object... args) {
    }

    public void mo9823d(Throwable t, String tag, String message, Object... args) {
    }

    public void mo9826i(String tag, String message, Object... args) {
    }

    public void mo9827i(Throwable t, String tag, String message, Object... args) {
    }

    public void mo9830w(String tag, String message, Object... args) {
        Log.w(tag, formatString(message, args));
    }

    public void mo9831w(Throwable t, String tag, String message, Object... args) {
        Log.w(tag, formatString(message, args), t);
    }

    public void mo9824e(String tag, String message, Object... args) {
        Log.e(tag, formatString(message, args));
    }

    public void mo9825e(Throwable t, String tag, String message, Object... args) {
        Log.e(tag, formatString(message, args), t);
    }
}
