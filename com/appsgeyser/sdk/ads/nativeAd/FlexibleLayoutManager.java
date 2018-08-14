package com.appsgeyser.sdk.ads.nativeAd;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.GridLayoutManager.SpanSizeLookup;
import android.support.v7.widget.RecyclerView.Recycler;
import android.support.v7.widget.RecyclerView.State;
import android.util.Log;

public class FlexibleLayoutManager extends GridLayoutManager {
    public FlexibleLayoutManager(Context context, SpanSizeLookup spanSizeLookup) {
        super(context, 3);
        setSpanSizeLookup(spanSizeLookup);
    }

    public void onLayoutChildren(Recycler recycler, State state) {
        try {
            super.onLayoutChildren(recycler, state);
        } catch (Exception e) {
            Log.e("NativeAdList", e.toString());
        }
    }
}
