package com.mopub.nativeads;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.AdapterDataObserver;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.ViewGroup;
import com.mopub.common.Preconditions.NoThrow;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.logging.MoPubLog;
import com.mopub.nativeads.MoPubNativeAdPositioning.MoPubClientPositioning;
import com.mopub.nativeads.MoPubNativeAdPositioning.MoPubServerPositioning;
import java.util.List;
import java.util.WeakHashMap;

public final class MoPubRecyclerAdapter extends Adapter<ViewHolder> {
    static final int NATIVE_AD_VIEW_TYPE_BASE = -56;
    @Nullable
    private MoPubNativeAdLoadedListener mAdLoadedListener;
    @NonNull
    private final AdapterDataObserver mAdapterDataObserver;
    @NonNull
    private final Adapter mOriginalAdapter;
    @Nullable
    private RecyclerView mRecyclerView;
    @NonNull
    private ContentChangeStrategy mStrategy;
    @NonNull
    private final MoPubStreamAdPlacer mStreamAdPlacer;
    @NonNull
    private final WeakHashMap<View, Integer> mViewPositionMap;
    @NonNull
    private final VisibilityTracker mVisibilityTracker;

    class C37341 implements VisibilityTrackerListener {
        C37341() {
        }

        public void onVisibilityChanged(List<View> visibleViews, List<View> invisibleViews) {
            MoPubRecyclerAdapter.this.handleVisibilityChanged(visibleViews, invisibleViews);
        }
    }

    class C37352 implements MoPubNativeAdLoadedListener {
        C37352() {
        }

        public void onAdLoaded(int position) {
            MoPubRecyclerAdapter.this.handleAdLoaded(position);
        }

        public void onAdRemoved(int position) {
            MoPubRecyclerAdapter.this.handleAdRemoved(position);
        }
    }

    class C37363 extends AdapterDataObserver {
        C37363() {
        }

        public void onChanged() {
            MoPubRecyclerAdapter.this.mStreamAdPlacer.setItemCount(MoPubRecyclerAdapter.this.mOriginalAdapter.getItemCount());
            MoPubRecyclerAdapter.this.notifyDataSetChanged();
        }

        public void onItemRangeChanged(int positionStart, int itemCount) {
            int adjustedEndPosition = MoPubRecyclerAdapter.this.mStreamAdPlacer.getAdjustedPosition((positionStart + itemCount) - 1);
            int adjustedStartPosition = MoPubRecyclerAdapter.this.mStreamAdPlacer.getAdjustedPosition(positionStart);
            MoPubRecyclerAdapter.this.notifyItemRangeChanged(adjustedStartPosition, (adjustedEndPosition - adjustedStartPosition) + 1);
        }

        public void onItemRangeInserted(int positionStart, int itemCount) {
            int adjustedStartPosition = MoPubRecyclerAdapter.this.mStreamAdPlacer.getAdjustedPosition(positionStart);
            int newOriginalCount = MoPubRecyclerAdapter.this.mOriginalAdapter.getItemCount();
            MoPubRecyclerAdapter.this.mStreamAdPlacer.setItemCount(newOriginalCount);
            boolean addingToEnd = positionStart + itemCount >= newOriginalCount;
            if (ContentChangeStrategy.KEEP_ADS_FIXED == MoPubRecyclerAdapter.this.mStrategy || (ContentChangeStrategy.INSERT_AT_END == MoPubRecyclerAdapter.this.mStrategy && addingToEnd)) {
                MoPubRecyclerAdapter.this.notifyDataSetChanged();
                return;
            }
            for (int i = 0; i < itemCount; i++) {
                MoPubRecyclerAdapter.this.mStreamAdPlacer.insertItem(positionStart);
            }
            MoPubRecyclerAdapter.this.notifyItemRangeInserted(adjustedStartPosition, itemCount);
        }

