package com.mobfox.sdk.runnables;

import android.content.Context;
import android.util.Log;
import com.mobfox.sdk.constants.Constants;
import com.mobfox.sdk.logging.MobFoxReport;

public abstract class MobFoxRunnable implements Runnable {
    Context context;

    public abstract void mobFoxRun();

    public MobFoxRunnable(Context context) {
        this.context = context;
    }

    public void run() {
        try {
            if (condition()) {
                mobFoxRun();
            }
        } catch (Exception err) {
            if (!MobFoxReport.getCause(err).contains(WebViewRunnable.CONNECTION_EXCEPTION)) {
                MobFoxReport.postException(this.context, err, null);
            }
        } catch (Throwable t) {
            Log.d(Constants.MOBFOX_BANNER, "mobFoxRunnable err " + t.toString());
        }
    }

    protected boolean condition() {
        return true;
    }
}
