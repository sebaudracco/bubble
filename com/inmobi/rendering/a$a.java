package com.inmobi.rendering;

import android.annotation.TargetApi;
import android.os.Build.VERSION;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.commons.core.utilities.info.DisplayInfo;

@TargetApi(16)
/* compiled from: JavaScriptBridge */
class a$a implements OnGlobalLayoutListener {
    private int f7993a;
    private int f7994b;
    private View f7995c;
    private final Boolean f7996d = Boolean.valueOf(false);

    a$a(View view) {
        this.f7995c = view;
    }

    public void onGlobalLayout() {
        try {
            this.f7993a = DisplayInfo.m10422b(this.f7995c.getWidth());
            this.f7994b = DisplayInfo.m10422b(this.f7995c.getHeight());
            if (VERSION.SDK_INT >= 16) {
                this.f7995c.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            } else {
                this.f7995c.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
            synchronized (this.f7996d) {
                this.f7996d.notify();
            }
        } catch (Exception e) {
            Logger.m10359a(InternalLogLevel.INTERNAL, a.a(), "SDK encountered unexpected error in JavaScriptBridge$1.onGlobalLayout(); " + e.getMessage());
        }
    }
}
