package com.google.android.gms.ads.internal.overlay;

import android.content.Context;
import android.view.MotionEvent;
import android.widget.RelativeLayout;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.ads.zzadh;
import com.google.android.gms.internal.ads.zzald;

@zzadh
@VisibleForTesting
final class zzh extends RelativeLayout {
    @VisibleForTesting
    private zzald zzaed;
    @VisibleForTesting
    boolean zzbyh;

    public zzh(Context context, String str, String str2) {
        super(context);
        this.zzaed = new zzald(context, str);
        this.zzaed.zzda(str2);
    }

    public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (!this.zzbyh) {
            this.zzaed.zze(motionEvent);
        }
        return false;
    }
}
