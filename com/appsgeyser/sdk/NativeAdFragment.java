package com.appsgeyser.sdk;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import com.appsgeyser.sdk.ads.nativeAd.NativeAdHelper;

public class NativeAdFragment extends Fragment {
    private NativeAdHelper nativeAdHelper;
    private ProgressBar nativeAdProgressBar;
    private RecyclerView nativeAdRecyclerView;

    public static NativeAdFragment getFragment() {
        return new NativeAdFragment();
    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(C1195R.layout.appsgeysersdk_native_ad_activity, container, false);
        findAllViews(fragmentView);
        this.nativeAdHelper = new NativeAdHelper(getContext(), this.nativeAdRecyclerView, this.nativeAdProgressBar, false);
        return fragmentView;
    }

    private void findAllViews(View rootView) {
        this.nativeAdRecyclerView = (RecyclerView) rootView.findViewById(C1195R.id.appsgeysersdk_nativeAdRecycler);
        this.nativeAdProgressBar = (ProgressBar) rootView.findViewById(C1195R.id.appsgeysersdk_nativeAdProgressBar);
        ((Toolbar) rootView.findViewById(C1195R.id.toolbar)).setVisibility(8);
    }

    public void fragmentVisibilityChanged(boolean isVisible) {
        if (isVisible) {
            this.nativeAdHelper.onResume();
            this.nativeAdHelper.sendOfferWallImpression();
            return;
        }
        this.nativeAdHelper.onPause();
    }

    public void onDestroy() {
        super.onDestroy();
        this.nativeAdHelper.onDestroy();
    }
}
