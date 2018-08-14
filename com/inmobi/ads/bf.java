package com.inmobi.ads;

import android.os.Handler;
import android.os.Message;
import com.inmobi.commons.core.p115d.C3132b;
import com.inmobi.commons.core.p115d.C3135c;
import com.inmobi.rendering.RenderView;
import java.lang.ref.WeakReference;

/* compiled from: RenderTimeoutHandler */
final class bf extends Handler {
    private WeakReference<AdUnit> f7256a;

    bf(AdUnit adUnit) {
        this.f7256a = new WeakReference(adUnit);
    }

    public void handleMessage(Message message) {
        AdUnit adUnit = (AdUnit) this.f7256a.get();
        if (adUnit != null) {
            switch (message.what) {
                case 0:
                    try {
                        RenderView renderView = (RenderView) adUnit.mo6167t();
                        if (renderView != null) {
                            renderView.stopLoading();
                            adUnit.m8707N();
                            return;
                        }
                        return;
                    } catch (Throwable e) {
                        C3135c.m10255a().m10279a(new C3132b(e));
                        return;
                    } finally {
                        adUnit.m8707N();
                    }
                default:
                    return;
            }
        }
    }
}
