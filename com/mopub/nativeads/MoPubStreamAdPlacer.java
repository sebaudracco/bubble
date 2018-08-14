package com.mopub.nativeads;

import android.app.Activity;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import com.mopub.common.Preconditions;
import com.mopub.common.Preconditions.NoThrow;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.logging.MoPubLog;
import com.mopub.nativeads.MoPubNativeAdPositioning.MoPubClientPositioning;
import com.mopub.nativeads.MoPubNativeAdPositioning.MoPubServerPositioning;
import com.mopub.nativeads.PositioningSource.PositioningListener;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.WeakHashMap;

public class MoPubStreamAdPlacer {
    public static final int CONTENT_VIEW_TYPE = 0;
    private static final int DEFAULT_AD_VIEW_TYPE = -1;
    private static final MoPubNativeAdLoadedListener EMPTY_NATIVE_AD_LOADED_LISTENER = new C37371();
    private static final int MAX_VISIBLE_RANGE = 100;
    private static final int RANGE_BUFFER = 6;
    @NonNull
    private final Activity mActivity;
    @NonNull
    private MoPubNativeAdLoadedListener mAdLoadedListener;
    @NonNull
    private final NativeAdSource mAdSource;
    @Nullable
    private String mAdUnitId;
    private boolean mHasPlacedAds;
    private boolean mHasReceivedAds;
    private boolean mHasReceivedPositions;
    private int mItemCount;
    @NonNull
    private final WeakHashMap<View, NativeAd> mNativeAdMap;
    private boolean mNeedsPlacement;
    @Nullable
    private PlacementData mPendingPlacementData;
    @NonNull
    private PlacementData mPlacementData;
    @NonNull
    private final Handler mPlacementHandler;
    @NonNull
    private final Runnable mPlacementRunnable;
    @NonNull
    private final PositioningSource mPositioningSource;
    @NonNull
    private final HashMap<NativeAd, WeakReference<View>> mViewMap;
    private int mVisibleRangeEnd;
    private int mVisibleRangeStart;

    static class C37371 implements MoPubNativeAdLoadedListener {
        C37371() {
        }

        public void onAdLoaded(int position) {
        }

        public void onAdRemoved(int position) {
        }
    }

    class C37382 implements Runnable {
        C37382() {
        }

        public void run() {
            if (MoPubStreamAdPlacer.this.mNeedsPlacement) {
                MoPubStreamAdPlacer.this.placeAds();
                MoPubStreamAdPlacer.this.mNeedsPlacement = false;
            }
        }
    }

    class C37393 implements PositioningListener {
        C37393() {
        }

        public void onLoad(@NonNull MoPubClientPositioning positioning) {
            MoPubStreamAdPlacer.this.handlePositioningLoad(positioning);
        }

        public void onFailed() {
            MoPubLog.m12061d("Unable to show ads because ad positions could not be loaded from the MoPub ad server.");
        }
    }

    class C37404 implements NativeAdSource$AdSourceListener {
        C37404() {
        }

        public void onAdsAvailable() {
            MoPubStreamAdPlacer.this.handleAdsAvailable();
        }
    }

    public MoPubStreamAdPlacer(@NonNull Activity activity) {
        this(activity, MoPubNativeAdPositioning.serverPositioning());
    }

    public MoPubStreamAdPlacer(@NonNull Activity activity, @NonNull MoPubServerPositioning adPositioning) {
        this(activity, new NativeAdSource(), new ServerPositioningSource(activity));
    }

    public MoPubStreamAdPlacer(@NonNull Activity activity, @NonNull MoPubClientPositioning adPositioning) {
        this(activity, new NativeAdSource(), new ClientPositioningSource(adPositioning));
    }

    @VisibleForTesting
    MoPubStreamAdPlacer(@NonNull Activity activity, @NonNull NativeAdSource adSource, @NonNull PositioningSource positioningSource) {
        this.mAdLoadedListener = EMPTY_NATIVE_AD_LOADED_LISTENER;
        Preconditions.checkNotNull(activity, "activity is not allowed to be null");
        Preconditions.checkNotNull(adSource, "adSource is not allowed to be null");
        Preconditions.checkNotNull(positioningSource, "positioningSource is not allowed to be null");
        this.mActivity = activity;
        this.mPositioningSource = positioningSource;
        this.mAdSource = adSource;
        this.mPlacementData = PlacementData.empty();
        this.mNativeAdMap = new WeakHashMap();
        this.mViewMap = new HashMap();
        this.mPlacementHandler = new Handler();
        this.mPlacementRunnable = new C37382();
        this.mVisibleRangeStart = 0;
        this.mVisibleRangeEnd = 0;
    }

