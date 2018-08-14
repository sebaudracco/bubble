package com.moat.analytics.mobile.mpub;

import android.app.Activity;
import android.app.Application;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import java.util.Map;

abstract class NoOp {

    public static class MoatAnalytics extends MoatAnalytics {
        public void start(MoatOptions moatOptions, Application application) {
        }

        public void start(Application application) {
        }

        public void prepareNativeDisplayTracking(String str) {
        }
    }

    public static class MoatFactory extends MoatFactory {
        public WebAdTracker createWebAdTracker(@NonNull WebView webView) {
            return new C3403e();
        }

        public WebAdTracker createWebAdTracker(@NonNull ViewGroup viewGroup) {
            return new C3403e();
        }

        public NativeDisplayTracker createNativeDisplayTracker(@NonNull View view, @NonNull Map<String, String> map) {
            return new C3402c();
        }

        public NativeVideoTracker createNativeVideoTracker(@NonNull String str) {
            return new C3401b();
        }

        public <T> T createCustomTracker(C3408l<T> c3408l) {
            return c3408l.createNoOp();
        }
    }

    static class C3401b implements NativeVideoTracker {
        C3401b() {
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

        public final void dispatchEvent(MoatAdEvent moatAdEvent) {
        }

        public final boolean trackVideoAd(Map<String, String> map, MediaPlayer mediaPlayer, View view) {
            return false;
        }

        public final void setPlayerVolume(Double d) {
        }

        public final void changeTargetView(View view) {
        }

        public final void stopTracking() {
        }
    }

    static class C3402c implements NativeDisplayTracker {
        C3402c() {
        }

        public final void startTracking() {
        }

        public final void setListener(TrackerListener trackerListener) {
        }

        public final void removeListener() {
        }

        public final void setActivity(Activity activity) {
        }

        public final void stopTracking() {
        }

        public final void reportUserInteractionEvent(NativeDisplayTracker$MoatUserInteractionType nativeDisplayTracker$MoatUserInteractionType) {
        }
    }

    static class C3403e implements WebAdTracker {
        C3403e() {
        }

        public final void startTracking() {
        }

        public final void setListener(TrackerListener trackerListener) {
        }

        public final void removeListener() {
        }

        public final void setActivity(Activity activity) {
        }

        public final void stopTracking() {
        }
    }

    NoOp() {
    }
}
