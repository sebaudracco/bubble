package com.mobfox.sdk.services;

import android.content.Context;
import android.os.Handler;
import com.mobfox.sdk.logging.MobFoxReport;

public abstract class MobFoxBaseService {
    Context context;
    Handler handler;

    public abstract void callback();

    public MobFoxBaseService(Context context) {
        this.context = context;
        this.handler = new Handler(context.getMainLooper());
    }

    public void run() {
        try {
            callback();
        } catch (Exception e) {
            MobFoxReport.postException(this.context, e, null);
        }
    }
}