    public void registerAdRenderer(@NonNull MoPubAdRenderer adRenderer) {
        if (NoThrow.checkNotNull(adRenderer, "Cannot register a null adRenderer")) {
            this.mAdSource.registerAdRenderer(adRenderer);
        }
    }

    @Nullable
    public MoPubAdRenderer getAdRendererForViewType(int viewType) {
        return this.mAdSource.getAdRendererForViewType(viewType);
    }

    public void setAdLoadedListener(@Nullable MoPubNativeAdLoadedListener listener) {
        if (listener == null) {
            listener = EMPTY_NATIVE_AD_LOADED_LISTENER;
        }
        this.mAdLoadedListener = listener;
    }

    public void loadAds(@NonNull String adUnitId) {
        loadAds(adUnitId, null);
    }

    public void loadAds(@NonNull String adUnitId, @Nullable RequestParameters requestParameters) {
        if (!NoThrow.checkNotNull(adUnitId, "Cannot load ads with a null ad unit ID")) {
            return;
        }
        if (this.mAdSource.getAdRendererCount() == 0) {
            MoPubLog.m12069w("You must register at least 1 ad renderer by calling registerAdRenderer before loading ads");
            return;
        }
        this.mAdUnitId = adUnitId;
        this.mHasPlacedAds = false;
        this.mHasReceivedPositions = false;
        this.mHasReceivedAds = false;
        this.mPositioningSource.loadPositions(adUnitId, new C37393());
        this.mAdSource.setAdSourceListener(new C37404());
        this.mAdSource.loadAds(this.mActivity, adUnitId, requestParameters);
    }

    @VisibleForTesting
    void handlePositioningLoad(@NonNull MoPubClientPositioning positioning) {
        PlacementData placementData = PlacementData.fromAdPositioning(positioning);
        if (this.mHasReceivedAds) {
            placeInitialAds(placementData);
        } else {
            this.mPendingPlacementData = placementData;
        }
        this.mHasReceivedPositions = true;
    }

    @VisibleForTesting
    void handleAdsAvailable() {
        if (this.mHasPlacedAds) {
            notifyNeedsPlacement();
            return;
        }
        if (this.mHasReceivedPositions) {
            placeInitialAds(this.mPendingPlacementData);
        }
        this.mHasReceivedAds = true;
    }

    private void placeInitialAds(PlacementData placementData) {
        removeAdsInRange(0, this.mItemCount);
        this.mPlacementData = placementData;
        placeAds();
        this.mHasPlacedAds = true;
    }

    public void placeAdsInRange(int startPosition, int endPosition) {
        this.mVisibleRangeStart = startPosition;
        this.mVisibleRangeEnd = Math.min(endPosition, startPosition + 100);
        notifyNeedsPlacement();
    }

    public boolean isAd(int position) {
        return this.mPlacementData.isPlacedAd(position);
    }

    public void clearAds() {
        removeAdsInRange(0, this.mItemCount);
        this.mAdSource.clear();
    }

    public void destroy() {
        this.mPlacementHandler.removeMessages(0);
        this.mAdSource.clear();
        this.mPlacementData.clearAds();
    }

    @Nullable
    public Object getAdData(int position) {
        return this.mPlacementData.getPlacedAd(position);
    }

    @Nullable
    public View getAdView(int position, @Nullable View convertView, @Nullable ViewGroup parent) {
        NativeAd nativeAd = this.mPlacementData.getPlacedAd(position);
        if (nativeAd == null) {
            return null;
        }
        View view;
        if (convertView != null) {
            view = convertView;
        } else {
            view = nativeAd.createAdView(this.mActivity, parent);
        }
        bindAdView(nativeAd, view);
        return view;
    }

    public void bindAdView(@NonNull NativeAd nativeAd, @NonNull View adView) {
        WeakReference<View> mappedViewRef = (WeakReference) this.mViewMap.get(nativeAd);
        View mappedView = null;
        if (mappedViewRef != null) {
            mappedView = (View) mappedViewRef.get();
        }
        if (!adView.equals(mappedView)) {
            clearNativeAd(mappedView);
            clearNativeAd(adView);
            prepareNativeAd(nativeAd, adView);
            nativeAd.renderAdView(adView);
        }
    }

