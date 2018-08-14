package com.inmobi.ads;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import com.inmobi.ads.C3046c.C3044h;
import java.lang.ref.WeakReference;

public abstract class ViewableAd {
    @Nullable
    protected WeakReference<View> f6949a;
    @NonNull
    private AdContainer f6950b;

    public enum AdEvent {
        AD_EVENT_IMPRESSION_RECORDED,
        AD_EVENT_ENTER_FULLSCREEN,
        AD_EVENT_EXIT_FULLSCREEN,
        AD_EVENT_CLOSED,
        AD_EVENT_CLICK_THRU,
        AD_EVENT_VIDEO_PREPARED,
        AD_EVENT_VIDEO_PLAYED,
        AD_EVENT_VIDEO_PAUSED,
        AD_EVENT_VIDEO_RESUMED,
        AD_EVENT_VIDEO_QUARTILE_1,
        AD_EVENT_VIDEO_QUARTILE_2,
        AD_EVENT_VIDEO_QUARTILE_3,
        AD_EVENT_VIDEO_PLAY_COMPLETED,
        AD_EVENT_VIDEO_MUTE,
        AD_EVENT_VIDEO_UNMUTE,
        AD_EVENT_VIDEO_SKIPPED,
        AD_EVENT_VIDEO_RESUME_INLINE,
        AD_EVENT_VIDEO_ERROR
    }

    static abstract class C2964a {
        private boolean f6948a;

        C2964a() {
        }

        public boolean mo6200b() {
            return this.f6948a;
        }

        public void mo6198a() {
            if (!this.f6948a) {
                this.f6948a = true;
            }
        }
    }

    @Nullable
    public abstract View mo6226a(View view, ViewGroup viewGroup, boolean z);

    public abstract void mo6227a(AdEvent adEvent);

    public abstract void mo6228a(@NonNull C3044h c3044h, @Nullable View... viewArr);

    public abstract void mo6229c();

    public ViewableAd(@NonNull AdContainer adContainer) {
        this.f6950b = adContainer;
    }

    @NonNull
    public final AdContainer m9109e() {
        return this.f6950b;
    }

    protected void m9103a(View view) {
        this.f6949a = new WeakReference(view);
    }

    @Nullable
    public View mo6249b() {
        if (this.f6949a == null) {
            return null;
        }
        return (View) this.f6949a.get();
    }

    @Nullable
    public View mo6225a() {
        return null;
    }

    public void mo6230d() {
        if (this.f6949a != null) {
            this.f6949a.clear();
        }
    }
}
