package com.appsgeyser.sdk.server.implementation;

import com.appsgeyser.sdk.ErrorInfo;

public abstract class RegisterAppUsage {
    public abstract void appUsageRegistered();

    public void appUsageNotRegistered(ErrorInfo error) {
    }
}
