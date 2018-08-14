package com.mopub.mobileads.dfp.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import com.google.android.gms.ads.formats.NativeAd.Image;
import com.google.android.gms.ads.mediation.NativeAppInstallAdMapper;
import com.mopub.common.UrlAction;
import com.mopub.common.UrlHandler.Builder;
import com.mopub.common.util.Drawables;
import com.mopub.nativeads.NativeImageHelper;
import com.mopub.nativeads.StaticNativeAd;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MoPubNativeAppInstallAdMapper extends NativeAppInstallAdMapper {
    private StaticNativeAd mMopubNativeAdData;
    private int mPrivacyIconSize;
    private int privacyIconPlacement;
    private ImageView privacyInformationIconImageView;

    public MoPubNativeAppInstallAdMapper(StaticNativeAd ad, HashMap<String, Drawable> drawableMap, int privacyIconPlacementParam, int privacyIconSize) {
        this.mMopubNativeAdData = ad;
        setHeadline(this.mMopubNativeAdData.getTitle());
        setBody(this.mMopubNativeAdData.getText());
        setCallToAction(this.mMopubNativeAdData.getCallToAction());
        this.privacyIconPlacement = privacyIconPlacementParam;
        this.mPrivacyIconSize = privacyIconSize;
        List<Image> imagesList;
        if (drawableMap != null) {
            setIcon(new MoPubNativeMappedImage((Drawable) drawableMap.get(DownloadDrawablesAsync.KEY_ICON), this.mMopubNativeAdData.getIconImageUrl(), MoPubAdapter.DEFAULT_MOPUB_IMAGE_SCALE));
            imagesList = new ArrayList();
            imagesList.add(new MoPubNativeMappedImage((Drawable) drawableMap.get(DownloadDrawablesAsync.KEY_IMAGE), this.mMopubNativeAdData.getMainImageUrl(), MoPubAdapter.DEFAULT_MOPUB_IMAGE_SCALE));
            setImages(imagesList);
        } else {
            setIcon(new MoPubNativeMappedImage(null, this.mMopubNativeAdData.getIconImageUrl(), MoPubAdapter.DEFAULT_MOPUB_IMAGE_SCALE));
            imagesList = new ArrayList();
            imagesList.add(new MoPubNativeMappedImage(null, this.mMopubNativeAdData.getMainImageUrl(), MoPubAdapter.DEFAULT_MOPUB_IMAGE_SCALE));
            setImages(imagesList);
        }
        setOverrideClickHandling(true);
        setOverrideImpressionRecording(true);
    }

    public void untrackView(View view) {
        super.untrackView(view);
        this.mMopubNativeAdData.clear(view);
        if (this.privacyInformationIconImageView != null && ((ViewGroup) this.privacyInformationIconImageView.getParent()) != null) {
            ((ViewGroup) this.privacyInformationIconImageView.getParent()).removeView(this.privacyInformationIconImageView);
        }
    }

    public void trackView(View view) {
        this.mMopubNativeAdData.prepare(view);
        if (view instanceof ViewGroup) {
            ViewGroup adView = (ViewGroup) view;
            View overlayView = adView.getChildAt(adView.getChildCount() - 1);
            if (overlayView instanceof FrameLayout) {
                final Context context = view.getContext();
                if (context != null) {
                    this.privacyInformationIconImageView = new ImageView(context);
                    String privacyInformationImageUrl = this.mMopubNativeAdData.getPrivacyInformationIconImageUrl();
                    final String privacyInformationClickthroughUrl = this.mMopubNativeAdData.getPrivacyInformationIconClickThroughUrl();
                    if (privacyInformationImageUrl == null) {
                        this.privacyInformationIconImageView.setImageDrawable(Drawables.NATIVE_PRIVACY_INFORMATION_ICON.createDrawable(context));
                    } else {
                        NativeImageHelper.loadImageView(privacyInformationImageUrl, this.privacyInformationIconImageView);
                    }
                    this.privacyInformationIconImageView.setOnClickListener(new OnClickListener() {
                        public void onClick(View v) {
                            new Builder().withSupportedUrlActions(UrlAction.IGNORE_ABOUT_SCHEME, UrlAction.OPEN_NATIVE_BROWSER, UrlAction.OPEN_IN_APP_BROWSER, UrlAction.HANDLE_SHARE_TWEET, UrlAction.FOLLOW_DEEP_LINK_WITH_FALLBACK, UrlAction.FOLLOW_DEEP_LINK).build().handleUrl(context, privacyInformationClickthroughUrl);
                        }
                    });
                    this.privacyInformationIconImageView.setVisibility(0);
                    ((ViewGroup) overlayView).addView(this.privacyInformationIconImageView);
                    int icon_size_px = (int) (((double) (((float) this.mPrivacyIconSize) * context.getResources().getDisplayMetrics().density)) + 0.5d);
                    LayoutParams params = new LayoutParams(icon_size_px, icon_size_px);
                    switch (this.privacyIconPlacement) {
                        case 0:
                            params.gravity = 51;
                            break;
                        case 1:
                            params.gravity = 53;
                            break;
                        case 2:
                            params.gravity = 85;
                            break;
                        case 3:
                            params.gravity = 83;
                            break;
                        default:
                            params.gravity = 53;
                            break;
                    }
                    this.privacyInformationIconImageView.setLayoutParams(params);
                    adView.requestLayout();
                    return;
                }
                return;
            }
            Log.d(MoPubAdapter.TAG, "Failed to show AdChoices icon.");
        }
    }

    public void recordImpression() {
    }

    public void handleClick(View view) {
    }
}
