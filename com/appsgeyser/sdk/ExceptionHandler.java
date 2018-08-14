package com.appsgeyser.sdk;

class ExceptionHandler {
    ExceptionHandler() {
    }

    static void handleException(Exception e) {
        Logger.ErrorLog(e.getMessage());
    }
}
