package com.appnext.banners;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import com.appnext.core.C1128g;
import com.appnext.core.callbacks.OnECPMLoaded;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;

public abstract class C1005d {
    private BannerListener bannerListener;
    private BannerSize bannerSize;
    protected Context context;
    private String placementId;
    protected ViewGroup rootView;

    public abstract void click();

    public abstract void getECPM(BannerAdRequest bannerAdRequest, OnECPMLoaded onECPMLoaded);

    public abstract void impression();

    public abstract void loadAd(BannerAdRequest bannerAdRequest);

    public void parseAttributeSet(AttributeSet attributeSet) {
    }

    public void init(ViewGroup viewGroup) {
        this.rootView = viewGroup;
        this.context = viewGroup.getContext();
    }

    public void setPlacementId(String str) {
        if (str == null) {
            throw new IllegalArgumentException("The placement id cannot be null.");
        } else if (this.placementId != null) {
            throw new IllegalStateException("The placement id can only be set once.");
        } else {
            this.placementId = str;
        }
    }

    public String getPlacementId() {
        return this.placementId;
    }

    public void setBannerSize(BannerSize bannerSize) {
        if (bannerSize == null) {
            throw new IllegalArgumentException("The placement id cannot be null.");
        } else if (this.bannerSize != null) {
            throw new IllegalStateException("The banner size can only be set once.");
        } else {
            this.bannerSize = bannerSize;
        }
    }

    public BannerSize getBannerSize() {
        return this.bannerSize;
    }

    public void setBannerListener(BannerListener bannerListener) {
        this.bannerListener = bannerListener;
    }

    public BannerListener getBannerListener() {
        return this.bannerListener;
    }

    public void onScrollChanged(int i) {
    }

    public void destroy() {
        this.context = null;
    }

    public void play() {
    }

    public void pause() {
    }

    public boolean isClickEnabled() {
        return false;
    }

    public void setClickEnabled(boolean z) {
    }

    private boolean isViewPartiallyVisible(View view) {
        try {
            if (this.rootView.getParent() == null) {
                return false;
            }
            Rect rect = new Rect();
            ((ViewGroup) this.rootView.getParent()).getHitRect(rect);
            return view.getLocalVisibleRect(rect);
        } catch (Throwable th) {
            C1128g.m2351c(th);
            return false;
        }
    }

    public int getVisiblePercent(View view) {
        if (!isViewPartiallyVisible(this.rootView) || this.rootView.getWindowVisibility() == 8 || this.rootView.getWindowVisibility() == 4) {
            return 0;
        }
        Rect rect = new Rect();
        view.getGlobalVisibleRect(rect);
        return (int) ((((double) (rect.height() * rect.width())) * 100.0d) / ((double) (view.getWidth() * view.getHeight())));
    }

    protected void openLink(String str) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse(str));
        intent.addFlags(ErrorDialogData.BINDER_CRASH);
        this.context.startActivity(intent);
    }
}
