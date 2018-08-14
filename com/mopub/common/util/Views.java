package com.mopub.common.util;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.ViewGroup;
import com.mopub.common.logging.MoPubLog;

public class Views {
    public static void removeFromParent(@Nullable View view) {
        if (view != null && view.getParent() != null && (view.getParent() instanceof ViewGroup)) {
            ((ViewGroup) view.getParent()).removeView(view);
        }
    }

    @Nullable
    public static View getTopmostView(@Nullable Context context, @Nullable View view) {
        View rootViewFromActivity = getRootViewFromActivity(context);
        return rootViewFromActivity != null ? rootViewFromActivity : getRootViewFromView(view);
    }

    @Nullable
    private static View getRootViewFromActivity(@Nullable Context context) {
        if (context instanceof Activity) {
            return ((Activity) context).getWindow().getDecorView().findViewById(16908290);
        }
        return null;
    }

    @Nullable
    private static View getRootViewFromView(@Nullable View view) {
        if (view == null) {
            return null;
        }
        if (!ViewCompat.isAttachedToWindow(view)) {
            MoPubLog.m12061d("Attempting to call View#getRootView() on an unattached View.");
        }
        View rootView = view.getRootView();
        if (rootView == null) {
            return null;
        }
        View rootContentView = rootView.findViewById(16908290);
        return rootContentView == null ? rootView : rootContentView;
    }
}
