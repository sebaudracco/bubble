package com.moat.analytics.mobile.vng;

import android.app.Activity;
import android.app.Application;
import android.media.MediaPlayer;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import java.util.Map;

abstract class C3523v {

    public static class C3518a extends MoatAnalytics {
        public void prepareNativeDisplayTracking(String str) {
        }

        public void start(Application application) {
        }

        public void start(MoatOptions moatOptions, Application application) {
        }
    }

    public static class C3519b extends MoatFactory {
        public <T> T createCustomTracker(MoatPlugin<T> moatPlugin) {
            return null;
        }

        public NativeDisplayTracker createNativeDisplayTracker(View view, Map<String, String> map) {
            return new C3520c();
        }

        public NativeVideoTracker createNativeVideoTracker(String str) {
            return new C3521d();
        }

        public WebAdTracker createWebAdTracker(ViewGroup viewGroup) {
            return new C3522e();
        }

        public WebAdTracker createWebAdTracker(WebView webView) {
            return new C3522e();
        }
    }

    static class C3520c implements NativeDisplayTracker {
        C3520c() {
        }

        public void reportUserInteractionEvent(NativeDisplayTracker$MoatUserInteractionType nativeDisplayTracker$MoatUserInteractionType) {
        }

        public void setActivity(Activity activity) {
        }

        public void startTracking() {
        }

        public void stopTracking() {
        }
    }

    static class C3521d implements NativeVideoTracker {
        C3521d() {
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

        public boolean trackVideoAd(Map<String, String> map, MediaPlayer mediaPlayer, View view) {
            return false;
        }
    }

    static class C3522e implements WebAdTracker {
        C3522e() {
        }

        public void setActivity(Activity activity) {
        }

        public void startTracking() {
        }

        public void stopTracking() {
        }
    }

    C3523v() {
    }
}
