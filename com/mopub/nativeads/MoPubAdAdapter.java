package com.mopub.nativeads;

import android.app.Activity;
import android.database.DataSetObserver;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.mopub.common.Preconditions.NoThrow;
import com.mopub.common.VisibleForTesting;
import com.mopub.nativeads.MoPubNativeAdPositioning.MoPubClientPositioning;
import com.mopub.nativeads.MoPubNativeAdPositioning.MoPubServerPositioning;
import java.util.List;
import java.util.WeakHashMap;

public class MoPubAdAdapter extends BaseAdapter {
    @Nullable
    private MoPubNativeAdLoadedListener mAdLoadedListener;
    @NonNull
    private final Adapter mOriginalAdapter;
    @NonNull
    private final MoPubStreamAdPlacer mStreamAdPlacer;
    @NonNull
    private final WeakHashMap<View, Integer> mViewPositionMap;
    @NonNull
    private final VisibilityTracker mVisibilityTracker;

    class C37251 implements VisibilityTrackerListener {
        C37251() {
        }

        public void onVisibilityChanged(@NonNull List<View> visibleViews, List<View> list) {
            MoPubAdAdapter.this.handleVisibilityChange(visibleViews);
        }
    }

    class C37262 extends DataSetObserver {
        C37262() {
        }

        public void onChanged() {
            MoPubAdAdapter.this.mStreamAdPlacer.setItemCount(MoPubAdAdapter.this.mOriginalAdapter.getCount());
            MoPubAdAdapter.this.notifyDataSetChanged();
        }

        public void onInvalidated() {
            MoPubAdAdapter.this.notifyDataSetInvalidated();
        }
    }

    class C37273 implements MoPubNativeAdLoadedListener {
        C37273() {
        }

        public void onAdLoaded(int position) {
            MoPubAdAdapter.this.handleAdLoaded(position);
        }

        public void onAdRemoved(int position) {
            MoPubAdAdapter.this.handleAdRemoved(position);
        }
    }

    public MoPubAdAdapter(@NonNull Activity activity, @NonNull Adapter originalAdapter) {
        this(activity, originalAdapter, MoPubNativeAdPositioning.serverPositioning());
    }

    public MoPubAdAdapter(@NonNull Activity activity, @NonNull Adapter originalAdapter, @NonNull MoPubServerPositioning adPositioning) {
        this(new MoPubStreamAdPlacer(activity, adPositioning), originalAdapter, new VisibilityTracker(activity));
    }

    public MoPubAdAdapter(@NonNull Activity activity, @NonNull Adapter originalAdapter, @NonNull MoPubClientPositioning adPositioning) {
        this(new MoPubStreamAdPlacer(activity, adPositioning), originalAdapter, new VisibilityTracker(activity));
    }

    @VisibleForTesting
    MoPubAdAdapter(@NonNull MoPubStreamAdPlacer streamAdPlacer, @NonNull Adapter originalAdapter, @NonNull VisibilityTracker visibilityTracker) {
        this.mOriginalAdapter = originalAdapter;
        this.mStreamAdPlacer = streamAdPlacer;
        this.mViewPositionMap = new WeakHashMap();
        this.mVisibilityTracker = visibilityTracker;
        this.mVisibilityTracker.setVisibilityTrackerListener(new C37251());
        this.mOriginalAdapter.registerDataSetObserver(new C37262());
        this.mStreamAdPlacer.setAdLoadedListener(new C37273());
        this.mStreamAdPlacer.setItemCount(this.mOriginalAdapter.getCount());
    }

    @VisibleForTesting
    void handleAdLoaded(int position) {
        if (this.mAdLoadedListener != null) {
            this.mAdLoadedListener.onAdLoaded(position);
        }
        notifyDataSetChanged();
    }

    @VisibleForTesting
    void handleAdRemoved(int position) {
        if (this.mAdLoadedListener != null) {
            this.mAdLoadedListener.onAdRemoved(position);
        }
        notifyDataSetChanged();
    }

    public final void registerAdRenderer(@NonNull MoPubAdRenderer adRenderer) {
        if (NoThrow.checkNotNull(adRenderer, "Tried to set a null ad renderer on the placer.")) {
            this.mStreamAdPlacer.registerAdRenderer(adRenderer);
        }
    }