        public void onItemRangeRemoved(int positionStart, int itemsRemoved) {
            int adjustedStartPosition = MoPubRecyclerAdapter.this.mStreamAdPlacer.getAdjustedPosition(positionStart);
            int newOriginalCount = MoPubRecyclerAdapter.this.mOriginalAdapter.getItemCount();
            MoPubRecyclerAdapter.this.mStreamAdPlacer.setItemCount(newOriginalCount);
            boolean removingFromEnd = positionStart + itemsRemoved >= newOriginalCount;
            if (ContentChangeStrategy.KEEP_ADS_FIXED == MoPubRecyclerAdapter.this.mStrategy || (ContentChangeStrategy.INSERT_AT_END == MoPubRecyclerAdapter.this.mStrategy && removingFromEnd)) {
                MoPubRecyclerAdapter.this.notifyDataSetChanged();
                return;
            }
            int oldAdjustedCount = MoPubRecyclerAdapter.this.mStreamAdPlacer.getAdjustedCount(newOriginalCount + itemsRemoved);
            for (int i = 0; i < itemsRemoved; i++) {
                MoPubRecyclerAdapter.this.mStreamAdPlacer.removeItem(positionStart);
            }
            int itemsRemovedIncludingAds = oldAdjustedCount - MoPubRecyclerAdapter.this.mStreamAdPlacer.getAdjustedCount(newOriginalCount);
            MoPubRecyclerAdapter.this.notifyItemRangeRemoved(adjustedStartPosition - (itemsRemovedIncludingAds - itemsRemoved), itemsRemovedIncludingAds);
        }

