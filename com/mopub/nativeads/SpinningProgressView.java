package com.mopub.nativeads;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ProgressBar;
import com.mopub.common.Preconditions;
import com.mopub.common.util.Dips;
import com.mopub.common.util.Views;

class SpinningProgressView extends ViewGroup {
    @NonNull
    private final ProgressBar mProgressBar;
    private int mProgressIndicatorRadius;

    SpinningProgressView(@NonNull Context context) {
        super(context);
        LayoutParams params = new LayoutParams(-1, -1);
        params.gravity = 17;
        setLayoutParams(params);
        setVisibility(8);
        setBackgroundColor(-16777216);
        getBackground().setAlpha(180);
        this.mProgressBar = new ProgressBar(context);
        this.mProgressIndicatorRadius = Dips.asIntPixels(25.0f, getContext());
        this.mProgressBar.setIndeterminate(true);
        addView(this.mProgressBar);
    }

    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        if (changed) {
            int centerX = (left + right) / 2;
            int centerY = (top + bottom) / 2;
            this.mProgressBar.layout(centerX - this.mProgressIndicatorRadius, centerY - this.mProgressIndicatorRadius, this.mProgressIndicatorRadius + centerX, this.mProgressIndicatorRadius + centerY);
        }
    }

    boolean addToRoot(@NonNull View view) {
        Preconditions.checkNotNull(view);
        View rootView = view.getRootView();
        if (rootView == null || !(rootView instanceof ViewGroup)) {
            return false;
        }
        ViewGroup rootViewGroup = (ViewGroup) rootView;
        setVisibility(0);
        setMeasuredDimension(rootView.getWidth(), rootView.getHeight());
        rootViewGroup.addView(this);
        forceLayout();
        return true;
    }

    boolean removeFromRoot() {
        Views.removeFromParent(this);
        setVisibility(8);
        return true;
    }
}
