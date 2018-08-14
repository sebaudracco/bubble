package com.mobfox.sdk.runnables;

import android.content.Context;
import com.mobfox.sdk.nativeads.Native;

public abstract class NativeRunnable extends MobFoxRunnable {
    Native aNative;

    public NativeRunnable(Context context, Native aNative) {
        super(context);
        this.aNative = aNative;
    }

    protected boolean condition() {
        return this.aNative.getListener() != null;
    }
}
