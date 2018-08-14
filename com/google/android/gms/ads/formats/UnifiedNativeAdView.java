package com.google.android.gms.ads.formats;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.ads.zzane;
import com.google.android.gms.internal.ads.zzkb;
import com.google.android.gms.internal.ads.zzqa;

public final class UnifiedNativeAdView extends FrameLayout {
    private final FrameLayout zzvp;
    private final zzqa zzvq = zzbf();

    public UnifiedNativeAdView(Context context) {
        super(context);
        this.zzvp = zzb(context);
    }

    public UnifiedNativeAdView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.zzvp = zzb(context);
    }

    public UnifiedNativeAdView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.zzvp = zzb(context);
    }

    @TargetApi(21)
    public UnifiedNativeAdView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.zzvp = zzb(context);
    }

    private final void zza(String str, View view) {
        try {
            this.zzvq.zzb(str, ObjectWrapper.wrap(view));
        } catch (Throwable e) {
            zzane.zzb("Unable to call setAssetView on delegate", e);
        }
    }

    private final FrameLayout zzb(Context context) {
        View frameLayout = new FrameLayout(context);
        frameLayout.setLayoutParams(new LayoutParams(-1, -1));
        addView(frameLayout);
        return frameLayout;
    }

    private final zzqa zzbf() {
        Preconditions.checkNotNull(this.zzvp, "createDelegate must be called after overlayFrame has been created");
        return isInEditMode() ? null : zzkb.zzig().zza(this.zzvp.getContext(), this, this.zzvp);
    }

    private final View zzp(String str) {
        try {
            IObjectWrapper zzak = this.zzvq.zzak(str);
            if (zzak != null) {
                return (View) ObjectWrapper.unwrap(zzak);
            }
        } catch (Throwable e) {
            zzane.zzb("Unable to call getAssetView on delegate", e);
        }
        return null;
    }

    public final void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        super.addView(view, i, layoutParams);
        super.bringChildToFront(this.zzvp);
    }

    public final void bringChildToFront(View view) {
        super.bringChildToFront(view);
        if (this.zzvp != view) {
            super.bringChildToFront(this.zzvp);
        }
    }

    public final void destroy() {
        try {
            this.zzvq.destroy();
        } catch (Throwable e) {
            zzane.zzb("Unable to destroy native ad view", e);
        }
    }

    public final AdChoicesView getAdChoicesView() {
        View zzp = zzp(UnifiedNativeAdAssetNames.ASSET_ADCHOICES_CONTAINER_VIEW);
        return zzp instanceof AdChoicesView ? (AdChoicesView) zzp : null;
    }

    public final View getAdvertiserView() {
        return zzp(UnifiedNativeAdAssetNames.ASSET_ADVERTISER);
    }

    public final View getBodyView() {
        return zzp(UnifiedNativeAdAssetNames.ASSET_BODY);
    }

    public final View getCallToActionView() {
        return zzp(UnifiedNativeAdAssetNames.ASSET_CALL_TO_ACTION);
    }

    public final View getHeadlineView() {
        return zzp(UnifiedNativeAdAssetNames.ASSET_HEADLINE);
    }

    public final View getIconView() {
        return zzp(UnifiedNativeAdAssetNames.ASSET_ICON);
    }

    public final View getImageView() {
        return zzp(UnifiedNativeAdAssetNames.ASSET_IMAGE);
    }

    public final MediaView getMediaView() {
        View zzp = zzp(UnifiedNativeAdAssetNames.ASSET_MEDIA_VIDEO);
        if (zzp instanceof MediaView) {
            return (MediaView) zzp;
        }
        if (zzp != null) {
            zzane.zzck("View is not an instance of MediaView");
        }
        return null;
    }

    public final View getPriceView() {
        return zzp(UnifiedNativeAdAssetNames.ASSET_PRICE);
    }

    public final View getStarRatingView() {
        return zzp(UnifiedNativeAdAssetNames.ASSET_STAR_RATING);
    }

    public final View getStoreView() {
        return zzp(UnifiedNativeAdAssetNames.ASSET_STORE);
    }

    public final void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        if (this.zzvq != null) {
            try {
                this.zzvq.zzb(ObjectWrapper.wrap(view), i);
            } catch (Throwable e) {
                zzane.zzb("Unable to call onVisibilityChanged on delegate", e);
            }
        }
    }

    public final void removeAllViews() {
        super.removeAllViews();
        super.addView(this.zzvp);
    }

    public final void removeView(View view) {
        if (this.zzvp != view) {
            super.removeView(view);
        }
    }

    public final void setAdChoicesView(AdChoicesView adChoicesView) {
        zza(UnifiedNativeAdAssetNames.ASSET_ADCHOICES_CONTAINER_VIEW, adChoicesView);
    }

    public final void setAdvertiserView(View view) {
        zza(UnifiedNativeAdAssetNames.ASSET_ADVERTISER, view);
    }

    public final void setBodyView(View view) {
        zza(UnifiedNativeAdAssetNames.ASSET_BODY, view);
    }

    public final void setCallToActionView(View view) {
        zza(UnifiedNativeAdAssetNames.ASSET_CALL_TO_ACTION, view);
    }

    public final void setClickConfirmingView(View view) {
        try {
            this.zzvq.zzc(ObjectWrapper.wrap(view));
        } catch (Throwable e) {
            zzane.zzb("Unable to call setClickConfirmingView on delegate", e);
        }
    }

    public final void setHeadlineView(View view) {
        zza(UnifiedNativeAdAssetNames.ASSET_HEADLINE, view);
    }

    public final void setIconView(View view) {
        zza(UnifiedNativeAdAssetNames.ASSET_ICON, view);
    }

    public final void setImageView(View view) {
        zza(UnifiedNativeAdAssetNames.ASSET_IMAGE, view);
    }

    public final void setMediaView(MediaView mediaView) {
        zza(UnifiedNativeAdAssetNames.ASSET_MEDIA_VIDEO, mediaView);
    }

    public final void setNativeAd(UnifiedNativeAd unifiedNativeAd) {
        try {
            this.zzvq.zza((IObjectWrapper) unifiedNativeAd.zzbe());
        } catch (Throwable e) {
            zzane.zzb("Unable to call setNativeAd on delegate", e);
        }
    }

    public final void setPriceView(View view) {
        zza(UnifiedNativeAdAssetNames.ASSET_PRICE, view);
    }

    public final void setStarRatingView(View view) {
        zza(UnifiedNativeAdAssetNames.ASSET_STAR_RATING, view);
    }

    public final void setStoreView(View view) {
        zza(UnifiedNativeAdAssetNames.ASSET_STORE, view);
    }
}
