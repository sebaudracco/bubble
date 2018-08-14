package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import javax.annotation.concurrent.GuardedBy;

@zzadh
@TargetApi(19)
public final class zzabq extends zzabn {
    private Object zzbzn = new Object();
    @GuardedBy("mPopupWindowLock")
    private PopupWindow zzbzo;
    @GuardedBy("mPopupWindowLock")
    private boolean zzbzp = false;

    zzabq(Context context, zzaji com_google_android_gms_internal_ads_zzaji, zzaqw com_google_android_gms_internal_ads_zzaqw, zzabm com_google_android_gms_internal_ads_zzabm) {
        super(context, com_google_android_gms_internal_ads_zzaji, com_google_android_gms_internal_ads_zzaqw, com_google_android_gms_internal_ads_zzabm);
    }

    private final void zznv() {
        synchronized (this.zzbzn) {
            this.zzbzp = true;
            if ((this.mContext instanceof Activity) && ((Activity) this.mContext).isDestroyed()) {
                this.zzbzo = null;
            }
            if (this.zzbzo != null) {
                if (this.zzbzo.isShowing()) {
                    this.zzbzo.dismiss();
                }
                this.zzbzo = null;
            }
        }
    }

    public final void cancel() {
        zznv();
        super.cancel();
    }

    protected final void zznu() {
        Window window = this.mContext instanceof Activity ? ((Activity) this.mContext).getWindow() : null;
        if (window != null && window.getDecorView() != null && !((Activity) this.mContext).isDestroyed()) {
            View frameLayout = new FrameLayout(this.mContext);
            frameLayout.setLayoutParams(new LayoutParams(-1, -1));
            frameLayout.addView(this.zzbnd.getView(), -1, -1);
            synchronized (this.zzbzn) {
                if (this.zzbzp) {
                    return;
                }
                this.zzbzo = new PopupWindow(frameLayout, 1, 1, false);
                this.zzbzo.setOutsideTouchable(true);
                this.zzbzo.setClippingEnabled(false);
                zzane.zzck("Displaying the 1x1 popup off the screen.");
                try {
                    this.zzbzo.showAtLocation(window.getDecorView(), 0, -1, -1);
                } catch (Exception e) {
                    this.zzbzo = null;
                }
            }
        }
    }

    protected final void zzz(int i) {
        zznv();
        super.zzz(i);
    }
}
