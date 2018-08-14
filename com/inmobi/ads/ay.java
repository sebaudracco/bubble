package com.inmobi.ads;

import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import com.inmobi.ads.bv.C3024a;

/* compiled from: NativeV2VisibilityChecker */
public final class ay implements C3024a {
    private static volatile ay f7202a;
    private final Rect f7203b = new Rect();

    public static ay m9452a() {
        ay ayVar = f7202a;
        synchronized (ay.class) {
            if (ayVar == null) {
                ayVar = new ay();
                f7202a = ayVar;
            }
        }
        return ayVar;
    }

    public boolean mo6209a(@Nullable View view, @Nullable View view2, int i, @NonNull Object obj) {
        if (!(obj instanceof ai)) {
            return false;
        }
        ai aiVar = (ai) obj;
        if (aiVar.mo6176c() || view2 == null || view == null || !view2.isShown() || view.getParent() == null || !view2.getGlobalVisibleRect(this.f7203b)) {
            return false;
        }
        long height = ((long) this.f7203b.height()) * ((long) this.f7203b.width());
        long k = ((long) aiVar.m9339k().m9091k()) * ((long) aiVar.m9339k().m9092l());
        if (k <= 0 || height * 100 < k * ((long) i)) {
            return false;
        }
        return true;
    }
}
