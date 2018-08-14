package com.moat.analytics.mobile.mpub;

import android.app.Activity;
import android.view.View;
import com.moat.analytics.mobile.mpub.C3445p.C3405c;
import com.moat.analytics.mobile.mpub.base.functional.Optional;
import java.util.Map;

public class ReactiveVideoTrackerPlugin implements C3408l<ReactiveVideoTracker> {
    private final String f8650;

    class C34061 implements C3405c<ReactiveVideoTracker> {
        private /* synthetic */ ReactiveVideoTrackerPlugin f8649;

        C34061(ReactiveVideoTrackerPlugin reactiveVideoTrackerPlugin) {
            this.f8649 = reactiveVideoTrackerPlugin;
        }

        public final Optional<ReactiveVideoTracker> mo6511() {
            C3412a.m11639("[INFO] ", "Attempting to create ReactiveVideoTracker");
            return Optional.of(new C3467w(this.f8649.f8650));
        }
    }

    static class C3407d implements ReactiveVideoTracker {
        C3407d() {
        }

        public final void setActivity(Activity activity) {
        }

        public final void setListener(TrackerListener trackerListener) {
        }

        public final void removeListener() {
        }

        public final void setVideoListener(VideoTrackerListener videoTrackerListener) {
        }

        public final void removeVideoListener() {
        }

        public final boolean trackVideoAd(Map<String, String> map, Integer num, View view) {
            return false;
        }

        public final void setPlayerVolume(Double d) {
        }

        public final void changeTargetView(View view) {
        }

        public final void dispatchEvent(MoatAdEvent moatAdEvent) {
        }

        public final void stopTracking() {
        }
    }

    public ReactiveVideoTrackerPlugin(String str) {
        this.f8650 = str;
    }

    public ReactiveVideoTracker create() throws C3442o {
        return (ReactiveVideoTracker) C3445p.m11762(new C34061(this), ReactiveVideoTracker.class);
    }

    public ReactiveVideoTracker createNoOp() {
        return new C3407d();
    }
}
