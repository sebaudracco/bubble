package com.moat.analytics.mobile.vng;

import android.app.Activity;
import android.view.View;
import com.moat.analytics.mobile.vng.C3535x.C3469a;
import com.moat.analytics.mobile.vng.p130a.p132b.C3474a;
import java.util.Map;

public class ReactiveVideoTrackerPlugin implements MoatPlugin<ReactiveVideoTracker> {
    private final String f8862a;

    class C34701 implements C3469a<ReactiveVideoTracker> {
        final /* synthetic */ ReactiveVideoTrackerPlugin f8861a;

        C34701(ReactiveVideoTrackerPlugin reactiveVideoTrackerPlugin) {
            this.f8861a = reactiveVideoTrackerPlugin;
        }

        public C3474a<ReactiveVideoTracker> mo6526a() {
            C3511p.m11978a("[INFO] ", "Attempting to create ReactiveVideoTracker");
            return C3474a.m11841a(new C3536y(this.f8861a.f8862a));
        }
    }

    static class C3471a implements ReactiveVideoTracker {
        C3471a() {
        }

        public void changeTargetView(View view) {
        }

        public void dispatchEvent(MoatAdEvent moatAdEvent) {
        }

        public void setActivity(Activity activity) {
        }

        public void setPlayerVolume(Double d) {
        }

        public void stopTracking() {
        }

        public boolean trackVideoAd(Map<String, String> map, Integer num, View view) {
            return false;
        }
    }

    public ReactiveVideoTrackerPlugin(String str) {
        this.f8862a = str;
    }

    public /* synthetic */ Object mo6527a() {
        return m11836c();
    }

    public /* synthetic */ Object mo6528b() {
        return m11837d();
    }

    public ReactiveVideoTracker m11836c() {
        return (ReactiveVideoTracker) C3535x.m12027a(new C34701(this), ReactiveVideoTracker.class);
    }

    public ReactiveVideoTracker m11837d() {
        return new C3471a();
    }
}
