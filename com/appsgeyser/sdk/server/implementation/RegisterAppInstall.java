package com.appsgeyser.sdk.server.implementation;

import com.appsgeyser.sdk.ErrorInfo;

public abstract class RegisterAppInstall {
    public abstract void appGuidRegistered(String str);

    public void appGuidNotRegistered(ErrorInfo err) {
    }
}
