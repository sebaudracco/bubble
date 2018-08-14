package com.vungle.publisher;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.Size;

/* compiled from: vungle */
public class VunglePub extends VunglePubBase {
    public static final String VERSION = VunglePubBase.VERSION;
    private static final VunglePub f9752n = new VunglePub();

    public static VunglePub getInstance() {
        return f9752n;
    }

    VunglePub() {
    }

    public void addEventListeners(VungleAdEventListener... eventListeners) {
        super.addEventListeners(eventListeners);
    }

    public void clearEventListeners() {
        super.clearEventListeners();
    }

    public void clearAndSetEventListeners(VungleAdEventListener... eventListeners) {
        super.clearAndSetEventListeners(eventListeners);
    }

    public void removeEventListeners(VungleAdEventListener... eventListeners) {
        super.removeEventListeners(eventListeners);
    }

    public void init(@NonNull Context context, @NonNull String vungleAppId, @Size(min = 1) @NonNull String[] placementReferenceIds, @Nullable VungleInitListener initListener) {
        super.init(context, vungleAppId, placementReferenceIds, initListener);
    }

    @Nullable
    public AdConfig getGlobalAdConfig() {
        return super.getGlobalAdConfig();
    }

    public boolean isInitialized() {
        return super.isInitialized();
    }

    public boolean isAdPlayable(@NonNull String placementReferenceId) {
        return super.isAdPlayable(placementReferenceId);
    }

    public void loadAd(@NonNull String placementReferenceId) {
        super.loadAd(placementReferenceId);
    }

    public void playAd(@NonNull String placementReferenceId, @Nullable AdConfig adConfig) {
        super.playAd(placementReferenceId, adConfig);
    }

    public boolean closeFlexViewAd(@NonNull String placementReferenceId) {
        return super.closeFlexViewAd(placementReferenceId);
    }
}
