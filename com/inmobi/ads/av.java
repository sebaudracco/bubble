package com.inmobi.ads;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import com.inmobi.ads.AdContainer.RenderingProperties.PlacementType;
import com.inmobi.ads.C3046c.C3044h;
import com.inmobi.ads.C3087t.C2969a;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;

/* compiled from: NativeV2VideoAdTracker */
class av extends aj {
    private static final String f7192b = av.class.getSimpleName();
    private static final C2969a f7193c = new C30221();

    /* compiled from: NativeV2VideoAdTracker */
    static class C30221 implements C2969a {
        C30221() {
        }

        public void mo6147a(View view, Object obj) {
            ((au) obj).mo6205a(view);
        }
    }

    av() {
    }

    public void m9437a(@NonNull Context context, @NonNull View view, @NonNull ai aiVar, @NonNull C3044h c3044h) {
        if (aiVar.m9330d() == PlacementType.PLACEMENT_TYPE_FULLSCREEN) {
            m9352a(context, c3044h, f7193c, ax.m9450a()).m9987a(view, aiVar, c3044h.m9698c(), c3044h.m9699d());
        } else {
            Logger.m10359a(InternalLogLevel.INTERNAL, f7192b, "Infeed video is currently not checked for viewability. Impression beacon is fired on video play");
        }
    }
}
