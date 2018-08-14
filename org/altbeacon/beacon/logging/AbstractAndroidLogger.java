package org.altbeacon.beacon.logging;

abstract class AbstractAndroidLogger implements Logger {
    AbstractAndroidLogger() {
    }

    protected String formatString(String message, Object... args) {
        return args.length == 0 ? message : String.format(message, args);
    }
}