    public final void setAdLoadedListener(@Nullable MoPubNativeAdLoadedListener listener) {
        this.mAdLoadedListener = listener;
    }

    public void loadAds(@NonNull String adUnitId) {
        this.mStreamAdPlacer.loadAds(adUnitId);
    }

    public void loadAds(@NonNull String adUnitId, @Nullable RequestParameters requestParameters) {
        this.mStreamAdPlacer.loadAds(adUnitId, requestParameters);
    }

    public boolean isAd(int position) {
        return this.mStreamAdPlacer.isAd(position);
    }

    public void clearAds() {
        this.mStreamAdPlacer.clearAds();
    }

    public void destroy() {
        this.mStreamAdPlacer.destroy();
        this.mVisibilityTracker.destroy();
    }

    public boolean areAllItemsEnabled() {
        return (this.mOriginalAdapter instanceof ListAdapter) && ((ListAdapter) this.mOriginalAdapter).areAllItemsEnabled();
    }

    public boolean isEnabled(int position) {
        return isAd(position) || ((this.mOriginalAdapter instanceof ListAdapter) && ((ListAdapter) this.mOriginalAdapter).isEnabled(this.mStreamAdPlacer.getOriginalPosition(position)));
    }

    public int getCount() {
        return this.mStreamAdPlacer.getAdjustedCount(this.mOriginalAdapter.getCount());
    }

    @Nullable
    public Object getItem(int position) {
        Object ad = this.mStreamAdPlacer.getAdData(position);
        return ad != null ? ad : this.mOriginalAdapter.getItem(this.mStreamAdPlacer.getOriginalPosition(position));
    }

    public long getItemId(int position) {
        Object adData = this.mStreamAdPlacer.getAdData(position);
        if (adData != null) {
            return (long) (-System.identityHashCode(adData));
        }
        return this.mOriginalAdapter.getItemId(this.mStreamAdPlacer.getOriginalPosition(position));
    }

    public boolean hasStableIds() {
        return this.mOriginalAdapter.hasStableIds();
    }

    @Nullable
    public View getView(int position, View view, ViewGroup viewGroup) {
        View resultView;
        View adView = this.mStreamAdPlacer.getAdView(position, view, viewGroup);
        if (adView != null) {
            resultView = adView;
        } else {
            resultView = this.mOriginalAdapter.getView(this.mStreamAdPlacer.getOriginalPosition(position), view, viewGroup);
        }
        this.mViewPositionMap.put(resultView, Integer.valueOf(position));
        this.mVisibilityTracker.addView(resultView, 0, null);
        return resultView;
    }

    public int getItemViewType(int position) {
        int viewType = this.mStreamAdPlacer.getAdViewType(position);
        if (viewType != 0) {
            return (this.mOriginalAdapter.getViewTypeCount() + viewType) - 1;
        }
        return this.mOriginalAdapter.getItemViewType(this.mStreamAdPlacer.getOriginalPosition(position));
    }

    public int getViewTypeCount() {
        return this.mOriginalAdapter.getViewTypeCount() + this.mStreamAdPlacer.getAdViewTypeCount();
    }

    public boolean isEmpty() {
        return this.mOriginalAdapter.isEmpty() && this.mStreamAdPlacer.getAdjustedCount(0) == 0;
    }

    private void handleVisibilityChange(@NonNull List<View> visibleViews) {
        int min = Integer.MAX_VALUE;
        int max = 0;
        for (View view : visibleViews) {
            Integer pos = (Integer) this.mViewPositionMap.get(view);
            if (pos != null) {
                min = Math.min(pos.intValue(), min);
                max = Math.max(pos.intValue(), max);
            }
        }
        this.mStreamAdPlacer.placeAdsInRange(min, max + 1);
    }

    public int getOriginalPosition(int position) {
        return this.mStreamAdPlacer.getOriginalPosition(position);
    }

    public int getAdjustedPosition(int originalPosition) {
        return this.mStreamAdPlacer.getAdjustedPosition(originalPosition);
    }

    public void insertItem(int originalPosition) {
        this.mStreamAdPlacer.insertItem(originalPosition);
    }

    public void removeItem(int originalPosition) {
        this.mStreamAdPlacer.removeItem(originalPosition);
    }

