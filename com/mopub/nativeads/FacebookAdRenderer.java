package com.mopub.nativeads;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.facebook.ads.MediaView;
import com.mopub.common.Preconditions;
import java.util.WeakHashMap;

public class FacebookAdRenderer implements MoPubAdRenderer<FacebookVideoEnabledNativeAd> {
    private final ViewBinder mViewBinder;
    final WeakHashMap<View, FacebookNativeViewHolder> mViewHolderMap = new WeakHashMap();

    static class FacebookNativeViewHolder {
        private final boolean isMainImageViewInRelativeView;
        private final MediaView mMediaView;
        private final StaticNativeViewHolder mStaticNativeViewHolder;

        private FacebookNativeViewHolder(StaticNativeViewHolder staticNativeViewHolder, MediaView mediaView, boolean mainImageViewInRelativeView) {
            this.mStaticNativeViewHolder = staticNativeViewHolder;
            this.mMediaView = mediaView;
            this.isMainImageViewInRelativeView = mainImageViewInRelativeView;
        }

        static FacebookNativeViewHolder fromViewBinder(View view, ViewBinder viewBinder) {
            StaticNativeViewHolder staticNativeViewHolder = StaticNativeViewHolder.fromViewBinder(view, viewBinder);
            View mainImageView = staticNativeViewHolder.mainImageView;
            boolean mainImageViewInRelativeView = false;
            MediaView mediaView = null;
            if (mainImageView != null) {
                ViewGroup mainImageParent = (ViewGroup) mainImageView.getParent();
                if (mainImageParent instanceof RelativeLayout) {
                    mainImageViewInRelativeView = true;
                }
                View viewAfterImageView = mainImageParent.getChildAt(mainImageParent.indexOfChild(mainImageView) + 1);
                if (viewAfterImageView instanceof MediaView) {
                    mediaView = (MediaView) viewAfterImageView;
                }
            }
            return new FacebookNativeViewHolder(staticNativeViewHolder, mediaView, mainImageViewInRelativeView);
        }

        public View getMainView() {
            return this.mStaticNativeViewHolder.mainView;
        }

        public TextView getTitleView() {
            return this.mStaticNativeViewHolder.titleView;
        }

        public TextView getTextView() {
            return this.mStaticNativeViewHolder.textView;
        }

        public TextView getCallToActionView() {
            return this.mStaticNativeViewHolder.callToActionView;
        }

        public ImageView getMainImageView() {
            return this.mStaticNativeViewHolder.mainImageView;
        }

        public ImageView getIconImageView() {
            return this.mStaticNativeViewHolder.iconImageView;
        }

        public ImageView getPrivacyInformationIconImageView() {
            return this.mStaticNativeViewHolder.privacyInformationIconImageView;
        }

        public MediaView getMediaView() {
            return this.mMediaView;
        }

        public boolean isMainImageViewInRelativeView() {
            return this.isMainImageViewInRelativeView;
        }
    }

    public FacebookAdRenderer(ViewBinder viewBinder) {
        this.mViewBinder = viewBinder;
    }

    public View createAdView(Context context, ViewGroup parent) {
        View adView = LayoutInflater.from(context).inflate(this.mViewBinder.layoutId, parent, false);
        View mainImageView = adView.findViewById(this.mViewBinder.mainImageId);
        if (mainImageView != null) {
            LayoutParams mainImageViewLayoutParams = mainImageView.getLayoutParams();
            RelativeLayout.LayoutParams mediaViewLayoutParams = new RelativeLayout.LayoutParams(mainImageViewLayoutParams.width, mainImageViewLayoutParams.height);
            if (mainImageViewLayoutParams instanceof MarginLayoutParams) {
                MarginLayoutParams marginParams = (MarginLayoutParams) mainImageViewLayoutParams;
                mediaViewLayoutParams.setMargins(marginParams.leftMargin, marginParams.topMargin, marginParams.rightMargin, marginParams.bottomMargin);
            }
            if (mainImageViewLayoutParams instanceof RelativeLayout.LayoutParams) {
                int[] rules = ((RelativeLayout.LayoutParams) mainImageViewLayoutParams).getRules();
                for (int i = 0; i < rules.length; i++) {
                    mediaViewLayoutParams.addRule(i, rules[i]);
                }
                mainImageView.setVisibility(4);
            } else {
                mainImageView.setVisibility(8);
            }
            ViewGroup mainImageParent = (ViewGroup) mainImageView.getParent();
            mainImageParent.addView(new MediaView(context), mainImageParent.indexOfChild(mainImageView) + 1, mediaViewLayoutParams);
        }
        return adView;
    }

    public void renderAdView(View view, FacebookVideoEnabledNativeAd facebookVideoEnabledNativeAd) {
        FacebookNativeViewHolder facebookNativeViewHolder = (FacebookNativeViewHolder) this.mViewHolderMap.get(view);
        if (facebookNativeViewHolder == null) {
            facebookNativeViewHolder = FacebookNativeViewHolder.fromViewBinder(view, this.mViewBinder);
            this.mViewHolderMap.put(view, facebookNativeViewHolder);
        }
        update(facebookNativeViewHolder, facebookVideoEnabledNativeAd);
        NativeRendererHelper.updateExtras(facebookNativeViewHolder.getMainView(), this.mViewBinder.extras, facebookVideoEnabledNativeAd.getExtras());
        setViewVisibility(facebookNativeViewHolder, 0);
    }

    public boolean supports(BaseNativeAd nativeAd) {
        Preconditions.checkNotNull(nativeAd);
        return nativeAd instanceof FacebookVideoEnabledNativeAd;
    }

    private void update(FacebookNativeViewHolder facebookNativeViewHolder, FacebookVideoEnabledNativeAd nativeAd) {
        ImageView mainImageView = facebookNativeViewHolder.getMainImageView();
        NativeRendererHelper.addTextView(facebookNativeViewHolder.getTitleView(), nativeAd.getTitle());
        NativeRendererHelper.addTextView(facebookNativeViewHolder.getTextView(), nativeAd.getText());
        NativeRendererHelper.addTextView(facebookNativeViewHolder.getCallToActionView(), nativeAd.getCallToAction());
        NativeImageHelper.loadImageView(nativeAd.getMainImageUrl(), mainImageView);
        NativeImageHelper.loadImageView(nativeAd.getIconImageUrl(), facebookNativeViewHolder.getIconImageView());
        NativeRendererHelper.addPrivacyInformationIcon(facebookNativeViewHolder.getPrivacyInformationIconImageView(), nativeAd.getPrivacyInformationIconImageUrl(), nativeAd.getPrivacyInformationIconClickThroughUrl());
        MediaView mediaView = facebookNativeViewHolder.getMediaView();
        if (mediaView != null && mainImageView != null) {
            nativeAd.updateMediaView(mediaView);
            mediaView.setVisibility(0);
            if (facebookNativeViewHolder.isMainImageViewInRelativeView()) {
                mainImageView.setVisibility(4);
            } else {
                mainImageView.setVisibility(8);
            }
        }
    }

    private static void setViewVisibility(FacebookNativeViewHolder facebookNativeViewHolder, int visibility) {
        if (facebookNativeViewHolder.getMainView() != null) {
            facebookNativeViewHolder.getMainView().setVisibility(visibility);
        }
    }
}