    public int removeAdsInRange(int originalStartPosition, int originalEndPosition) {
        int[] positions = this.mPlacementData.getPlacedAdPositions();
        int adjustedStartRange = this.mPlacementData.getAdjustedPosition(originalStartPosition);
        int adjustedEndRange = this.mPlacementData.getAdjustedPosition(originalEndPosition);
        ArrayList<Integer> removedPositions = new ArrayList();
        for (int i = positions.length - 1; i >= 0; i--) {
            int position = positions[i];
            if (position >= adjustedStartRange && position < adjustedEndRange) {
                removedPositions.add(Integer.valueOf(position));
                if (position < this.mVisibleRangeStart) {
                    this.mVisibleRangeStart--;
                }
                this.mItemCount--;
            }
        }
        int clearedAdsCount = this.mPlacementData.clearAdsInRange(adjustedStartRange, adjustedEndRange);
        Iterator it = removedPositions.iterator();
        while (it.hasNext()) {
            this.mAdLoadedListener.onAdRemoved(((Integer) it.next()).intValue());
        }
        return clearedAdsCount;
    }

    public int getAdViewTypeCount() {
        return this.mAdSource.getAdRendererCount();
    }

    public int getAdViewType(int position) {
        NativeAd nativeAd = this.mPlacementData.getPlacedAd(position);
        if (nativeAd == null) {
            return 0;
        }
        return this.mAdSource.getViewTypeForAd(nativeAd);
    }

    public int getOriginalPosition(int position) {
        return this.mPlacementData.getOriginalPosition(position);
    }

    public int getAdjustedPosition(int originalPosition) {
        return this.mPlacementData.getAdjustedPosition(originalPosition);
    }

    public int getOriginalCount(int count) {
        return this.mPlacementData.getOriginalCount(count);
    }

    public int getAdjustedCount(int originalCount) {
        return this.mPlacementData.getAdjustedCount(originalCount);
    }

    public void setItemCount(int originalCount) {
        this.mItemCount = this.mPlacementData.getAdjustedCount(originalCount);
        if (this.mHasPlacedAds) {
            notifyNeedsPlacement();
        }
    }

    public void insertItem(int originalPosition) {
        this.mPlacementData.insertItem(originalPosition);
    }

    public void removeItem(int originalPosition) {
        this.mPlacementData.removeItem(originalPosition);
    }

    public void moveItem(int originalPosition, int newPosition) {
        this.mPlacementData.moveItem(originalPosition, newPosition);
    }

    private void notifyNeedsPlacement() {
        if (!this.mNeedsPlacement) {
            this.mNeedsPlacement = true;
            this.mPlacementHandler.post(this.mPlacementRunnable);
        }
    }

    private void placeAds() {
        if (tryPlaceAdsInRange(this.mVisibleRangeStart, this.mVisibleRangeEnd)) {
            tryPlaceAdsInRange(this.mVisibleRangeEnd, this.mVisibleRangeEnd + 6);
        }
    }

    private boolean tryPlaceAdsInRange(int start, int end) {
        int position = start;
        int lastPosition = end - 1;
        while (position <= lastPosition && position != -1 && position < this.mItemCount) {
            if (this.mPlacementData.shouldPlaceAd(position)) {
                if (!tryPlaceAd(position)) {
                    return false;
                }
                lastPosition++;
            }
            position = this.mPlacementData.nextInsertionPosition(position);
        }
        return true;
    }

    private boolean tryPlaceAd(int position) {
        NativeAd nativeAd = this.mAdSource.dequeueAd();
        if (nativeAd == null) {
            return false;
        }
        this.mPlacementData.placeAd(position, nativeAd);
        this.mItemCount++;
        this.mAdLoadedListener.onAdLoaded(position);
        return true;
    }

    private void clearNativeAd(@Nullable View view) {
        if (view != null) {
            NativeAd lastNativeAd = (NativeAd) this.mNativeAdMap.get(view);
            if (lastNativeAd != null) {
                lastNativeAd.clear(view);
                this.mNativeAdMap.remove(view);
                this.mViewMap.remove(lastNativeAd);
            }
        }
    }

    private void prepareNativeAd(@NonNull NativeAd nativeAd, @NonNull View view) {
        this.mViewMap.put(nativeAd, new WeakReference(view));
        this.mNativeAdMap.put(view, nativeAd);
        nativeAd.prepare(view);
    }
}