    public void setOnClickListener(@NonNull ListView listView, @Nullable final OnItemClickListener listener) {
        if (!NoThrow.checkNotNull(listView, "You called MoPubAdAdapter.setOnClickListener with a null ListView")) {
            return;
        }
        if (listener == null) {
            listView.setOnItemClickListener(null);
        } else {
            listView.setOnItemClickListener(new OnItemClickListener() {
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                    if (!MoPubAdAdapter.this.mStreamAdPlacer.isAd(position)) {
                        listener.onItemClick(adapterView, view, MoPubAdAdapter.this.mStreamAdPlacer.getOriginalPosition(position), id);
                    }
                }
            });
        }
    }

    public void setOnItemLongClickListener(@NonNull ListView listView, @Nullable final OnItemLongClickListener listener) {
        if (!NoThrow.checkNotNull(listView, "You called MoPubAdAdapter.setOnItemLongClickListener with a null ListView")) {
            return;
        }
        if (listener == null) {
            listView.setOnItemLongClickListener(null);
        } else {
            listView.setOnItemLongClickListener(new OnItemLongClickListener() {
                public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                    if (!MoPubAdAdapter.this.isAd(position)) {
                        if (!listener.onItemLongClick(adapterView, view, MoPubAdAdapter.this.mStreamAdPlacer.getOriginalPosition(position), id)) {
                            return false;
                        }
                    }
                    return true;
                }
            });
        }
    }

    public void setOnItemSelectedListener(@NonNull ListView listView, @Nullable final OnItemSelectedListener listener) {
        if (!NoThrow.checkNotNull(listView, "You called MoPubAdAdapter.setOnItemSelectedListener with a null ListView")) {
            return;
        }
        if (listener == null) {
            listView.setOnItemSelectedListener(null);
        } else {
            listView.setOnItemSelectedListener(new OnItemSelectedListener() {
                public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                    if (!MoPubAdAdapter.this.isAd(position)) {
                        listener.onItemSelected(adapterView, view, MoPubAdAdapter.this.mStreamAdPlacer.getOriginalPosition(position), id);
                    }
                }

                public void onNothingSelected(AdapterView<?> adapterView) {
                    listener.onNothingSelected(adapterView);
                }
            });
        }
    }

    public void setSelection(@NonNull ListView listView, int originalPosition) {
        if (NoThrow.checkNotNull(listView, "You called MoPubAdAdapter.setSelection with a null ListView")) {
            listView.setSelection(this.mStreamAdPlacer.getAdjustedPosition(originalPosition));
        }
    }

    public void smoothScrollToPosition(@NonNull ListView listView, int originalPosition) {
        if (NoThrow.checkNotNull(listView, "You called MoPubAdAdapter.smoothScrollToPosition with a null ListView")) {
            listView.smoothScrollToPosition(this.mStreamAdPlacer.getAdjustedPosition(originalPosition));
        }
    }

    public void refreshAds(@NonNull ListView listView, @NonNull String adUnitId) {
        refreshAds(listView, adUnitId, null);
    }

    public void refreshAds(@NonNull ListView listView, @NonNull String adUnitId, @Nullable RequestParameters requestParameters) {
        if (NoThrow.checkNotNull(listView, "You called MoPubAdAdapter.refreshAds with a null ListView")) {
            View firstView = listView.getChildAt(0);
            int offsetY = firstView == null ? 0 : firstView.getTop();
            int firstPosition = listView.getFirstVisiblePosition();
            int startRange = Math.max(firstPosition - 1, 0);
            while (this.mStreamAdPlacer.isAd(startRange) && startRange > 0) {
                startRange--;
            }
            int lastPosition = listView.getLastVisiblePosition();
            while (this.mStreamAdPlacer.isAd(lastPosition) && lastPosition < getCount() - 1) {
                lastPosition++;
            }
            int originalStartRange = this.mStreamAdPlacer.getOriginalPosition(startRange);
            this.mStreamAdPlacer.removeAdsInRange(this.mStreamAdPlacer.getOriginalCount(lastPosition + 1), this.mStreamAdPlacer.getOriginalCount(getCount()));
            int numAdsRemoved = this.mStreamAdPlacer.removeAdsInRange(0, originalStartRange);
            if (numAdsRemoved > 0) {
                listView.setSelectionFromTop(firstPosition - numAdsRemoved, offsetY);
            }
            loadAds(adUnitId, requestParameters);
        }
    }
}
