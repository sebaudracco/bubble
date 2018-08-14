package com.mopub.nativeads;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import com.mopub.common.Preconditions.NoThrow;
import com.mopub.common.logging.MoPubLog;
import java.util.HashSet;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;

public abstract class BaseNativeAd {
    @NonNull
    private final Set<String> mClickTrackers = new HashSet();
    @NonNull
    private final Set<String> mImpressionTrackers = new HashSet();
    @Nullable
    private NativeEventListener mNativeEventListener;

    public interface NativeEventListener {
        void onAdClicked();

        void onAdImpressed();
    }

    public abstract void clear(@NonNull View view);

    public abstract void destroy();

    public abstract void prepare(@NonNull View view);

    protected BaseNativeAd() {
    }

    public void setNativeEventListener(@Nullable NativeEventListener nativeEventListener) {
        this.mNativeEventListener = nativeEventListener;
    }

    protected final void notifyAdImpressed() {
        if (this.mNativeEventListener != null) {
            this.mNativeEventListener.onAdImpressed();
        }
    }

    protected final void notifyAdClicked() {
        if (this.mNativeEventListener != null) {
            this.mNativeEventListener.onAdClicked();
        }
    }

    protected final void addImpressionTrackers(Object impressionTrackers) throws ClassCastException {
        if (impressionTrackers instanceof JSONArray) {
            JSONArray trackers = (JSONArray) impressionTrackers;
            for (int i = 0; i < trackers.length(); i++) {
                try {
                    addImpressionTracker(trackers.getString(i));
                } catch (JSONException e) {
                    MoPubLog.m12061d("Unable to parse impression trackers.");
                }
            }
            return;
        }
        throw new ClassCastException("Expected impression trackers of type JSONArray.");
    }

    protected final void addClickTrackers(Object clickTrackers) throws ClassCastException {
        if (clickTrackers instanceof JSONArray) {
            JSONArray trackers = (JSONArray) clickTrackers;
            for (int i = 0; i < trackers.length(); i++) {
                try {
                    addClickTracker(trackers.getString(i));
                } catch (JSONException e) {
                    MoPubLog.m12061d("Unable to parse click trackers.");
                }
            }
            return;
        }
        throw new ClassCastException("Expected click trackers of type JSONArray.");
    }

    public final void addImpressionTracker(@NonNull String url) {
        if (NoThrow.checkNotNull(url, "impressionTracker url is not allowed to be null")) {
            this.mImpressionTrackers.add(url);
        }
    }

    public final void addClickTracker(@NonNull String url) {
        if (NoThrow.checkNotNull(url, "clickTracker url is not allowed to be null")) {
            this.mClickTrackers.add(url);
        }
    }

    @NonNull
    Set<String> getImpressionTrackers() {
        return new HashSet(this.mImpressionTrackers);
    }

    @NonNull
    Set<String> getClickTrackers() {
        return new HashSet(this.mClickTrackers);
    }
}
