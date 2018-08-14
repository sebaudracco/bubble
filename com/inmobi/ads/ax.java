package com.inmobi.ads;

import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import com.inmobi.ads.bv.C3024a;

/* compiled from: NativeV2VideoImpressionChecker */
public class ax implements C3024a {
    private static volatile ax f7200a;
    private final Rect f7201b = new Rect();

    public static ax m9450a() {
        ax axVar = f7200a;
        synchronized (ax.class) {
            if (axVar == null) {
                axVar = new ax();
                f7200a = axVar;
            }
        }
        return axVar;
    }

    public boolean mo6209a(@Nullable View view, @Nullable View view2, int i, @NonNull Object obj) {
        if (!(obj instanceof au)) {
            return false;
        }
        if (view2 instanceof NativeStrandVideoView) {
            NativeStrandVideoView nativeStrandVideoView = (NativeStrandVideoView) view2;
            if (!nativeStrandVideoView.isShown()) {
                return false;
            }
            af mediaPlayer = nativeStrandVideoView.getMediaPlayer();
            if (!(mediaPlayer == null || 3 == mediaPlayer.m9282b())) {
                return false;
            }
        }
        if (view == null || view.getParent() == null || !view2.getGlobalVisibleRect(this.f7201b)) {
            return false;
        }
        long width = ((long) view2.getWidth()) * ((long) view2.getHeight());
        boolean z = width > 0 && (((long) this.f7201b.height()) * ((long) this.f7201b.width())) * 100 >= width * ((long) i);
        return z;
    }
}