        public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
            MoPubRecyclerAdapter.this.notifyDataSetChanged();
        }
    }

    public enum ContentChangeStrategy {
        INSERT_AT_END,
        MOVE_ALL_ADS_WITH_CONTENT,
        KEEP_ADS_FIXED
    }

    public MoPubRecyclerAdapter(@NonNull Activity activity, @NonNull Adapter originalAdapter) {
        this(activity, originalAdapter, MoPubNativeAdPositioning.serverPositioning());
    }

    public MoPubRecyclerAdapter(@NonNull Activity activity, @NonNull Adapter originalAdapter, @NonNull MoPubServerPositioning adPositioning) {
        this(new MoPubStreamAdPlacer(activity, adPositioning), originalAdapter, new VisibilityTracker(activity));
    }

    public MoPubRecyclerAdapter(@NonNull Activity activity, @NonNull Adapter originalAdapter, @NonNull MoPubClientPositioning adPositioning) {
        this(new MoPubStreamAdPlacer(activity, adPositioning), originalAdapter, new VisibilityTracker(activity));
    }

    @VisibleForTesting
    MoPubRecyclerAdapter(@NonNull MoPubStreamAdPlacer streamAdPlacer, @NonNull Adapter originalAdapter, @NonNull VisibilityTracker visibilityTracker) {
        this.mStrategy = ContentChangeStrategy.INSERT_AT_END;
        this.mViewPositionMap = new WeakHashMap();
        this.mOriginalAdapter = originalAdapter;
        this.mVisibilityTracker = visibilityTracker;
        this.mVisibilityTracker.setVisibilityTrackerListener(new C37341());
        setHasStableIdsInternal(this.mOriginalAdapter.hasStableIds());
        this.mStreamAdPlacer = streamAdPlacer;
        this.mStreamAdPlacer.setAdLoadedListener(new C37352());
        this.mStreamAdPlacer.setItemCount(this.mOriginalAdapter.getItemCount());
        this.mAdapterDataObserver = new C37363();
        this.mOriginalAdapter.registerAdapterDataObserver(this.mAdapterDataObserver);
    }

    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.mRecyclerView = recyclerView;
    }

    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        this.mRecyclerView = null;
    }

    public void setAdLoadedListener(@Nullable MoPubNativeAdLoadedListener listener) {
        this.mAdLoadedListener = listener;
    }

    public void registerAdRenderer(@NonNull MoPubAdRenderer adRenderer) {
        if (NoThrow.checkNotNull(adRenderer, "Cannot register a null adRenderer")) {
            this.mStreamAdPlacer.registerAdRenderer(adRenderer);
        }
    }

    public void loadAds(@NonNull String adUnitId) {
        this.mStreamAdPlacer.loadAds(adUnitId);
    }

    public void loadAds(@NonNull String adUnitId, @Nullable RequestParameters requestParameters) {
        this.mStreamAdPlacer.loadAds(adUnitId, requestParameters);
    }

    public static int computeScrollOffset(@NonNull LinearLayoutManager linearLayoutManager, @Nullable ViewHolder holder) {
        if (holder == null) {
            return 0;
        }
        View view = holder.itemView;
        if (linearLayoutManager.canScrollVertically()) {
            if (linearLayoutManager.getStackFromEnd()) {
                return view.getBottom();
            }
            return view.getTop();
        } else if (!linearLayoutManager.canScrollHorizontally()) {
            return 0;
        } else {
            if (linearLayoutManager.getStackFromEnd()) {
                return view.getRight();
            }
            return view.getLeft();
        }
    }

    public void refreshAds(@NonNull String adUnitId) {
        refreshAds(adUnitId, null);
    }

    public void refreshAds(@NonNull String adUnitId, @Nullable RequestParameters requestParameters) {
        if (this.mRecyclerView == null) {
            MoPubLog.m12069w("This adapter is not attached to a RecyclerView and cannot be refreshed.");
            return;
        }
        LayoutManager layoutManager = this.mRecyclerView.getLayoutManager();
        if (layoutManager == null) {
            MoPubLog.m12069w("Can't refresh ads when there is no layout manager on a RecyclerView.");
        } else if (layoutManager instanceof LinearLayoutManager) {
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
            int firstPosition = linearLayoutManager.findFirstVisibleItemPosition();
            int scrollOffset = computeScrollOffset(linearLayoutManager, this.mRecyclerView.findViewHolderForLayoutPosition(firstPosition));
            int startOfRange = Math.max(0, firstPosition - 1);
            while (this.mStreamAdPlacer.isAd(startOfRange) && startOfRange > 0) {
                startOfRange--;
            }
            int itemCount = getItemCount();
            int endOfRange = linearLayoutManager.findLastVisibleItemPosition();
            while (this.mStreamAdPlacer.isAd(endOfRange) && endOfRange < itemCount - 1) {
                endOfRange++;
            }
            int originalStartOfRange = this.mStreamAdPlacer.getOriginalPosition(startOfRange);
            this.mStreamAdPlacer.removeAdsInRange(this.mStreamAdPlacer.getOriginalPosition(endOfRange), this.mOriginalAdapter.getItemCount());
            int numAdsRemoved = this.mStreamAdPlacer.removeAdsInRange(0, originalStartOfRange);
            if (numAdsRemoved > 0) {
                linearLayoutManager.scrollToPositionWithOffset(firstPosition - numAdsRemoved, scrollOffset);
            }
            loadAds(adUnitId, requestParameters);
        } else {
            MoPubLog.m12069w("This LayoutManager can't be refreshed.");
        }
    }

    public void clearAds() {
        this.mStreamAdPlacer.clearAds();
    }

    public boolean isAd(int position) {
        return this.mStreamAdPlacer.isAd(position);
    }

    public int getAdjustedPosition(int originalPosition) {
        return this.mStreamAdPlacer.getAdjustedPosition(originalPosition);
    }

    public int getOriginalPosition(int position) {
        return this.mStreamAdPlacer.getOriginalPosition(position);
    }

    public void setContentChangeStrategy(@NonNull ContentChangeStrategy strategy) {
        if (NoThrow.checkNotNull(strategy)) {
            this.mStrategy = strategy;
        }
    }

    public int getItemCount() {
        return this.mStreamAdPlacer.getAdjustedCount(this.mOriginalAdapter.getItemCount());
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType < NATIVE_AD_VIEW_TYPE_BASE || viewType > this.mStreamAdPlacer.getAdViewTypeCount() + NATIVE_AD_VIEW_TYPE_BASE) {
            return this.mOriginalAdapter.onCreateViewHolder(parent, viewType);
        }
        MoPubAdRenderer adRenderer = this.mStreamAdPlacer.getAdRendererForViewType(viewType + 56);
        if (adRenderer != null) {
            return new MoPubRecyclerViewHolder(adRenderer.createAdView((Activity) parent.getContext(), parent));
        }
        MoPubLog.m12069w("No view binder was registered for ads in MoPubRecyclerAdapter.");
        return null;
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        Object adResponse = this.mStreamAdPlacer.getAdData(position);
        if (adResponse != null) {
            this.mStreamAdPlacer.bindAdView((NativeAd) adResponse, holder.itemView);
            return;
        }
        this.mViewPositionMap.put(holder.itemView, Integer.valueOf(position));
        this.mVisibilityTracker.addView(holder.itemView, 0, null);
        this.mOriginalAdapter.onBindViewHolder(holder, this.mStreamAdPlacer.getOriginalPosition(position));
    }

    public int getItemViewType(int position) {
        int type = this.mStreamAdPlacer.getAdViewType(position);
        if (type != 0) {
            return type + NATIVE_AD_VIEW_TYPE_BASE;
        }
        return this.mOriginalAdapter.getItemViewType(this.mStreamAdPlacer.getOriginalPosition(position));
    }

    public void setHasStableIds(boolean hasStableIds) {
        setHasStableIdsInternal(hasStableIds);
        this.mOriginalAdapter.unregisterAdapterDataObserver(this.mAdapterDataObserver);
        this.mOriginalAdapter.setHasStableIds(hasStableIds);
        this.mOriginalAdapter.registerAdapterDataObserver(this.mAdapterDataObserver);
    }

    public void destroy() {
        this.mOriginalAdapter.unregisterAdapterDataObserver(this.mAdapterDataObserver);
        this.mStreamAdPlacer.destroy();
        this.mVisibilityTracker.destroy();
    }

    public long getItemId(int position) {
        if (!this.mOriginalAdapter.hasStableIds()) {
            return -1;
        }
        Object adData = this.mStreamAdPlacer.getAdData(position);
        if (adData != null) {
            return (long) (-System.identityHashCode(adData));
        }
        return this.mOriginalAdapter.getItemId(this.mStreamAdPlacer.getOriginalPosition(position));
    }

    public boolean onFailedToRecycleView(ViewHolder holder) {
        if (holder instanceof MoPubRecyclerViewHolder) {
            return super.onFailedToRecycleView(holder);
        }
        return this.mOriginalAdapter.onFailedToRecycleView(holder);
    }

    public void onViewAttachedToWindow(ViewHolder holder) {
        if (holder instanceof MoPubRecyclerViewHolder) {
            super.onViewAttachedToWindow(holder);
        } else {
            this.mOriginalAdapter.onViewAttachedToWindow(holder);
        }
    }

    public void onViewDetachedFromWindow(ViewHolder holder) {
        if (holder instanceof MoPubRecyclerViewHolder) {
            super.onViewDetachedFromWindow(holder);
        } else {
            this.mOriginalAdapter.onViewDetachedFromWindow(holder);
        }
    }

    public void onViewRecycled(ViewHolder holder) {
        if (holder instanceof MoPubRecyclerViewHolder) {
            super.onViewRecycled(holder);
        } else {
            this.mOriginalAdapter.onViewRecycled(holder);
        }
    }

    @VisibleForTesting
    void handleAdLoaded(int position) {
        if (this.mAdLoadedListener != null) {
            this.mAdLoadedListener.onAdLoaded(position);
        }
        notifyItemInserted(position);
    }

    @VisibleForTesting
    void handleAdRemoved(int position) {
        if (this.mAdLoadedListener != null) {
            this.mAdLoadedListener.onAdRemoved(position);
        }
        notifyItemRemoved(position);
    }

    private void handleVisibilityChanged(List<View> visibleViews, List<View> list) {
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

    private void setHasStableIdsInternal(boolean hasStableIds) {
        super.setHasStableIds(hasStableIds);
    }
}
