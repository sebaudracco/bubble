package com.appsgeyser.sdk.ads.nativeAd;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;

public abstract class EndlessRecyclerViewScrollListener extends android.support.v7.widget.RecyclerView.OnScrollListener {
    private int currentPage = 0;
    private OnScrollListener listener;
    private boolean loading = true;
    LayoutManager mLayoutManager;
    private int previousTotalItemCount = 0;
    private int startingPageIndex = 0;
    private int visibleThreshold = 5;

    public interface OnScrollListener {
        void sendItemsImpressions();
    }

    public abstract void onLoadMore(int i, int i2, RecyclerView recyclerView);

    public EndlessRecyclerViewScrollListener(LinearLayoutManager layoutManager, OnScrollListener listener) {
        this.mLayoutManager = layoutManager;
        this.listener = listener;
    }

    public EndlessRecyclerViewScrollListener(GridLayoutManager layoutManager, OnScrollListener listener) {
        this.mLayoutManager = layoutManager;
        this.visibleThreshold *= layoutManager.getSpanCount();
        this.listener = listener;
    }

    public EndlessRecyclerViewScrollListener(StaggeredGridLayoutManager layoutManager, OnScrollListener listener) {
        this.mLayoutManager = layoutManager;
        this.visibleThreshold *= layoutManager.getSpanCount();
        this.listener = listener;
    }

    public int getLastVisibleItem(int[] lastVisibleItemPositions) {
        int maxSize = 0;
        for (int i = 0; i < lastVisibleItemPositions.length; i++) {
            if (i == 0) {
                maxSize = lastVisibleItemPositions[i];
            } else if (lastVisibleItemPositions[i] > maxSize) {
                maxSize = lastVisibleItemPositions[i];
            }
        }
        return maxSize;
    }

    public void onScrolled(RecyclerView view, int dx, int dy) {
        int lastVisibleItemPosition = 0;
        int totalItemCount = this.mLayoutManager.getItemCount();
        if (this.mLayoutManager instanceof StaggeredGridLayoutManager) {
            lastVisibleItemPosition = getLastVisibleItem(((StaggeredGridLayoutManager) this.mLayoutManager).findLastVisibleItemPositions(null));
        } else if (this.mLayoutManager instanceof GridLayoutManager) {
            lastVisibleItemPosition = ((GridLayoutManager) this.mLayoutManager).findLastVisibleItemPosition();
        } else if (this.mLayoutManager instanceof LinearLayoutManager) {
            lastVisibleItemPosition = ((LinearLayoutManager) this.mLayoutManager).findLastVisibleItemPosition();
        }
        if (totalItemCount < this.previousTotalItemCount) {
            this.currentPage = this.startingPageIndex;
            this.previousTotalItemCount = totalItemCount;
            if (totalItemCount == 0) {
                this.loading = true;
            }
        }
        if (this.loading && totalItemCount > this.previousTotalItemCount) {
            this.loading = false;
            this.previousTotalItemCount = totalItemCount;
        }
        if (!this.loading && this.visibleThreshold + lastVisibleItemPosition > totalItemCount) {
            this.currentPage++;
            onLoadMore(this.currentPage, totalItemCount, view);
            this.loading = true;
        }
        this.listener.sendItemsImpressions();
    }

    public void resetState() {
        this.currentPage = this.startingPageIndex;
        this.previousTotalItemCount = 0;
        this.loading = true;
    }
}
